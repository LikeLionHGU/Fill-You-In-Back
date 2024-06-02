package com.likelion.fillyouinback.folder.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddFolderRequest {
    private String name;
    private Long categoryId;
}
