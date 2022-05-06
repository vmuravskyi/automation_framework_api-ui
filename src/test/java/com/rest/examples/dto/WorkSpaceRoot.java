package com.rest.examples.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL) // when building an object include only fields which are not null
public class WorkSpaceRoot {

    @JsonProperty("workspace")
    private WorkspaceDto workspace;

    public WorkSpaceRoot() {
        // default constructor
    }

    public WorkSpaceRoot(WorkspaceDto workspaceDto) {
        this.workspace = workspaceDto;
    }

    public WorkspaceDto getWorkspace() {
        return workspace;
    }

    public void setWorkspace(WorkspaceDto workspace) {
        this.workspace = workspace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WorkSpaceRoot that = (WorkSpaceRoot) o;

        return new EqualsBuilder().append(workspace, that.workspace).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(workspace).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("workspace", workspace)
            .toString();
    }
}
