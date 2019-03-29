package softuni.residentevil.domain.entities;

import java.util.List;

public enum Mutation {
  ZOMBIE("ZOMBIE"), T_078_TYRANT("T_078_TYRANT"), GIANT_SPIDER("GIANT_SPIDER");

  private String name;

  Mutation(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
