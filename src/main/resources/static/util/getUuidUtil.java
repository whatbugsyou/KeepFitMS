
public class getUuidUtil {
	//获取16位
	public static String getUUID_16() {
		int machineId = 1;// 最大支持1-9个集群机器部署
 
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {// 有可能是负数
			hashCodeV = -hashCodeV;
		}
		String string = machineId + String.format("%015d", hashCodeV);
		return string;
	}
	
	//获取8位
	public static String getUUID_8() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();
 
	}

}
