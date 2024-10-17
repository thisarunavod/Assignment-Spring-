package lk.ijse.multishop.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class OrderDetailPK implements Serializable {

    @Column(name = "orderId")
    private String orderId;
    @Column(name = "id")
    private String itemId;

}
