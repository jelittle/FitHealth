package Database;


import userData.User;

interface UserInterface {
    public User getUserById(int id);
    public void setUser(User user);
    public void updateUser(User user);
    public User getUserByUserName(String userName);
    public boolean checkforExistingUsername(String userName);
    public void deleteUser(User user);
}
