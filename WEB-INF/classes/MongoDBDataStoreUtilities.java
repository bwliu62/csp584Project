import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.AggregationOutput;

import java.util.*;

public class MongoDBDataStoreUtilities {
    static DBCollection doctorReviews;
    static DBCollection hospitalReviews;
    static DBCollection insuranceReviews;

    public static void getConnection() {
        MongoClient mongo;
        mongo = new MongoClient("localhost", 27017);

        DB db = mongo.getDB("CustomerReviews");
        doctorReviews = db.getCollection("doctorReviews");
        hospitalReviews = db.getCollection("hospitalReviews");
        insuranceReviews = db.getCollection("insuranceReviews");
    }

    public static String insertDoctorReview(String doctorId, String userId, String reviewrating, String reviewdate,
            String reviewtext) {

        try {
            getConnection();

            BasicDBObject doc = new BasicDBObject("title", "doctorReviews").append("doctorId", doctorId)
                    .append("userId", userId).append("reviewrating", Integer.parseInt(reviewrating))
                    .append("reviewdate", reviewdate).append("reviewtext", reviewtext);
            doctorReviews.insert(doc);
            return "Successfull";
        } catch (Exception e) {
            return "UnSuccessfull";
        }

    }

    //////////////////////////////
    public static String insertHospitalReview(String hospitalId, String reviewrating, String reviewdate,
            String reviewtext) {

        try {
            getConnection();

            BasicDBObject hos = new BasicDBObject("title", "hospitalReviews").append("hospitalId", hospitalId)
                    .append("reviewrating", Integer.parseInt(reviewrating)).append("reviewdate", reviewdate)
                    .append("reviewtext", reviewtext);
            hospitalReviews.insert(hos);
            return "Successfull";
        } catch (Exception e) {
            return "UnSuccessfull";
        }

    }

    public static String insertInsuranceReview(String insuranceId, String reviewrating, String reviewdate,
            String reviewtext) {

        try {
            getConnection();

            BasicDBObject ins = new BasicDBObject("title", "insuranceReviews").append("insuranceId", insuranceId)
                    .append("reviewrating", Integer.parseInt(reviewrating)).append("reviewdate", reviewdate)
                    .append("reviewtext", reviewtext);
            insuranceReviews.insert(ins);
            return "Successfull";
        } catch (Exception e) {
            return "UnSuccessfull";
        }

    }
    ///////////////////////////////

