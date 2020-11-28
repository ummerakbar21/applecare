package applecare.com.applecare.Model;

public class SignUpUser {
    private String email;
    private String name;
    private String password;
    private String district;

    public SignUpUser() {
    }

    public SignUpUser(String email, String name, String password, String district) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.district = district;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
