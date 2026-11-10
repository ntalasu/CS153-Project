public class NamedRecordTest
{
    private static class NamedRecordTest_AddressRec
    {
        String street;
        String city;
        String state;
        String zip;
    }

    private static class NamedRecordTest_PersonRec
    {
        String firstName;
        String lastName;
        int age;
        NamedRecordTest_AddressRec address[];
        String phones[];

        NamedRecordTest_PersonRec()
        {
            address = new NamedRecordTest_AddressRec[4];
            for (int _i1 = 0; _i1 < 4; _i1++)
                address[_i1] = new NamedRecordTest_AddressRec();
            phones = new String[2];
        }
    }

    private static NamedRecordTest_PersonRec john;
    private static NamedRecordTest_PersonRec mary;
    private static NamedRecordTest_PersonRec team[];
    private static NamedRecordTest_PersonRec dept[][];

    static
    {
        john = new NamedRecordTest_PersonRec();
        mary = new NamedRecordTest_PersonRec();
        team = new NamedRecordTest_PersonRec[5];
        for (int _i1 = 0; _i1 < 5; _i1++)
            team[_i1] = new NamedRecordTest_PersonRec();
        dept = new NamedRecordTest_PersonRec[4][5];
        for (int _i1 = 0; _i1 < 4; _i1++)
            for (int _i2 = 0; _i2 < 5; _i2++)
                dept[_i1][_i2] = new NamedRecordTest_PersonRec();
    }

    static void print(NamedRecordTest_PersonRec person, String heading)
    {
        int age;
        NamedRecordTest_AddressRec addr = new NamedRecordTest_AddressRec();
        String phs[] = new String[2];

        age = person.age;
        addr.street = person.address[2].street;
        addr.city = person.address[2].city;
        addr.state = person.address[2].state;
        addr.zip = person.address[2].zip;
        phs[0] = person.phones[0];
        phs[1] = person.phones[1];
        System.out.println();
        System.out.printf("----------\n");
        System.out.printf("%s\n", heading);
        System.out.printf("----------\n");
        System.out.printf("First name: %s\n", person.firstName);
        System.out.printf(" Last name: %s\n", person.lastName);
        System.out.printf("       Age: %d\n", age);
        System.out.printf("    Street: %s\n", addr.street);
        System.out.printf("      City: %s\n", addr.city);
        System.out.printf("     State: %s\n", addr.state);
        System.out.printf("       ZIP: %s\n", addr.zip);
        System.out.printf("  Phone #1: %s\n", phs[0]);
        System.out.printf("  Phone #2: %s\n", phs[1]);
    }

    public static void main(String[] args)
    {
        java.time.Instant _start = java.time.Instant.now();
        john.firstName = "John";
        john.lastName = "Doe";
        john.age = 24;
        john.address[2].street = "1680 25th Street";
        john.address[2].city = "San Pablo";
        john.address[2].state = "CALIFORNIA";
        john.address[2].zip = "94806";
        john.phones[0] = "111-1111";
        john.phones[1] = "222-2222";
        print(john, "John");
        mary.firstName = "Mary";
        mary.lastName = "Jane";
        mary.age = 22;
        mary.address[2].street = "4899 Bela Drive ";
        mary.address[2].city = "San Jose";
        mary.address[2].state = "CALIFORNIA";
        mary.address[2].zip = "95129";
        mary.phones[0] = "333-3333";
        mary.phones[1] = "444-4444";
        print(mary, "Mary");
        team[3].firstName = mary.firstName;
        team[3].lastName = mary.lastName;
        team[3].age = mary.age;
        team[3].address[2].street = mary.address[2].street;
        team[3].address[2].city = mary.address[2].city;
        team[3].address[2].state = mary.address[2].state;
        team[3].address[2].zip = mary.address[2].zip;
        team[3].phones[0] = mary.phones[0];
        team[3].phones[1] = mary.phones[1];
        print(team[3], "team[3]");
        dept[2][3].firstName = john.firstName;
        dept[2][3].lastName = john.lastName;
        dept[2][3].age = john.age;
        dept[2][3].address[2].street = john.address[2].street;
        dept[2][3].address[2].city = john.address[2].city;
        dept[2][3].address[2].state = john.address[2].state;
        dept[2][3].address[2].zip = john.address[2].zip;
        dept[2][3].phones[0] = john.phones[0];
        dept[2][3].phones[1] = john.phones[1];
        print(dept[2][3], "dept[2, 3]");

        java.time.Instant _end = java.time.Instant.now();
        long _elapsed = java.time.Duration.between(_start, _end).toMillis();
        System.out.printf("\n[%,d milliseconds execution time.]\n", _elapsed);
    }
}
