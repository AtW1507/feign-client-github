package com.zadanie02;

import java.util.List;

public record RepositoryInfo(String name,String ownerLogin ,List<BranchInfo> branches) {
}
