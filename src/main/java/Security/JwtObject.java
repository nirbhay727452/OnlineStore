package Security;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
public class JwtObject {
    private String email;
    private long UserID;
    private Date expiryAt;
    private Date createdAt;
    private List<Role> roles;
}
