package course4.L1_Strings;

public class MultiLineStrings {

    public static void main(String[] args) {
        // JSON example
        String json = """
                {
                  "id": 101,
                  "name": "Java Course",
                  "price": 499
                }
                """;
        System.out.println("JSON String:\n" + json);

        // SQL example
        String query = """
                SELECT id, name, email
                FROM users
                WHERE status = 'active'
                ORDER BY name ASC;
                """;
        System.out.println("SQL Query:\n" + query);

        // Using formatted()
        String template = """
                Hello, %s!
                Welcome to the %s course.
                """;
        String message = template.formatted("Vinoth", "Java");
        System.out.println("Formatted Message:\n" + message);
    }
}