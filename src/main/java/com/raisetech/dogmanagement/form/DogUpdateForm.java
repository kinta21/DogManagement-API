package com.raisetech.dogmanagement.form;

import com.raisetech.dogmanagement.entity.DogSex;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DogUpdateForm {

    @NotBlank
    @Size(min = 1, max = 30, message="30文字以内で入力してください。")
    private String name;

    @NotBlank
    private DogSex dogSex;

    @NotBlank
    private String age;

    @NotBlank
    @Size(min = 1, max = 30, message="30文字以内で入力してください。")
    private String dogBreed;

    @NotBlank
    private String region;
}

