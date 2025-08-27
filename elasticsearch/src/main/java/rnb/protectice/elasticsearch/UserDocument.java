package rnb.protectice.elasticsearch;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

// 인덱스의 정보 기입
// mapping에 대한 정의를 코드로 작성
@Document(indexName = "users")
public class UserDocument {

    @Id
    private String id; // Elasticsearch에서의 ID값은 String
    @Field(type = FieldType.Keyword)
    private String name;
    @Field(type = FieldType.Long)
    private Long age;
    @Field(type = FieldType.Boolean)
    private Boolean isActive;

    public UserDocument(String id, String name, Long age, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "UserDocument{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isActive=" + isActive +
                '}';
    }
}
