package opencv.domain.image;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Images extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String imagePath;

    @Column(length = 500, nullable = false)
    private String imageName;

    @Column(length = 500, nullable = false)
    private String originalImageName;

    @Builder
    public Images (String imagePath, String imageName, String originalImageName) {
        this.imagePath = imagePath;
        this.imageName = imageName;
        this.originalImageName = originalImageName;
    }
}
