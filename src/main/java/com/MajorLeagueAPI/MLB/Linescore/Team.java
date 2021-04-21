package com.MajorLeagueAPI.MLB.Linescore;

public class Team {
   private int runs, hits, errors, leftOnBase;
   private String name;
   private int id;

   public int getRuns() {
      return runs;
   }

   public void setRuns(int runs) {
      this.runs = runs;
   }

   public int getHits() {
      return hits;
   }

   public void setHits(int hits) {
      this.hits = hits;
   }

   public int getErrors() {
      return errors;
   }

   public void setErrors(int errors) {
      this.errors = errors;
   }

   public int getLeftOnBase() {
      return leftOnBase;
   }

   public void setLeftOnBase(int leftOnBase) {
      this.leftOnBase = leftOnBase;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }
}
