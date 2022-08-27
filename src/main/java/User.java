public class User implements java.io.Serializable{
    private String name;
    private int age;
    Credentials creds;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", creds=" + creds +
                '}';
    }

    public User (String name, int age, Credentials creds){
        this.name = name;
        this.age = age;
        this.creds = creds;
    }
}