    public static void insertDefaultDoctorReview() {
        insertDoctorReview("2", "1", "7", "2021-11-01 16:59:17.007", "review1");
        insertDoctorReview("2", "3", "4", "2021-11-01 16:59:17.007", "review7");
        insertDoctorReview("2", "4", "2", "2021-11-01 16:59:17.007", "review8");
        insertDoctorReview("5", "6", "4", "2021-11-01 16:59:17.007", "review2");
        insertDoctorReview("9", "7", "6", "2021-11-01 16:59:17.007", "review3");
        insertDoctorReview("7", "8", "8", "2021-11-02 23:34:10.917", "review4");
        insertDoctorReview("11", "9", "3", "2021-11-02 23:34:10.917", "review5");
        insertDoctorReview("12", "10", "9", "2021-11-02 23:34:10.917", "review6");

        insertDoctorReview("11", "11", "6", "2021-11-07 05:10:33.568", "review11");
        insertDoctorReview("12", "13", "8", "2021-11-08 19:55:02.403", "review12");
        insertDoctorReview("13", "14", "5", "2021-11-08 18:13:48.016", "review13");
        insertDoctorReview("14", "15", "8", "2021-11-09 02:38:30.743", "review14");
        insertDoctorReview("15", "16", "4", "2021-11-09 02:45:20.898", "review15");
        insertDoctorReview("16", "17", "10", "2021-11-10 23:34:10.917", "review16");
        insertDoctorReview("17", "18", "6", "2021-11-10 08:56:51.496", "review17");
        insertDoctorReview("18", "19", "9", "2021-11-11 23:40:53.423", "review18");
        insertDoctorReview("19", "20", "9", "2021-11-11 03:46:57.240", "review19");
        insertDoctorReview("20", "21", "9", "2021-11-11 22:33:31.979", "review20");
        insertDoctorReview("21", "22", "10", "2021-11-12 23:34:10.927", "review21");
        insertDoctorReview("22", "23", "9", "2021-11-12 05:02:18.606", "review22");
        insertDoctorReview("23", "24", "7", "2021-11-12 08:33:31.044", "review23");
        insertDoctorReview("24", "25", "6", "2021-11-13 06:15:03.549", "review24");
        insertDoctorReview("25", "26", "10", "2021-11-13 23:14:10.927", "review25");
        insertDoctorReview("26", "27", "4", "2021-11-14 18:54:33.156", "review26");
        insertDoctorReview("27", "28", "6", "2021-11-14 16:29:10.059", "review27");
        insertDoctorReview("28", "1", "6", "2021-11-14 10:26:20.667", "review28");
        insertDoctorReview("29", "2", "10", "2021-11-14 23:14:10.117", "review29");
        insertDoctorReview("30", "3", "7", "2021-11-15 08:17:01.875", "review30");
        insertDoctorReview("31", "4", "8", "2021-11-15 21:40:29.769", "review31");
        insertDoctorReview("32", "5", "6", "2021-11-15 23:46:14.771", "review32");
        insertDoctorReview("33", "6", "7", "2021-11-15 17:22:18.375", "review33");
        insertDoctorReview("34", "7", "6", "2021-11-15 23:47:29.520", "review34");
        insertDoctorReview("35", "8", "9", "2021-11-15 12:59:57.867", "review35");
        insertDoctorReview("36", "9", "9", "2021-11-15 11:40:03.984", "review36");

    }

    /////////////////////////////////////////////////
    public static void insertDefaultHospitalReview() {

        insertHospitalReview("61", "9", "2021-11-23 06:18:20.249", "review61");
        insertHospitalReview("62", "4", "2021-11-23 00:20:36.215", "review62");
        insertHospitalReview("63", "4", "2021-11-24 12:29:45.902", "review63");
        insertHospitalReview("64", "10", "2021-11-24 12:34:12.231", "review64");
        insertHospitalReview("65", "6", "2021-11-25 00:37:57.392", "review65");
        insertHospitalReview("66", "9", "2021-11-26 13:11:26.223", "review66");
        insertHospitalReview("67", "10", "2021-11-26 13:34:20.127", "review67");
        insertHospitalReview("68", "6", "2021-11-27 09:55:17.252", "review68");
        insertHospitalReview("69", "9", "2021-11-27 12:08:38.401", "review69");
        insertHospitalReview("70", "5", "2021-11-27 21:17:20.515", "review70");
        insertHospitalReview("71", "9", "2021-11-27 17:06:56.126", "review71");

    }

    public static void insertDefaultInsuranceReview() {
        insertInsuranceReview("91", "10", "2021-11-16 12:24:10.127", "review37");
        insertInsuranceReview("92", "9", "2021-11-17 15:49:52.673", "review38");
        insertInsuranceReview("93", "7", "2021-11-17 04:58:47.139", "review39");
        insertInsuranceReview("94", "4", "2021-11-17 20:42:54.319", "review40");
        insertInsuranceReview("95", "8", "2021-11-17 16:48:39.745", "review41");
        insertInsuranceReview("96", "6", "2021-11-18 00:19:47.886", "review42");
        insertInsuranceReview("97", "6", "2021-11-18 18:55:33.719", "review43");
        insertInsuranceReview("98", "6", "2021-11-18 01:22:57.874", "review44");
        insertInsuranceReview("99", "5", "2021-11-18 15:18:07.423", "review45");
        insertInsuranceReview("100", "6", "2021-11-18 16:51:42.574", "review45");
        insertInsuranceReview("101", "7", "2021-11-18 20:44:41.109", "review47");
        insertInsuranceReview("102", "5", "2021-11-18 11:19:22.342", "review48");

    }

