package rnb.protectice.elasticsearch;


public class UserCreateRequestDTO {
    private String id;
    private String name;
    private Long age;
    private Boolean isActive;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getAge() {
        return age;
    }

    public Boolean getIsActive() {
        return isActive;
    }
}
