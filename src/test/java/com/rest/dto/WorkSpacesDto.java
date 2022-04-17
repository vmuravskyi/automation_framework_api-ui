package com.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.selenium.pom.utils.FakerUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class WorkSpacesDto {

    @JsonProperty("workspaces")
    private List<WorkspaceDto> workspaces;

    public WorkSpacesDto() {
        // default constructor
    }

    public List<WorkspaceDto> getWorkspaces() {
        return workspaces;
    }

    public WorkSpacesDto setWorkspaces(List<WorkspaceDto> workspaces) {
        this.workspaces = workspaces;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WorkSpacesDto that = (WorkSpacesDto) o;

        return new EqualsBuilder()
            .append(workspaces, that.workspaces)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(workspaces)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("workspaces", workspaces)
            .toString();
    }

    public List<WorkspaceDto> generateRandomListOfWorkspace() {
        List<WorkspaceDto> list = new ArrayList<>();
        IntStream.range(0, new FakerUtils().generateRandomNumber(1).intValue())
            .forEach(value -> list.add(new WorkspaceDto().generateRandomWorkspace()));
        return list;
    }
}
