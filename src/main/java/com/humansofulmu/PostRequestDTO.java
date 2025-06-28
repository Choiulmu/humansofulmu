package com.humansofulmu;

import com.humansofulmu.entity.Post;

public record PostRequestDTO(
        String name,
        String content
) {

    public Post toEntity() {
        return Post.builder()
                .user(this.name)
                .content(this.content)
                .build();
    }
}
