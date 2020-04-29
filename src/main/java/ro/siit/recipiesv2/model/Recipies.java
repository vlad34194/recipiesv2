package ro.siit.recipiesv2.model;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;


@Entity
public class Recipies {
    @Id
    @GeneratedValue
    private Long id;
    @Size(min = 6, message = "Must contains minimum 6 characters")
    @NotEmpty
    public String recipieName;
    @Size(min = 20,message = "Must contains minimum 20 characters")
    @NotEmpty
    public String shortDescription;
    @Size(min = 20,message = "Must contains minimum 20 characters")
    @NotEmpty
    public String ingredients;
    @Size(min = 20,message = "Must contains minimum 20 characters")
    @NotEmpty
    public String directions;
    public Category category;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateCreated;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @UpdateTimestamp
    private LocalDate lastModified;

    @Lob
    @NotEmpty
    private byte[] image;

    public Recipies() {
    }

    public Recipies(String recipieName, String shortDescription, String ingredients, String directions, LocalDate dateCreated, Category category, LocalDate lastModified) {
        this.recipieName = recipieName.trim();
        this.shortDescription = shortDescription.trim();
        this.ingredients = ingredients.trim();
        this.directions = directions.trim();
        this.dateCreated = dateCreated;
        this.category = category;
        this.lastModified = lastModified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getrecipieName() {
        return recipieName;
    }

    public void setrecipieName(String recipieName) {
        this.recipieName = recipieName.trim();
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription.trim();
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients.trim();
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions.trim();
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }

    public String getImage() {
        if (image != null) {
            try {
                return new String(Base64.encodeBase64(image), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void setImage(MultipartFile image) {
        try {
            this.image = image.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}