    /////////////////////////////////////////////////
    public static HashMap<String, ArrayList<Review>> selectDoctorReview() {
        HashMap<String, ArrayList<Review>> reviews = null;

        try {

            getConnection();
            DBCursor cursor = doctorReviews.find();
            reviews = new HashMap<String, ArrayList<Review>>();
            while (cursor.hasNext()) {
                BasicDBObject obj = (BasicDBObject) cursor.next();

                if (!reviews.containsKey(obj.getString("doctorId"))) {
                    ArrayList<Review> arr = new ArrayList<Review>();
                    reviews.put(obj.getString("doctorId"), arr);
                }
                ArrayList<Review> listReview = reviews.get(obj.getString("doctorId"));
                Review review = new Review(obj.getString("doctorId"), obj.getString("reviewrating"),
                        obj.getString("reviewdate"), obj.getString("reviewtext"));
                // add to review hashmap
                listReview.add(review);

            }
            return reviews;
        } catch (Exception e) {
            reviews = null;
            return reviews;
        }

    }

    //////////////////////////////////////////////

    public static HashMap<String, ArrayList<Review>> selectHospitalReview() {
        HashMap<String, ArrayList<Review>> reviews = null;

        try {

            getConnection();
            DBCursor cursor = hospitalReviews.find();
            reviews = new HashMap<String, ArrayList<Review>>();
            while (cursor.hasNext()) {
                BasicDBObject obj = (BasicDBObject) cursor.next();

                if (!reviews.containsKey(obj.getString("hospitalId"))) {
                    ArrayList<Review> arr = new ArrayList<Review>();
                    reviews.put(obj.getString("hospitalId"), arr);
                }
                ArrayList<Review> listReview = reviews.get(obj.getString("hospitalId"));
                Review review = new Review(obj.getString("hospitalId"), obj.getString("reviewrating"),
                        obj.getString("reviewdate"), obj.getString("reviewtext"));
                // add to review hashmap
                listReview.add(review);

            }
            return reviews;
        } catch (Exception e) {
            reviews = null;
            return reviews;
        }

    }

    public static HashMap<String, ArrayList<Review>> selectInsuranceReview() {
        HashMap<String, ArrayList<Review>> reviews = null;

        try {

            getConnection();
            DBCursor cursor = insuranceReviews.find();
            reviews = new HashMap<String, ArrayList<Review>>();
            while (cursor.hasNext()) {
                BasicDBObject obj = (BasicDBObject) cursor.next();

                if (!reviews.containsKey(obj.getString("insuranceId"))) {
                    ArrayList<Review> arr = new ArrayList<Review>();
                    reviews.put(obj.getString("insuranceId"), arr);
                }
                ArrayList<Review> listReview = reviews.get(obj.getString("insuranceId"));
                Review review = new Review(obj.getString("insuranceId"), obj.getString("reviewrating"),
                        obj.getString("reviewdate"), obj.getString("reviewtext"));
                // add to review hashmap
                listReview.add(review);

            }
            return reviews;
        } catch (Exception e) {
            reviews = null;
            return reviews;
        }

    }

    ////////////////////////////////////////////////

    // public static ArrayList<Bestrating> topProducts() {
    // ArrayList<Bestrating> Bestrate = new ArrayList<Bestrating>();
    // try {

    // getConnection();
    // int retlimit = 5;
    // DBObject sort = new BasicDBObject();
    // sort.put("reviewrating", -1);
    // DBCursor cursor = myReviews.find().limit(retlimit).sort(sort);
    // while (cursor.hasNext()) {
    // BasicDBObject obj = (BasicDBObject) cursor.next();

    // String prodcutnm = obj.get("productname").toString();
    // String rating = obj.get("reviewrating").toString();
    // Bestrating best = new Bestrating(prodcutnm, rating);
    // Bestrate.add(best);
    // }

    // } catch (Exception e) {
    // System.out.println(e.getMessage());
    // }
    // return Bestrate;
    // }
}