package org.example;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Field;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Aggregates.*;

public class CrudRead {
    public static List<Document> getCoachAthletes(ObjectId coachId) {
        /**
         * Function returns all athletes which trainer is the trainer with coachId id.
         */

        MongoCollection<Document> athletes = Main.db.getCollection("Zawodnicy");

        List<Document> pipeline = List.of(new Document("$addFields",
                new Document("coach", new Document("$toObjectId", "$coach"))),
                new Document("$match", new Document("coach", coachId)));

        AggregateIterable<Document> results = athletes.aggregate(pipeline);

        List<Document> resultList = new ArrayList<>();
        for (Document document: results)
            resultList.add(document);

        return resultList;
    }

    public static List<Document> getAllAthletesInClub(String clubName) {
        /**
         * Function returns all athletes from specified club
         */
        return null;
    }

    public static List<Document> getAllAthletesInThisMeeting(ObjectId meetingId) {
        /**
         * Function returns specific data of all athletes that are reported to the meeting, no matter what status do they have.
         * It returns list of documents which consists of
         * * id of report
         * * date of report
         * * firstname and lastname of competitor
         * * its birthdate
         * * discipline in which it will be competing
         * * personal record in this discipline or null if it is a debut (it is also necessary if it is an outdoor or indoor meeting)
         * * status of report
         */

        MongoCollection<Document> athletes = Main.db.getCollection("Zgloszenia");

        // Document addFields = new Document("$addFields", (new Document("meetingId", new Document("$toObjectId", "$meetingId"))));
        // Document lookup = new Document("$lookup", new Document());
        Bson add = addFields(new Field<>("meetingId", new Document("$toObjectId", "$meetingId")),
                new Field<>("athleteId", new Document("$toObjectId", "$athleteId")));
        Bson match = match(eq("meetingId", meetingId));
        Bson lookAthlete = lookup("Zawodnicy", "athleteId", "_id", "athlete");
        Bson lookMeeting = lookup("Zawody", "meetingId", "_id", "meeting");
        Bson unwindAthlete = unwind("$athlete");
        Bson unwindMeeting = unwind("$meeting");
        Bson project = project(new Document("_id", 0)
                .append("reportId", "$_id")
                .append("meetingId", 1)
                .append("firstname", "$athlete.firstname")
                .append("lastname", "$athlete.lastname")
                .append("birthDate", "$athlete.birth_date")
                .append("discipline", 1)
                .append("status", 1)
                .append("reportDate", "$date")
                .append("personal_records_outdoor", "$athlete.personal_records_outdoor")
                .append("meeting", 1)
        );


        List<Bson> pipeline = List.of(add, match, lookAthlete, unwindAthlete, lookMeeting, unwindMeeting, project);

        AggregateIterable<Document> results = athletes.aggregate(pipeline);

        Document first = results.first();
        Document meetingDocument = first.get("meeting", Document.class);
        String indoorOrOutdoor = meetingDocument.containsKey("indoor") ? "short_track" : "outdoor";
        System.out.println(indoorOrOutdoor);

        List<Document> resultList = new ArrayList<>();


        for (Document document: results) {
            String discipline = document.getString("discipline");
            String disciplineWithoutGender = discipline.substring(0, discipline.length()-2);

            Double personalRecord = getPersonalRecord(document, disciplineWithoutGender, "personal_records_" + indoorOrOutdoor);
            document.put("personalRecord", personalRecord);
            document.remove("personal_records_outdoor");
            document.remove("meeting");

            resultList.add(document);
        }
        return resultList;
    }

    public static List<Document> getAthletesInThisMeetingInThisCompetition(ObjectId meetingId, String competition) {
        /**
         * Returns athletes starting in specified competition in specified meeting - same values as getAllAthletesInThisMeeting function.
         */
        List<Document> allAthletes = getAllAthletesInThisMeeting(meetingId);
        List<Document> resultList = new ArrayList<>();
        for (Document document: allAthletes) {
            if (document.get("discipline").equals(competition))
                resultList.add(document);
        }
        return resultList;
    }

    private static Double getPersonalRecord(Document document, String discipline, String place) {
        Document personalRecordsOutdoor = (Document) document.get(place);

        Double personalRecord = personalRecordsOutdoor.getDouble(discipline);
        return personalRecord;
    }

    public static void printResults(List<Document> resultList) {
        for (Document document: resultList)
            System.out.println(document.toJson());
    }
}
