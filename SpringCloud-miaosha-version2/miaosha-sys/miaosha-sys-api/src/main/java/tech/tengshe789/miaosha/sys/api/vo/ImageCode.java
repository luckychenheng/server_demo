package tech.tengshe789.miaosha.sys.api.vo;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @author lengleng
 */
@Data
public class ImageCode implements Serializable {
	private String code;

	private LocalDateTime expireTime;

	private BufferedImage image;

	public ImageCode(BufferedImage image, String sRand, int defaultImageExpire) {
		this.image = image;
		this.code = sRand;
		this.expireTime = LocalDateTime.now().plusSeconds(defaultImageExpire);
	}
}
