package Project;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import com.google.gson.Gson;

public class Main {
	public static ElectricPowerList getListInfo(int page, int perPage) {
		try {
			URL url = new URL("https://api.odcloud.kr/api/15102459/v1/uddi:e135c06b-b9f1-4fc5-95da-f000da38638b"
					+ "?page=" +page + "&perPage=" +perPage
					+ "&returnType=JSON&serviceKey=R1jof6NgKLyzzQUX349d9TdjNCYOlItxebh4QimXxAtbsQPVbj%2FtUZAyysKajJuX3e4z1tEAEmo3BN2a6s3S0Q%3D%3D");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),
					"UTF-8"));
			StringBuilder sb = new StringBuilder();
			String input = "";
			while ((input = br.readLine()) != null) {
				sb.append(input);
				}
			br.close();
			con.disconnect();
			Gson gson =new Gson();
			ElectricPowerList power = gson.fromJson(sb.toString(),
					ElectricPowerList.class);
			return power;
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return null;
		
	}
	public static int getTotalCount() {
		return getListInfo(1, 1).getTotalCount();
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int totalCount = getTotalCount();
		System.out.println("현제 전국에 전기차 충전소는 " + totalCount + "개 있습니다");
		
		ElectricPowerList power = getListInfo(1, totalCount);
		
		Scanner s = new Scanner(System.in);
		while (true) {
			System.out.print("\n시명 또는 지역 입력(X 종료): ");
			String str = s.nextLine();
			if (str.equals("X"))
				break;
			
			System.out.println("=========================");
			for(Datum item : power.getData()) {
				if (item.get본부().contains(str)|| item.get사업소().contains(str) ||
				item.get충전소명().contains(str)){
					System.out.println("본부:" + item.get본부());
					System.out.println("사업소:" + item.get사업소());
					System.out.println("충전소명:" + item.get충전소명());
					System.out.println("충전기ID:" + item.get충전기ID());
					System.out.println("충전기용량:" + item.get충전기용량());
					System.out.println("충전기명:" + item.get충전기명());
					System.out.println("충전소구분:" + item.get충전소구분());
					System.out.println("충전방식:" + item.get충전방식());
					
	}
}
}
		System.out.println("종료되었습니다!");
	}
}
