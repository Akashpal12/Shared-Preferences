package com.example.myapiaplication;

import java.util.Objects;

public class OwnersModel {
    private String name;
    private String ownerName;
    private String ownerNumber;

  /*  @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnersModel that = (OwnersModel) o;
        return name == that.name;// && DATE.equals(that.DATE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                name//, DATE
        );
    }
*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerNumber() {
        return ownerNumber;
    }

    public void setOwnerNumber(String ownerNumber) {
        this.ownerNumber = ownerNumber;
    }
}
