package com.bluemsun.entity;

import lombok.Data;

import java.util.List;

@Data
public class History {

   private Integer id;
   private String title;
   private List<String> part;
}
