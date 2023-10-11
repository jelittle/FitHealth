public class User{
    private String name;
    private String password;
    private double height;
    private double weight;
    private int age; 
    private int ID;

    public User(){
        name = "";
        password = "";
        height = 0;
        weight = 0;
        age = 0;
        ID = 0;
    }

    public User(String name){
        this.name = name;
        password = "";
        height = 0;
        weight = 0;
        age = 0;
        ID = 0;
    }

    public User(String name, String password, double height, double weight, int age, int ID){
        this.name = name;
        this.password = password;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.ID = ID;
    }


    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }

    public void setHeight(double height){
        this.height = height;
    }
    public double getHeight(){
        return height;
    }

    public void setWeight(double weight){
        this.weight = weight;
    }
    public double getWeight(){
        return weight;
    }

    public void setAge(int age){
        this.age = age;
    }
    public int getAge(){
        return age;
    }

    public void setID(int ID){
        this.ID = ID;
    }
    public int getID(){
        return ID;
    }
}