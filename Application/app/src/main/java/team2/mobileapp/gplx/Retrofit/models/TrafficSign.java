package team2.mobileapp.gplx.Retrofit.models;

public class TrafficSign {
    private String id;
    private String code;
    private String name;
    private String description;
    private String photo;
    private String trafficSignType;

    public TrafficSign() {
    }

    public TrafficSign(String id, String code, String name, String description, String photo, String trafficSignType) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.trafficSignType = trafficSignType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTrafficSignType() {
        return trafficSignType;
    }

    public void setTrafficSignType(String trafficSignType) {
        this.trafficSignType = trafficSignType;
    }

    @Override
    public String toString() {
        return "TrafficSign{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", photo='" + photo + '\'' +
                ", trafficSignType='" + trafficSignType + '\'' +
                '}';
    }
}
