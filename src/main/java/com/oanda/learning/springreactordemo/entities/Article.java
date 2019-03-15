package com.oanda.learning.springreactordemo.entities;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("articles")
//@ApiModel(value = "Article", description = "A Real life News Article")
public class Article {
    @Id
//    @ApiModelProperty(value = "Numerical ID/Identifier for an Article", example = "1")
    private Long id;
//    @ApiModelProperty(value = "Text content for an Article", example = "This is an example article for our newsletter")
    private String article;
}
