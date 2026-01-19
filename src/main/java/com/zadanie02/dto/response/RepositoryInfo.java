package com.zadanie02.dto.response;

import java.util.List;

public record RepositoryInfo(String name,String ownerLogin ,List<BranchInfo> branches) {
}
