package userData;

public class User{
    private String name;
    private String password;
    private String sex;
    private int height;
    private float weight;
    private int age; 
    private int id;
    private ExerciseLogs exerciseLogs;
    private DietLogs dietLogs;

    public User(){
        name = "";
        password = "";
        sex = "";
        height = 0;
        weight = 0;
        age = 0;
        id = 0;
    }

    public User(String name){
        this.name = name;
        password = "";
        sex = "";
        height = 0;
        weight = 0;
        age = 0;
        id = 0;
    }

    public User(String name, String password, int height, float weight, int age, int id){
        this.name = name;
        this.password = password;
        sex = "";
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.id = id;
    }

    public User(String name, String password, String sex, int height, float weight, int age, int id){
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.id = id;
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

    public void setSex(String sex){
        this.sex = sex;
    }
    public String getSex(){
        return sex;
    }

    public void setHeight(int height){
        this.height = height;
    }
    public int getHeight(){
        return height;
    }

    public void setWeight(float weight){
        this.weight = weight;
    }
    public float getWeight(){
        return weight;
    }

    public void setAge(int age){
        this.age = age;
    }
    public int getAge(){
        return age;
    }

    public void setID(int id){
        this.id = id;
    }
    public int getID(){
        return id;
    }

//    public void addExercise(ExerciseLog exerciseLog){ this.exerciseLogs}

}