package dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ErrorMessageDto {

    private String timestamp;
    private int status;
    private  String error;
    private String message;
    private String path;


}
