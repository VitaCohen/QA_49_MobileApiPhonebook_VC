package dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class Contact {
   private  String id;
   private  String name;
   private  String lastName;
   private  String email;
   private  String phone;
   private  String address;
   private  String descriptions;

}
