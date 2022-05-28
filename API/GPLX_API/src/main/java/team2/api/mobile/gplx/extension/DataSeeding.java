package team2.api.mobile.gplx.extension;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import team2.api.mobile.gplx.models.Account;
import team2.api.mobile.gplx.models.AccountStatus;
import team2.api.mobile.gplx.models.Answer;
import team2.api.mobile.gplx.models.License;
import team2.api.mobile.gplx.models.LicenseType;
import team2.api.mobile.gplx.models.Question;
import team2.api.mobile.gplx.models.QuestionSet;
import team2.api.mobile.gplx.models.QuestionType;
import team2.api.mobile.gplx.models.Role;
import team2.api.mobile.gplx.models.Status;
import team2.api.mobile.gplx.models.TrafficSign;
import team2.api.mobile.gplx.models.TrafficSignType;
import team2.api.mobile.gplx.repository.AccountRepository;
import team2.api.mobile.gplx.repository.AnswerRepository;
import team2.api.mobile.gplx.repository.LicenseRepository;
import team2.api.mobile.gplx.repository.LicenseTypeRepository;
import team2.api.mobile.gplx.repository.QuestionRepository;
import team2.api.mobile.gplx.repository.QuestionSetRepository;
import team2.api.mobile.gplx.repository.QuestionTypeRepository;
import team2.api.mobile.gplx.repository.RoleRepository;
import team2.api.mobile.gplx.repository.TrafficSignRepository;
import team2.api.mobile.gplx.repository.TrafficSignTypeRepository;

@Component
@AllArgsConstructor
public class DataSeeding implements CommandLineRunner {
	
	@Autowired
	private LicenseTypeRepository licenseTypeRepo;
	@Autowired
	private LicenseRepository licenseRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private AccountRepository accountRepo;
	@Autowired
	private TrafficSignTypeRepository trafficSignTypeRepo;
	@Autowired
	private TrafficSignRepository trafficSignRepo;
	@Autowired
	private QuestionTypeRepository questionTypeRepo;
	@Autowired
	private QuestionRepository questionRepo;
	@Autowired
	private QuestionSetRepository questionSetRepo;
	@Autowired
	private AnswerRepository answerRepo;
	
	@Override
	public void run(String... args) throws Exception {
		LoadRole();
		LoadAccount();
		LoadLicenseType();
		LoadLicense();
		LoadTrafficSignType();
		LoadTrafficSign();
		LoadQuestionSet();
		LoadQuestionType();
		LoadQuestion();
		LoadAnswer();
		
	}
	
	private void LoadQuestionSet() {
		if(questionSetRepo.count() == 0) {
			for(int c = 1; c <= 8; c++) {
				QuestionSet set = new QuestionSet("Đề " + c, false, 25, 0,0);
				questionSetRepo.save(set);
			}
		}
		
	}
	
	private void LoadAnswer() {
		// Đề 1
		List<Question> questionList = questionRepo.findAll();
		ArrayList<String> idList = new ArrayList<String>();
		
		for(Question q : questionList) {
			idList.add(q.getId());
		}
		
		answerRepo.deleteAll();
//		if(answerRepo.count() == 0 && idList.size() > 0) {
//			String[] answers_1_set1 = {"Phần mặt đường và lề đường", "Phần đường xe chạy", "Phần đường xe cơ giới"};
//			Answer answer_1_set1 = new Answer(answers_1_set1, 1, idList.get(0));
//			answerRepo.save(answer_1_set1);
//		}
		
		String[] answers_1_set1 = {"Phần mặt đường và lề đường", "Phần đường xe chạy", "Phần đường xe cơ giới"};
		Answer answer_1_set1 = new Answer(answers_1_set1, 1, idList.get(0));
		answerRepo.save(answer_1_set1);

		String[] answers_2_set1 = {"Phương tiện giao thông cơ giới đường bộ", "Phương tiện giao thông thô sơ đường bộ và xe máy chuyên dùng", "Cả ý 1 và ý 2"};
		Answer answer_2_set1 = new Answer(answers_2_set1, 2, idList.get(1));
		answerRepo.save(answer_2_set1);

		String[] answers_3_set1 = {"Chỉ bị nhắc nhở", "Bị xử phạt hành chính hoặc có thể bị xử lý hình sự tùy theo mức độ vi phạm", "Không bị xử lý hình sự"};
		Answer answer_3_set1 = new Answer(answers_3_set1, 1, idList.get(2));
		answerRepo.save(answer_3_set1);

		String[] answers_4_set1 = {"Không được vượt", "Được vượt khi đang đi trên cầu", "Được phép vượt khi đi qua nơi giao nhau có ít phương tiện cùng tham gia  giao thông", "Được vượt khi đảm bảo an toàn"};
		Answer answer_4_set1 = new Answer(answers_4_set1, 0, idList.get(3));
		answerRepo.save(answer_4_set1);

		String[] answers_5_set1 = {"Chỉ được kéo nếu đã nhìn thấy trạm xăng", "Chỉ được thực hiện trên đường vắng phương tiện cùng tham gia giao thông", "Không được phép"};
		Answer answer_5_set1 = new Answer(answers_5_set1, 2, idList.get(4));
		answerRepo.save(answer_5_set1);

		String[] answers_6_set1 = {"Biển báo nguy hiểm", "Biển báo cấm", "Biển báo hiệu lệnh phải thi hành", "Biển báo chỉ dẫn"};
		Answer answer_6_set1 = new Answer(answers_6_set1, 2, idList.get(5));
		answerRepo.save(answer_6_set1);

		String[] answers_7_set1 = {"Phải báo hiệu bằng đèn hoặc còi", "Chỉ được báo hiệu bằng còi", "Phải báo hiệu bằng cả còi và đèn", "Chỉ được báo hiệu bằng đèn"};
		Answer answer_7_set1 = new Answer(answers_7_set1, 3, idList.get(6));
		answerRepo.save(answer_7_set1);

		String[] answers_8_set1 = {"Xe cơ giới, xe máy chuyên dùng phải bật đèn; xe thô sơ phải bật đèn hoặc có vật phát sáng báo hiệu; chỉ được dừng xe, đỗ xe ở nơi quy định", "Xe cơ giới phải bật đèn ngay cả khi đường hầm sáng; phải cho xe chạy trên một làn đường và chỉ chuyển làn ở nơi được phép; được quay đầu xe, lùi xe khi cần thiết", "Xe máy chuyên dùng phải bật đèn ngay cả khi đường hầm sáng; phải cho xe chạy trên một làn đường và chỉ chuyển làn ở nơi được phép; được quay đầu xe, lùi xe khi cần thiết"};
		Answer answer_8_set1 = new Answer(answers_8_set1, 0, idList.get(7));
		answerRepo.save(answer_8_set1);

		String[] answers_9_set1 = {"Xe bị vượt bất ngờ tăng tốc độ và cố tình không nhường đường", "Xe bị vượt giảm tốc độ và nhường đường", "Phát hiện có xe đi ngược chiều", "Cả ý 1 và ý 3"};
		Answer answer_9_set1 = new Answer(answers_9_set1, 3, idList.get(8));
		answerRepo.save(answer_9_set1);

		String[] answers_10_set1 = {"Chủ động giữ khoảng cách an toàn phù hợp với xe chạy liền trước xe của mình", "Đảm bảo khoảng cách an toàn theo mật độ phương tiện, tình hình giao thông thực tế", "Cả ý 1 và ý 2"};
		Answer answer_10_set1 = new Answer(answers_10_set1, 2, idList.get(9));
		answerRepo.save(answer_10_set1);

		String[] answers_11_set1 = {"Ra tín hiệu bằng tay rồi cho xe vượt qua", "Tăng ga mạnh để gây sự chú ý rồi cho xe vượt qua", "Bạn phải có tín hiệu bằng đèn hoặc còi"};
		Answer answer_11_set1 = new Answer(answers_11_set1, 2, idList.get(10));
		answerRepo.save(answer_11_set1);

		String[] answers_12_set1 = {"Giữ tay ga ở mức độ phù hợp, sử dụng phanh trước và phanh sau để giảm tốc độ", "Nhả hết tay ga, tắt động cơ, sử dụng phanh trước và phanh sau để giảm tốc độ", "Sử dụng phanh trước để giảm tốc độ kết hợp với tắt chìa khóa điện của xe"};
		Answer answer_12_set1 = new Answer(answers_12_set1, 0, idList.get(11));
		answerRepo.save(answer_12_set1);

		String[] answers_13_set1 = {"Để điều khiển xe chạy về phía trước", "Để điều tiết công suất động cơ qua đó điều khiển tốc độ của xe", "Để điều khiển xe chạy lùi", "Cả ý 1 và ý 2"};
		Answer answer_13_set1 = new Answer(answers_13_set1, 3, idList.get(12));
		answerRepo.save(answer_13_set1);

		String[] answers_14_set1 = {"Biển 1.", "Biển 2.", "Cả hai biển."};
		Answer answer_14_set1 = new Answer(answers_14_set1, 0, idList.get(13));
		answerRepo.save(answer_14_set1);

		String[] answers_15_set1 = {"Biển 1.", "Biển 2.", "Biển 1 và 2."};
		Answer answer_15_set1 = new Answer(answers_15_set1, 2, idList.get(14));
		answerRepo.save(answer_15_set1);

		String[] answers_16_set1 = {"Biển 1.", "Biển 2.", "Biển 3."};
		Answer answer_16_set1 = new Answer(answers_16_set1, 1, idList.get(15));
		answerRepo.save(answer_16_set1);

		String[] answers_17_set1 = {"Biển 1.", "Biển 2.", "Biển 3.", "Cả ba biển."};
		Answer answer_17_set1 = new Answer(answers_17_set1, 2, idList.get(16));
		answerRepo.save(answer_17_set1);

		String[] answers_18_set1 = {"Biển 1.", "Biển 2.", "Biển 3."};
		Answer answer_18_set1 = new Answer(answers_18_set1, 0, idList.get(17));
		answerRepo.save(answer_18_set1);

		String[] answers_19_set1 = {"Biển 1", "Biển 2", "Biển 3."};
		Answer answer_19_set1 = new Answer(answers_19_set1, 1, idList.get(18));
		answerRepo.save(answer_19_set1);

		String[] answers_20_set1 = {"Biển 1", "Biển 2", "Biển 3", "Biển 2 và 3."};
		Answer answer_20_set1 = new Answer(answers_20_set1, 3, idList.get(19));
		answerRepo.save(answer_20_set1);

		String[] answers_21_set1 = {"Vạch 1", "Vạch 2", "Vạch 3", "Cả 3 vạch."};
		Answer answer_21_set1 = new Answer(answers_21_set1, 1, idList.get(20));
		answerRepo.save(answer_21_set1);

		String[] answers_22_set1 = {"Xe mô tô", "Xe cứu thương."};
		Answer answer_22_set1 = new Answer(answers_22_set1, 1, idList.get(21));
		answerRepo.save(answer_22_set1);

		String[] answers_23_set1 = {"Đúng", "Không đúng."};
		Answer answer_23_set1 = new Answer(answers_23_set1, 1, idList.get(22));
		answerRepo.save(answer_23_set1);

		String[] answers_24_set1 = {"Cả ba hướng", "Hướng 1 và 2", "Hướng 1 và 3", "Hướng 2 và 3."};
		Answer answer_24_set1 = new Answer(answers_24_set1, 2, idList.get(23));
		answerRepo.save(answer_24_set1);

		String[] answers_25_set1 = {"Xe con, xe tải, xe khách", "Xe tải, xe khách, xe mô tô", "Xe khách, xe mô tô, xe con", "Cả bốn xe."};
		Answer answer_25_set1 = new Answer(answers_25_set1, 1, idList.get(24));
		answerRepo.save(answer_25_set1);

		String[] answers_1_set2 = {"Là một phần của phần đường xe chạy được chia theo chiều dọc của đường, sử dụng cho xe chạy", "Là một phần của phần đường xe chạy được chia theo chiều dọc của đường, có bề rộng đủ cho xe chạy an toàn", "Là đường cho xe ô tô chạy, dừng, đỗ an toàn"};
		Answer answer_1_set2 = new Answer(answers_1_set2, 1, idList.get(25));
		answerRepo.save(answer_1_set2);

		String[] answers_2_set2 = {"Người điều khiển, người sử dụng phương tiện tham gia giao thông đường bộ", "Người điều khiển, dẫn dắt súc vật; người đi bộ trên đường bộ", "Cả ý 1 và ý 2"};
		Answer answer_2_set2 = new Answer(answers_2_set2, 2, idList.get(26));
		answerRepo.save(answer_2_set2);

		String[] answers_3_set2 = {"Người điều khiển: Xe ô tô, xe mô tô, xe đạp, xe gắn máy", "Người ngồi phía sau người điều khiển xe cơ giới", "Người đi bộ", "Cả ý 1 và ý 2"};
		Answer answer_3_set2 = new Answer(answers_3_set2, 0, idList.get(27));
		answerRepo.save(answer_3_set2);

		String[] answers_4_set2 = {"Được phép", "Nếu phương tiện được kéo, đẩy có khối lượng nhỏ hơn phương tiện của mình", "Tuỳ trường hợp", "Không được phép"};
		Answer answer_4_set2 = new Answer(answers_4_set2, 3, idList.get(28));
		answerRepo.save(answer_4_set2);

		String[] answers_5_set2 = {"Không được vận chuyển", "Chỉ được vận chuyển khi đã chằng buộc cẩn thận", "Chỉ được vận chuyển vật cồng kềnh trên xe máy nếu khoảng cách về nhà ngắn hơn 2 km"};
		Answer answer_5_set2 = new Answer(answers_5_set2, 0, idList.get(29));
		answerRepo.save(answer_5_set2);

		String[] answers_6_set2 = {"Biển báo nguy hiểm", "Biển báo cấm", "Biển báo hiệu lệnh phải thi hành", "Biển báo chỉ dẫn"};
		Answer answer_6_set2 = new Answer(answers_6_set2, 3, idList.get(30));
		answerRepo.save(answer_6_set2);

		String[] answers_7_set2 = {"Tăng tốc độ và ra hiệu cho xe sau vượt, không được gây trở ngại cho xe sau vượt", "Người điều khiển phương tiện phía trước phải giảm tốc độ, đi sát về bên phải của phần đường xe chạy cho đến khi xe sau đã vượt qua, không được gây trở ngại cho xe sau vượt", "Cho xe tránh về bên trái mình và ra hiệu cho xe sau vượt; nếu có chướng ngại vật phía trước hoặc thiếu điều kiện an toàn chưa cho vượt được phải ra hiệu cho xe sau biết; cấm gây trở ngại cho xe xin vượt"};
		Answer answer_7_set2 = new Answer(answers_7_set2, 1, idList.get(31));
		answerRepo.save(answer_7_set2);

		String[] answers_8_set2 = {"Khi tham gia giao thông đường bộ", "Chỉ khi đi trên đường chuyên dùng; đường cao tốc", "Khi tham gia giao thông trên đường tỉnh lộ hoặc quốc lộ"};
		Answer answer_8_set2 = new Answer(answers_8_set2, 0, idList.get(32));
		answerRepo.save(answer_8_set2);

		String[] answers_9_set2 = {"Nếu đủ điều kiện an toàn, người lái xe phải giảm tốc độ, đi sát về bên phải của phần đường xe chạy cho đến khi xe sau đã vượt qua, không được gây trở ngại đối với xe xin vượt", "Lái xe vào lề đường bên trái và giảm tốc độ để xe phía sau vượt qua, không được gây trở ngại đối với xe xin vượt", "Nếu đủ điều kiện an toàn, người lái xe phải tăng tốc độ, đi sát về bên phải của phần đường xe chạy cho đến khi xe sau đã vượt qua"};
		Answer answer_9_set2 = new Answer(answers_9_set2, 0, idList.get(33));
		answerRepo.save(answer_9_set2);

		String[] answers_10_set2 = {"Gặp biển báo nguy hiểm trên đường", "Gặp biển chỉ dẫn trên đường", "Gặp biển báo hết mọi lệnh cấ"};
		Answer answer_10_set2 = new Answer(answers_10_set2, 0, idList.get(34));
		answerRepo.save(answer_10_set2);

		String[] answers_11_set2 = {"Đường ướt, đường có sỏi cát trên nền đường", "Đường hẹp có nhiều điểm giao cắt từ hai phí", "Đường đèo dốc, vòng liên tục", "Tất cả các ý nêu trên"};
		Answer answer_11_set2 = new Answer(answers_11_set2, 3, idList.get(35));
		answerRepo.save(answer_11_set2);

		String[] answers_12_set2 = {"Quan sát biển báo hiệu để biết nơi được phép quay đầu; quan sát kỹ địa hình nơi chọn để quay đầu; lựa chọn quỹ đạo quay đầu xe cho thích hợp; quay đầu xe với tốc độ thấp; thường xuyên báo tín hiệu để người, các phương tiện xung quanh được biết; nếu quay đầu xe ở nơi nguy hiểm thì đưa đầu xe về phía nguy hiểm đưa đuôi xe về phía an toàn", "Quan sát biển báo hiệu để biết nơi được phép quay đầu; quan sát kỹ địa hình nơi chọn để quay đầu; lựa chọn quỹ đạo quay đầu xe; quay đầu xe với tốc độ tối đa; thường xuyên báo tín hiệu để người, các phương tiện xung quanh được biết; nếu quay đầu xe ở nơi nguy hiểm thì đưa đuôi xe về phía nguy hiểm và đầu xe về phía an toàn"};
		Answer answer_12_set2 = new Answer(answers_12_set2, 0, idList.get(36));
		answerRepo.save(answer_12_set2);

		String[] answers_13_set2 = {"Để quan sát an toàn phía bên trái khi chuẩn bị rẽ trái", "Để quan sát an toàn phía bên phải khi chuẩn bị rẽ phải", "Để quan sát an toàn phía sau cả bên trái và bên phải trước khi chuyển hướng", "Để quan sát an toàn phía trước cả bên trái và bên phải trước khi chuyển hướng"};
		Answer answer_13_set2 = new Answer(answers_13_set2, 2, idList.get(37));
		answerRepo.save(answer_13_set2);

		String[] answers_14_set2 = {"Biển 1.", "Biển 2.", "Không biển nào."};
		Answer answer_14_set2 = new Answer(answers_14_set2, 1, idList.get(38));
		answerRepo.save(answer_14_set2);

		String[] answers_15_set2 = {"Biển 1.", "Biển 2.", "Cả ba biển."};
		Answer answer_15_set2 = new Answer(answers_15_set2, 1, idList.get(39));
		answerRepo.save(answer_15_set2);

		String[] answers_16_set2 = {"Biển 1.", "Biển 2."};
		Answer answer_16_set2 = new Answer(answers_16_set2, 0, idList.get(40));
		answerRepo.save(answer_16_set2);

		String[] answers_17_set2 = {"Biển 1 và 2.", "Biển 1 và 3.", "Biển 2 và 3.", "Cả ba biển."};
		Answer answer_17_set2 = new Answer(answers_17_set2, 2, idList.get(41));
		answerRepo.save(answer_17_set2);

		String[] answers_18_set2 = {"Biển 1.", "Biển 2.", "Biển 3."};
		Answer answer_18_set2 = new Answer(answers_18_set2, 1, idList.get(42));
		answerRepo.save(answer_18_set2);

		String[] answers_19_set2 = {"Báo hiệu đường có ổ gà, lồi lõm.", "Báo hiệu đường có gồ giảm tốc phía trước."};
		Answer answer_19_set2 = new Answer(answers_19_set2, 1, idList.get(43));
		answerRepo.save(answer_19_set2);

		String[] answers_20_set2 = {"Biển 1", "Biển 2", "Biển 3", "Cả ba biển."};
		Answer answer_20_set2 = new Answer(answers_20_set2, 1, idList.get(44));
		answerRepo.save(answer_20_set2);

		String[] answers_21_set2 = {"Vạch 1", "Vạch 2", "Vạch 3", "Vạch 1 và 3."};
		Answer answer_21_set2 = new Answer(answers_21_set2, 3, idList.get(45));
		answerRepo.save(answer_21_set2);

		String[] answers_22_set2 = {"Xe con và xe khách", "Mô tô."};
		Answer answer_22_set2 = new Answer(answers_22_set2, 0, idList.get(46));
		answerRepo.save(answer_22_set2);

		String[] answers_23_set2 = {"Xe con", "Xe mô tô"};
		Answer answer_23_set2 = new Answer(answers_23_set2, 1, idList.get(47));
		answerRepo.save(answer_23_set2);

		String[] answers_24_set2 = {"Xe công an, xe quân sự, xe con + mô tô", "Xe quân sự, xe công an, xe con + mô tô", "Xe mô tô + xe con, xe quân sự, xe công an."};
		Answer answer_24_set2 = new Answer(answers_24_set2, 1, idList.get(48));
		answerRepo.save(answer_24_set2);

		String[] answers_25_set2 = {"Xe tải, mô tô", "Xe khách, mô tô", "Xe tải, xe con", "Mô tô, xe con."};
		Answer answer_25_set2 = new Answer(answers_25_set2, 1, idList.get(49));
		answerRepo.save(answer_25_set2);

		String[] answers_1_set3 = {"Là bộ phận của đường để ngăn cách không cho các loại xe vào những nơi không được phép", "Là bộ phận của đường để phân tách phần đường xe chạy và hành lang an toàn giao thông", "Là bộ phận của đường để phân chia mặt đường thành hai chiều xe chạy riêng biệt hoặc để phân chia phần đường của xe cơ giới và xe thô sơ"};
		Answer answer_1_set3 = new Answer(answers_1_set3, 2, idList.get(50));
		answerRepo.save(answer_1_set3);

		String[] answers_2_set3 = {"Người điều khiển xe cơ giới, người điều khiển xe thô sơ", "Người điều khiển xe máy chuyên dùng tham gia giao thông đường bộ", "Cả ý 1 và ý 2"};
		Answer answer_2_set3 = new Answer(answers_2_set3, 2, idList.get(51));
		answerRepo.save(answer_2_set3);

		String[] answers_3_set3 = {"Bị nghiêm cấm tuỳ từng trường hợp", "Không bị nghiêm cấ", "Bị nghiêm cấm"};
		Answer answer_3_set3 = new Answer(answers_3_set3, 2, idList.get(52));
		answerRepo.save(answer_3_set3);

		String[] answers_4_set3 = {"Được phép", "Tuỳ trường hợp", "Không được phép"};
		Answer answer_4_set3 = new Answer(answers_4_set3, 2, idList.get(53));
		answerRepo.save(answer_4_set3);

		String[] answers_5_set3 = {"16 tuổi", "18 tuổi", "17 tuổi"};
		Answer answer_5_set3 = new Answer(answers_5_set3, 1, idList.get(54));
		answerRepo.save(answer_5_set3);

		String[] answers_6_set3 = {"02 năm", "03 năm", "05 năm", "04 năm"};
		Answer answer_6_set3 = new Answer(answers_6_set3, 2, idList.get(55));
		answerRepo.save(answer_6_set3);

		String[] answers_7_set3 = {"Quan sát gương, ra tín hiệu, quan sát an toàn và chuyển hướn", "Quan sát gương, giảm tốc độ, ra tín hiệu chuyển hướng, quan sát an toàn và chuyển hướng", "Quan sát gương, tăng tốc độ, ra tín hiệu và chuyển hướn"};
		Answer answer_7_set3 = new Answer(answers_7_set3, 1, idList.get(56));
		answerRepo.save(answer_7_set3);

		String[] answers_8_set3 = {"Chở người bệnh đi cấp cứu; trẻ em dưới 14 tuổi", "Áp giải người có hành vi vi phạm pháp luật", "Cả ý 1 và ý 2"};
		Answer answer_8_set3 = new Answer(answers_8_set3, 2, idList.get(57));
		answerRepo.save(answer_8_set3);

		String[] answers_9_set3 = {"Phải đội mũ bảo hiểm đạt chuẩn, có cài quai đúng quy cách, mặc quần áo gọn gàng; không sử dụng ô, điện thoại di động, thiết bị âm thanh (trừ thiết bị trợ thính)", "Phải đội mũ bảo hiểm khi trời mưa gió hoặc trời quá nắng; có thể sử dụng ô, điện thoại di động, thiết bị âm thanh nhưng phải đảm bảo an toàn", "Phải đội mũ bảo hiểm khi cảm thấy mất an toàn giao thông hoặc khi chuẩn bị di chuyển quãng đường xa"};
		Answer answer_9_set3 = new Answer(answers_9_set3, 0, idList.get(58));
		answerRepo.save(answer_9_set3);

		String[] answers_10_set3 = {"Hiệu lệnh của nhân viên gác chắn", "Đèn đỏ sáng nháy, cờ đỏ, biển đỏ", "Còi, chuông kêu, chắn đã đóng", "Tất cả các ý trên"};
		Answer answer_10_set3 = new Answer(answers_10_set3, 3, idList.get(59));
		answerRepo.save(answer_10_set3);

		String[] answers_11_set3 = {"Tăng tốc độ để nhanh chóng vượt qua bến đỗ", "Giảm tốc độ đến mức an toàn có thể và quan sát người qua đường và từ từ vượt qua xe buýt", "Yêu cầu phải dừng lại phía sau xe buýt chờ xe rời bến mới đi tiếp"};
		Answer answer_11_set3 = new Answer(answers_11_set3, 1, idList.get(60));
		answerRepo.save(answer_11_set3);

		String[] answers_12_set3 = {"Không nên đi cố vào đường hẹp; xe đi ở sườn núi nên dừng lại trước để nhường đường; khi dừng xe nhường đường phải đỗ ngay ngắn", "Trong khi tránh nhau không nên đổi số; khi tránh nhau ban đêm, phải tắt đèn pha bật đèn cốt", "Khi tránh nhau ban đêm, phải thường xuyên bật đèn pha tắt đèn cốt", "Cả ý 1 và ý 2"};
		Answer answer_12_set3 = new Answer(answers_12_set3, 3, idList.get(61));
		answerRepo.save(answer_12_set3);

		String[] answers_13_set3 = {"Tăng ga thật nhanh, giảm ga từ từ", "Tăng ga thật nhanh, giảm ga thật nhanh", "Tăng ga từ từ, giảm ga thật nhanh", "Tăng ga từ từ, giảm ga từ từ"};
		Answer answer_13_set3 = new Answer(answers_13_set3, 2, idList.get(62));
		answerRepo.save(answer_13_set3);

		String[] answers_14_set3 = {"Biển 1 và 2.", "Biển 1 và 3.", "Biển 2 và 3.", "Cả ba biển."};
		Answer answer_14_set3 = new Answer(answers_14_set3, 0, idList.get(63));
		answerRepo.save(answer_14_set3);

		String[] answers_15_set3 = {"Biển 1.", "Biển 2.", "Cả hai biển."};
		Answer answer_15_set3 = new Answer(answers_15_set3, 0, idList.get(64));
		answerRepo.save(answer_15_set3);

		String[] answers_16_set3 = {"Biển 1.", "Biển 2.", "Biển 3.", "Biển 1 và 3."};
		Answer answer_16_set3 = new Answer(answers_16_set3, 0, idList.get(65));
		answerRepo.save(answer_16_set3);

		String[] answers_17_set3 = {"Biển 1.", "Biển 2.", "Biển 3.", "Biển 1 và 3."};
		Answer answer_17_set3 = new Answer(answers_17_set3, 0, idList.get(66));
		answerRepo.save(answer_17_set3);

		String[] answers_18_set3 = {"Biển 1.", "Biển 2.", "Biển 3."};
		Answer answer_18_set3 = new Answer(answers_18_set3, 2, idList.get(67));
		answerRepo.save(answer_18_set3);

		String[] answers_19_set3 = {"Biển 1", "Biển 2", "Không biển nào."};
		Answer answer_19_set3 = new Answer(answers_19_set3, 2, idList.get(68));
		answerRepo.save(answer_19_set3);

		String[] answers_20_set3 = {"Biển 1", "Biển 2", "Cả 2 biển", "Không biển nào."};
		Answer answer_20_set3 = new Answer(answers_20_set3, 0, idList.get(69));
		answerRepo.save(answer_20_set3);

		String[] answers_21_set3 = {"Phân chia hai chiều xe chạy ngược nhiều nhau", "Phân chia các làn xe chạy cùng chiều nhau."};
		Answer answer_21_set3 = new Answer(answers_21_set3, 0, idList.get(70));
		answerRepo.save(answer_21_set3);

		String[] answers_22_set3 = {"Xe khách, xe tải, mô tô", "Xe tải, xe con, mô tô", "Xe khách, xe con, mô tô."};
		Answer answer_22_set3 = new Answer(answers_22_set3, 0, idList.get(71));
		answerRepo.save(answer_22_set3);

		String[] answers_23_set3 = {"Xe con (A), mô tô, xe con (B), xe đạp", "Xe con (B), xe đạp, mô tô, xe con (A)", "Xe con (A), xe con (B), mô tô + xe đạp", "Mô tô + xe đạp, xe con (A), xe con (B)."};
		Answer answer_23_set3 = new Answer(answers_23_set3, 3, idList.get(72));
		answerRepo.save(answer_23_set3);

		String[] answers_24_set3 = {"Xe con ( E ), mô tô ( C )", "Xe tải ( A ), mô tô ( D )", "Xe khách ( B ), mô tô ( C )", "Xe khách ( B ), mô tô ( D )."};
		Answer answer_24_set3 = new Answer(answers_24_set3, 0, idList.get(73));
		answerRepo.save(answer_24_set3);

		String[] answers_25_set3 = {"Xe của bạn, mô tô, xe con", "Xe con, xe của bạn, mô tô", "Mô tô, xe con, xe của bạn."};
		Answer answer_25_set3 = new Answer(answers_25_set3, 2, idList.get(74));
		answerRepo.save(answer_25_set3);

		String[] answers_1_set4 = {"Dải phân cách gồm loại cố định và loại di động", "Dải phân cách gồm tường chống ồn, hộ lan cứng và hộ lan mềm", "Dải phân cách gồm giá long môn và biển báo hiệu đường bộ"};
		Answer answer_1_set4 = new Answer(answers_1_set4, 0, idList.get(75));
		answerRepo.save(answer_1_set4);

		String[] answers_2_set4 = {"Là người điều khiển phương tiện tham gia giao thông tại nơi thi công, nơi ùn tắc giao thông, ở bến phà, tại cầu đường bộ đi chung với đường sắt", "Là cảnh sát giao thông, người được giao nhiệm vụ hướng dẫn giao thông tại nơi thi công, nơi ùn tắc giao thông, ở bến phà, tại cầu đường bộ đi chung với đường sắt", "Là người tham gia giao thông tại nơi thi công, nơi ùn tắc giao thông, ở bến phà, tại cầu đường bộ đi chung với đường sắt"};
		Answer answer_2_set4 = new Answer(answers_2_set4, 1, idList.get(76));
		answerRepo.save(answer_2_set4);

		String[] answers_3_set4 = {"Từ 22 giờ đêm đến 5 giờ sáng", "Từ 5 giờ sáng đến 22 giờ tối", "Từ 23 giờ đêm đến 5 giờ sáng hôm sau"};
		Answer answer_3_set4 = new Answer(answers_3_set4, 1, idList.get(77));
		answerRepo.save(answer_3_set4);

		String[] answers_4_set4 = {"Buông cả hai tay; sử dụng xe để kéo, đẩy xe khác, vật khác; sử dụng chân chống của xe quệt xuống đường khi xe đang chạy", "Buông một tay; sử dụng xe để chở người hoặc hàng hoá; để chân chạm xuống đất khi khởi hành", "Đội mũ bảo hiểm; chạy xe đúng tốc độ quy định và chấp hành đúng quy tắc giao thông đường bộ.", "Chở người ngồi sau dưới 16 tuổi"};
		Answer answer_4_set4 = new Answer(answers_4_set4, 0, idList.get(78));
		answerRepo.save(answer_4_set4);

		String[] answers_5_set4 = {"Xe mô tô 2 bánh có dung tích xi-lanh từ 50 cm3 trở lên", "Xe gắn máy có dung tích xi-lanh dưới 50 cm3", "Xe ô tô tải dưới 3,5 tấn; xe chở người đến 9 chỗ ngồi", "Tất cả các ý nêu trên"};
		Answer answer_5_set4 = new Answer(answers_5_set4, 1, idList.get(79));
		answerRepo.save(answer_5_set4);

		String[] answers_6_set4 = {"Người tham gia giao thông ở các hướng phải dừng lại", "Người tham gia giao thông ở các hướng được đi theo chiều gậy chỉ của cảnh sát giao thông", "Người tham gia giao thông ở phía trước và phía sau người điều khiển được đi tất cả các hướng; người tham gia giao thông ở phía bên phải và phía bên trái người điều khiển phải dừng lại", "Người tham gia giao thông ở phía trước và phía sau người điều khiển phải dừng lại; người tham giao thông ở phía bên phải và bên trái người điều khiển được đi tất cả các hướng"};
		Answer answer_6_set4 = new Answer(answers_6_set4, 3, idList.get(80));
		answerRepo.save(answer_6_set4);

		String[] answers_7_set4 = {"Nơi đường hẹp chỉ đủ cho một xe chạy và có chỗ tránh xe thì xe nào ở gần chỗ tránh hơn phải vào vị trí tránh, nhường đường cho xe kia đi", "Xe xuống dốc phải nhường đường cho xe đang lên dốc; xe nào có chướng ngại vật phía trước phải nhường đường cho xe không có chướng ngại vật đi trước", "Xe lên dốc phải nhường đường cho xe xuống dốc; xe nào không có chướng ngại vật phía trước phải nhường đường cho xe có chướng ngại vật đi trước.", "Cả ý 1 và ý 2"};
		Answer answer_7_set4 = new Answer(answers_7_set4, 3, idList.get(81));
		answerRepo.save(answer_7_set4);

		String[] answers_8_set4 = {"Đi vào phần đường dành cho người đi bộ và phương tiện khác; sử dụng ô, điện thoại di động, thiết bị âm thanh (trừ thiết bị trợ thính), đi xe dàn hàng ngang", "Chở 02 người; trong đó, có người bệnh đi cấp cứu hoặc trẻ em dưới 14 tuổi hoặc áp giải người có hành vi vi phạm pháp luật", "Điều khiển phương tiện tham gia giao thông trên đường tỉnh lộ hoặc quốc lộ"};
		Answer answer_8_set4 = new Answer(answers_8_set4, 0, idList.get(82));
		answerRepo.save(answer_8_set4);

		String[] answers_9_set4 = {"Là đoạn đường nằm trong khu công nghiệp có đông người và phương tiện tham gia giao thông và được xác định cụ thể bằng biển chỉ dẫn địa giới", "Là đoạn đường bộ nằm trong khu vực nội thành phố, nội thị xã, nội thị trấn và những đoạn đường có đông dân cư sinh sống sát dọc theo đường, có các hoạt động ảnh hưởng đến an toàn giao thông; được xác định bằng biển báo hiệu là đường khu đông dân cư", "Là đoạn đường nằm ngoài khu vực nội thành phố, nội thị xã có đông người và phương tiện tham gia giao thông và được xác định cụ thể bằng biển chỉ dẫn địa giới"};
		Answer answer_9_set4 = new Answer(answers_9_set4, 1, idList.get(83));
		answerRepo.save(answer_9_set4);

		String[] answers_10_set4 = {"Để làm đẹp", "Để tránh mưa nắng", "Để giảm thiểu chấn thương vùng đầu", "Để các loại phương tiện khác dễ quan sát"};
		Answer answer_10_set4 = new Answer(answers_10_set4, 2, idList.get(84));
		answerRepo.save(answer_10_set4);

		String[] answers_11_set4 = {"Là sự hiểu biết và chấp hành nghiêm chỉnh pháp luật về giao thông; là ý thức trách nhiệm với cộng đồng khi tham gia giao thông", "Là ứng xử có văn hóa, có tình yêu thương con người trong các tình huống không may xảy ra khi tham gia giao thông", "Cả ý 1 và ý 2"};
		Answer answer_11_set4 = new Answer(answers_11_set4, 2, idList.get(85));
		answerRepo.save(answer_11_set4);

		String[] answers_12_set4 = {"Quan sát cẩn thận các chướng ngại vật và báo hiệu bằng còi, đèn; giảm tốc độ tới mức cần thiết, về số thấp và thực hiện quay vòng với tốc độ phù hợp với bán kính cong của đường vòng", "Quan sát cẩn thận các chướng ngại vật và báo hiệu bằng còi, đèn; tăng tốc để nhanh chóng qua đường vòng, đạp ly hợp (côn) và giảm tốc độ sau khi qua đường vòng"};
		Answer answer_12_set4 = new Answer(answers_12_set4, 0, idList.get(86));
		answerRepo.save(answer_12_set4);

		String[] answers_13_set4 = {"Đứng thẳng trên giá gác chân lái sau đó hơi gập đầu gối và khuỷu tay, đi chậm để không nẩy quá mạnh", "Ngồi lùi lại phía sau, tăng ga vượt nhanh qua đoạn đường xóc", "Ngồi lệch sang bên trái hoặc bên phải để lấy thăng bằng qua đoạn đường gồ ghề"};
		Answer answer_13_set4 = new Answer(answers_13_set4, 0, idList.get(87));
		answerRepo.save(answer_13_set4);

		String[] answers_14_set4 = {"Biển 1 và 2.", "Biển 1 và 3.", "Biển 2 và 3.", "Cả ba biển."};
		Answer answer_14_set4 = new Answer(answers_14_set4, 0, idList.get(88));
		answerRepo.save(answer_14_set4);

		String[] answers_15_set4 = {"Biển 1.", "Biển 2.", "Biển 3.", "Biển 1 và 2."};
		Answer answer_15_set4 = new Answer(answers_15_set4, 3, idList.get(89));
		answerRepo.save(answer_15_set4);

		String[] answers_16_set4 = {"Biển 1.", "Biển 1 và 3.", "Biển 3.", "Cả ba biển."};
		Answer answer_16_set4 = new Answer(answers_16_set4, 2, idList.get(90));
		answerRepo.save(answer_16_set4);

		String[] answers_17_set4 = {"Biển 1 và 2.", "Biển 1 và 3.", "Biển 2 và 3.", "Cả ba biển."};
		Answer answer_17_set4 = new Answer(answers_17_set4, 1, idList.get(91));
		answerRepo.save(answer_17_set4);

		String[] answers_18_set4 = {"Biển 1.", "Biển 2.", "Biển 3."};
		Answer answer_18_set4 = new Answer(answers_18_set4, 0, idList.get(92));
		answerRepo.save(answer_18_set4);

		String[] answers_19_set4 = {"Biển 1", "Biển 2."};
		Answer answer_19_set4 = new Answer(answers_19_set4, 0, idList.get(93));
		answerRepo.save(answer_19_set4);

		String[] answers_20_set4 = {"Biển 1", "Biển 2", "Cả 2 biển", "Không biển nào."};
		Answer answer_20_set4 = new Answer(answers_20_set4, 1, idList.get(94));
		answerRepo.save(answer_20_set4);

		String[] answers_21_set4 = {"Vạch 1", "Vạch 2", "Vạch 3", "Vạch 1 và vạch 3"};
		Answer answer_21_set4 = new Answer(answers_21_set4, 3, idList.get(95));
		answerRepo.save(answer_21_set4);

		String[] answers_22_set4 = {"Xe khách, xe tải, mô tô, xe con.", "Xe con, xe khách, xe tải, mô tô", "Mô tô, xe tải, xe khách, xe con.", "Mô tô, xe tải, xe con, xe khách."};
		Answer answer_22_set4 = new Answer(answers_22_set4, 2, idList.get(96));
		answerRepo.save(answer_22_set4);

		String[] answers_23_set4 = {"Xe mô tô", "Xe con"};
		Answer answer_23_set4 = new Answer(answers_23_set4, 0, idList.get(97));
		answerRepo.save(answer_23_set4);

		String[] answers_24_set4 = {"Xe con (B), mô tô (C)", "Xe con (A), mô tô (C)", "Xe con (E), mô tô (D)", "Tất cả các loại xe trên."};
		Answer answer_24_set4 = new Answer(answers_24_set4, 2, idList.get(98));
		answerRepo.save(answer_24_set4);

		String[] answers_25_set4 = {"Xe của bạn, mô tô, xe con", "Xe con, xe của bạn, mô tô", "Mô tô, xe con, xe của bạn."};
		Answer answer_25_set4 = new Answer(answers_25_set4, 1, idList.get(99));
		answerRepo.save(answer_25_set4);

		String[] answers_1_set5 = {"Là người điều khiển xe cơ giới", "Là người điều khiển xe thô sơ", "Là người điều khiển xe có súc vật kéo"};
		Answer answer_1_set5 = new Answer(answers_1_set5, 0, idList.get(100));
		answerRepo.save(answer_1_set5);

		String[] answers_2_set5 = {"Là trạng thái đứng yên của phương tiện giao thông không giới hạn thời gian để cho người lên, xuống phương tiện, xếp dỡ hàng hóa hoặc thực hiện công việc khác", "Là trạng thái đứng yên tạm thời của phương tiện giao thông trong một khoảng thời gian cần thiết đủ để cho người lên, xuống phương tiện, xếp dỡ hàng hóa hoặc thực hiện công việc khác", "Là trạng thái đứng yên của phương tiện giao thông không giới hạn thời gian giữa 02 lần vận chuyển hàng hoá hoặc hành khách"};
		Answer answer_2_set5 = new Answer(answers_2_set5, 1, idList.get(101));
		answerRepo.save(answer_2_set5);

		String[] answers_3_set5 = {"Bất cứ đèn nào miễn là mắt nhìn rõ phía trước", "Chỉ bật đèn chiếu xa (đèn pha) khi không nhìn rõ đường", "Đèn chiếu xa (đèn pha) khi đường vắng, đèn pha chiếu gần (đèn cốt) khi có xe đi ngược chiều", "Đèn chiếu gần (đèn cốt)"};
		Answer answer_3_set5 = new Answer(answers_3_set5, 3, idList.get(102));
		answerRepo.save(answer_3_set5);

		String[] answers_4_set5 = {"Được mang, vác tuỳ trường hợp cụ thể", "Không được mang, vác", "Được mang, vác nhưng phải đảm bảo an toàn", "Được mang, vác tùy theo sức khỏe của bản thân"};
		Answer answer_4_set5 = new Answer(answers_4_set5, 1, idList.get(103));
		answerRepo.save(answer_4_set5);

		String[] answers_5_set5 = {"Xe mô tô có dung tích xi-lanh 125 cm3", "Xe mô tô có dung tích xi-lanh từ 175 cm3 trở lên", "Xe mô tô có dung tích xi-lanh 100 cm3"};
		Answer answer_5_set5 = new Answer(answers_5_set5, 1, idList.get(104));
		answerRepo.save(answer_5_set5);

		String[] answers_6_set5 = {"Người tham gia giao thông ở hướng đối diện cảnh sát giao thông được đi, các hướng khác cần phải dừng lại", "Người tham gia giao thông được rẽ phải theo chiều mũi tên màu xanh ở bục cảnh sát giao thông", "Người tham gia giao thông ở các hướng đều phải dừng lại trừ các xe đã ở trong khu vực giao nhau", "Người ở hướng đối diện cảnh sát giao thông phải dừng lại, các hướng khác được đi trong đó có bạn"};
		Answer answer_6_set5 = new Answer(answers_6_set5, 2, idList.get(105));
		answerRepo.save(answer_6_set5);

		String[] answers_7_set5 = {"Tiếp tục đi vì xe lên dốc phải nhường đường cho mình", "Nhường đường cho xe lên dốc", "Chỉ nhường đường khi xe lên dốc nháy đèn"};
		Answer answer_7_set5 = new Answer(answers_7_set5, 1, idList.get(106));
		answerRepo.save(answer_7_set5);

		String[] answers_8_set5 = {"Được phép nhưng phải đảm bảo an toàn", "Không được phép", "Được phép tùy từng hoàn cảnh, điều kiện cụ thể"};
		Answer answer_8_set5 = new Answer(answers_8_set5, 1, idList.get(107));
		answerRepo.save(answer_8_set5);

		String[] answers_9_set5 = {"50 km/h", "40 km/h", "60 km/h"};
		Answer answer_9_set5 = new Answer(answers_9_set5, 1, idList.get(108));
		answerRepo.save(answer_9_set5);

		String[] answers_10_set5 = {"Tăng tốc độ qua đường giao nhau để đi trước xe đi trên đường ưu tiên", "Giảm tốc độ qua đường giao nhau để đi trước xe đi trên đường ưu tiên", "Nhường đường cho xe đi trên đường ưu tiên từ bất kỳ hướng nào tới"};
		Answer answer_10_set5 = new Answer(answers_10_set5, 2, idList.get(109));
		answerRepo.save(answer_10_set5);

		String[] answers_11_set5 = {"Điều khiển xe đi bên phải theo chiều đi của mình; đi đúng phần đường, làn đường quy định; đội mũ bảo hiểm đạt chuẩn, cài quai đúng quy cách", "Điều khiển xe đi trên phần đường, làn đường có ít phương tiện tham gia giao thông", "Điều khiển xe và đội mũ bảo hiểm ở nơi có biển báo bắt buộc đội mũ bảo hiểm"};
		Answer answer_11_set5 = new Answer(answers_11_set5, 0, idList.get(110));
		answerRepo.save(answer_11_set5);

		String[] answers_12_set5 = {"Sử dụng phanh trước", "Sử dụng phanh sau", "Giảm hết ga; sử dụng đồng thời cả phanh sau và phanh trước"};
		Answer answer_12_set5 = new Answer(answers_12_set5, 2, idList.get(111));
		answerRepo.save(answer_12_set5);

		String[] answers_13_set5 = {"Biển 1.", "Biển 2.", "Cả hai biển."};
		Answer answer_13_set5 = new Answer(answers_13_set5, 2, idList.get(112));
		answerRepo.save(answer_13_set5);

		String[] answers_14_set5 = {"Biển 1.", "Biển 2.", "Không biển nào."};
		Answer answer_14_set5 = new Answer(answers_14_set5, 1, idList.get(113));
		answerRepo.save(answer_14_set5);

		String[] answers_15_set5 = {"Biển 1.", "Biển 2.", "Biển 3."};
		Answer answer_15_set5 = new Answer(answers_15_set5, 2, idList.get(114));
		answerRepo.save(answer_15_set5);

		String[] answers_16_set5 = {"Biển 1.", "Biển 2.", "Biển 3."};
		Answer answer_16_set5 = new Answer(answers_16_set5, 0, idList.get(115));
		answerRepo.save(answer_16_set5);

		String[] answers_17_set5 = {"Biển 1.", "Biển 2.", "Biển 3.", "Biển 2 và 3."};
		Answer answer_17_set5 = new Answer(answers_17_set5, 0, idList.get(116));
		answerRepo.save(answer_17_set5);

		String[] answers_18_set5 = {"Biển 1.", "Biển 2.", "Biển 3."};
		Answer answer_18_set5 = new Answer(answers_18_set5, 1, idList.get(117));
		answerRepo.save(answer_18_set5);

		String[] answers_19_set5 = {"Biển 1", "Biển 2", "Cả hai biển."};
		Answer answer_19_set5 = new Answer(answers_19_set5, 1, idList.get(118));
		answerRepo.save(answer_19_set5);

		String[] answers_20_set5 = {"Biển 1", "Biển 2", "Biển 3."};
		Answer answer_20_set5 = new Answer(answers_20_set5, 1, idList.get(119));
		answerRepo.save(answer_20_set5);

		String[] answers_21_set5 = {"Vị trí dừng xe của các phương tiện vận tải hành khách công cộng", "Báo cho người điều khiển được dừng phương tiện trong phạm vi phần mặt đường có bố trí vạch để tránh ùn tắc giao thông.", "Dùng để xác định vị trí giữa các phương tiện trên đường."};
		Answer answer_21_set5 = new Answer(answers_21_set5, 0, idList.get(120));
		answerRepo.save(answer_21_set5);

		String[] answers_22_set5 = {"Xe tải", "Xe con và mô tô", "Cả ba xe", "Xe con và xe tải."};
		Answer answer_22_set5 = new Answer(answers_22_set5, 0, idList.get(121));
		answerRepo.save(answer_22_set5);

		String[] answers_23_set5 = {"Xe khách", "Mô tô", "Xe con", "Xe con và mô tô."};
		Answer answer_23_set5 = new Answer(answers_23_set5, 2, idList.get(122));
		answerRepo.save(answer_23_set5);

		String[] answers_24_set5 = {"Cho phép", "Không được vượt."};
		Answer answer_24_set5 = new Answer(answers_24_set5, 1, idList.get(123));
		answerRepo.save(answer_24_set5);

		String[] answers_25_set5 = {"Tăng tốc độ, rẽ phải trước xe tải và xe đạp", "Giảm tốc độ, rẽ phải sau xe tải và xe đạp", "Tăng tốc độ, rẽ phải trước xe đạp."};
		Answer answer_25_set5 = new Answer(answers_25_set5, 1, idList.get(124));
		answerRepo.save(answer_25_set5);

		String[] answers_1_set6 = {"Đường không ưu tiên", "Đường tỉnh lộ", "Đường quốc lộ", "Đường ưu tiên"};
		Answer answer_1_set6 = new Answer(answers_1_set6, 3, idList.get(125));
		answerRepo.save(answer_1_set6);

		String[] answers_2_set6 = {"Là trạng thái đứng yên của phương tiện giao thông có giới hạn trong một khoảng thời gian cần thiết đủ để cho người lên, xuống phương tiện đó, xếp dỡ hàng hóa hoặc thực hiện công việc khác", "Là trạng thái đứng yên của phương tiện giao thông không giới hạn thời gian"};
		Answer answer_2_set6 = new Answer(answers_2_set6, 1, idList.get(126));
		answerRepo.save(answer_2_set6);

		String[] answers_3_set6 = {"Phải đảm bảo phụ tùng do đúng nhà sản xuất đó cung cấp", "Phải được chấp thuận của cơ quan có thẩm quyền", "Phải là xe đăng ký và hoạt động tại các khu vực có địa hình phức tạp"};
		Answer answer_3_set6 = new Answer(answers_3_set6, 1, idList.get(127));
		answerRepo.save(answer_3_set6);

		String[] answers_4_set6 = {"Được phép", "Được bám trong trường hợp phương tiện của mình bị hỏng", "Được kéo, đẩy trong trường hợp phương tiện khác bị hỏng.", "Không được phép"};
		Answer answer_4_set6 = new Answer(answers_4_set6, 3, idList.get(128));
		answerRepo.save(answer_4_set6);

		String[] answers_5_set6 = {"Xe mô tô hai bánh có dung tích xi-lanh từ 50 cm3 đến dưới 175 cm3", "Xe mô tô ba bánh dùng cho người khuyết tật", "Cả ý 1 và ý 2"};
		Answer answer_5_set6 = new Answer(answers_5_set6, 2, idList.get(129));
		answerRepo.save(answer_5_set6);

		String[] answers_6_set6 = {"Biển báo hiệu cố định", "Báo hiệu tạm thời"};
		Answer answer_6_set6 = new Answer(answers_6_set6, 1, idList.get(130));
		answerRepo.save(answer_6_set6);

		String[] answers_7_set6 = {"Nhường đường cho xe đi ở bên phải mình tới", "Nhường đường cho xe đi ở bên trái mình tới", "Nhường đường cho xe đi trên đường ưu tiên hoặc đường chính từ bất kỳ hướng nào tới"};
		Answer answer_7_set6 = new Answer(answers_7_set6, 2, idList.get(131));
		answerRepo.save(answer_7_set6);

		String[] answers_8_set6 = {"Khi có báo hiệu cảnh báo nguy hiểm hoặc có chướng ngại vật trên đường; khi chuyển hướng xe chạy hoặc tầm nhìn bị hạn chế; khi qua nơi đường giao nhau, nơi đường bộ giao nhau với đường sắt; đường vòng; đường có địa hình quanh co, đèo dốc", "Khi qua cầu, cống hẹp; khi lên gần đỉnh dốc, khi xuống dốc, khi qua trường học, khu đông dân cư, khu vực đang thi công trên đường bộ; hiện trường xảy ra tai nạn giao thông", "Khi điều khiển xe vượt xe khác trên quốc lộ, đường cao tốc", "Cả ý 1 và ý 2"};
		Answer answer_8_set6 = new Answer(answers_8_set6, 3, idList.get(132));
		answerRepo.save(answer_8_set6);

		String[] answers_9_set6 = {"60 km/h", "50 km/h", "40 km/h"};
		Answer answer_9_set6 = new Answer(answers_9_set6, 0, idList.get(133));
		answerRepo.save(answer_9_set6);

		String[] answers_10_set6 = {"Giảm tốc độ, đi từ từ để vượt qua trước người đi bộ", "Giảm tốc độ, có thể dừng lại nếu cần thiết trước vạch dừng xe để nhường đường cho người đi bộ qua đường", "Tăng tốc độ để vượt qua trước người đi bộ"};
		Answer answer_10_set6 = new Answer(answers_10_set6, 1, idList.get(134));
		answerRepo.save(answer_10_set6);

		String[] answers_11_set6 = {"Điều khiển xe đi bên phải theo chiều đi của mình; đi đúng phần đường, làn đường quy định; dừng, đỗ xe đúng nơi quy định; đã uống rượu, bia thì không lái xe", "Điều khiển xe đi trên phần đường, làn đường có ít phương tiện giao thông; dừng xe, đỗ xe ở nơi thuận tiện hoặc theo yêu cầu của hành khách, của người thân", "Dừng và đỗ xe ở nơi thuận tiện cho việc chuyên chở hành khách và giao nhận hàng hóa; sử dụng ít rượu, bia thì có thể lái xe"};
		Answer answer_11_set6 = new Answer(answers_11_set6, 0, idList.get(135));
		answerRepo.save(answer_11_set6);

		String[] answers_12_set6 = {"Giảm tốc độ để đảm bảo an toàn với xe phía trước và sử dụng điện thoại để liên lạc", "Giảm tốc độ để dừng xe ở nơi cho phép dừng xe sau đó sử dụng điện thoại để liên lạc", "Tăng tốc độ để cách xa xe phía sau và sử dụng điện thoại để liên lạc"};
		Answer answer_12_set6 = new Answer(answers_12_set6, 1, idList.get(136));
		answerRepo.save(answer_12_set6);

		String[] answers_13_set6 = {"Biển 1.", "Biển 2.", "Biển 3."};
		Answer answer_13_set6 = new Answer(answers_13_set6, 0, idList.get(137));
		answerRepo.save(answer_13_set6);

		String[] answers_14_set6 = {"Biển 1.", "Biển 2.", "Cả 2 biển."};
		Answer answer_14_set6 = new Answer(answers_14_set6, 2, idList.get(138));
		answerRepo.save(answer_14_set6);

		String[] answers_15_set6 = {"Tốc độ tối đa cho phép về ban đêm cho các phương tiện là 70 km/h.", "Tốc độ tối thiểu cho phép về ban đêm cho các phương tiện là 70 km/h."};
		Answer answer_15_set6 = new Answer(answers_15_set6, 0, idList.get(139));
		answerRepo.save(answer_15_set6);

		String[] answers_16_set6 = {"Biển 1.", "Biển 1 và 2.", "Biển 2 và 3.", "Cả ba biển."};
		Answer answer_16_set6 = new Answer(answers_16_set6, 3, idList.get(140));
		answerRepo.save(answer_16_set6);

		String[] answers_17_set6 = {"Biển 1 và 3.", "Biển 2.", "Biển 3."};
		Answer answer_17_set6 = new Answer(answers_17_set6, 1, idList.get(141));
		answerRepo.save(answer_17_set6);

		String[] answers_18_set6 = {"Biển 1.", "Biển 2.", "Biển 3."};
		Answer answer_18_set6 = new Answer(answers_18_set6, 1, idList.get(142));
		answerRepo.save(answer_18_set6);

		String[] answers_19_set6 = {"Biển 1", "Biển 2", "Biển 3", "Cả ba biển."};
		Answer answer_19_set6 = new Answer(answers_19_set6, 0, idList.get(143));
		answerRepo.save(answer_19_set6);

		String[] answers_20_set6 = {"Dừng xe tại khu vực có trạm Cảnh sát giao thông.", "Tiếp tục lưu thông với tốc độ bình thường", "Phải giảm tốc độ đến mức an toàn và không được vượt khi đi qua khu vực này."};
		Answer answer_20_set6 = new Answer(answers_20_set6, 2, idList.get(144));
		answerRepo.save(answer_20_set6);

		String[] answers_21_set6 = {"Xe tải, xe khách, xe con, mô tô.", "Xe tải, mô tô, xe khách, xe con.", "xe khách, xe tải, xe con, mô tô.", "Mô tô, xe khách, xe tải, xe con."};
		Answer answer_21_set6 = new Answer(answers_21_set6, 1, idList.get(145));
		answerRepo.save(answer_21_set6);

		String[] answers_22_set6 = {"Cả ba hướng", "Chỉ hướng 1 và 3", "Chỉ hướng 1."};
		Answer answer_22_set6 = new Answer(answers_22_set6, 0, idList.get(146));
		answerRepo.save(answer_22_set6);

		String[] answers_23_set6 = {"Các xe phía tay phải và tay trái của người điều khiển được phép đi thẳng", "Cho phép các xe ở mọi hướng được rẽ phải", "Tất cả các xe phải dừng lại trước ngã tư, trừ những xe đã ở trong ngã tư được phép tiếp tục đi."};
		Answer answer_23_set6 = new Answer(answers_23_set6, 2, idList.get(147));
		answerRepo.save(answer_23_set6);

		String[] answers_24_set6 = {"Xe mô tô", "Xe ô tô con", "Không xe nào vi phạm", "Cả hai xe."};
		Answer answer_24_set6 = new Answer(answers_24_set6, 3, idList.get(148));
		answerRepo.save(answer_24_set6);

		String[] answers_25_set6 = {"Xe con", "Xe mô tô", "Cả 2 xe đều đúng."};
		Answer answer_25_set6 = new Answer(answers_25_set6, 0, idList.get(149));
		answerRepo.save(answer_25_set6);

		String[] answers_1_set7 = {"Gồm xe ô tô; máy kéo; xe mô tô hai bánh; xe mô tô ba bánh; xe gắn máy; xe cơ giới dùng cho người khuyết tật và xe máy chuyên dùng", "Gồm xe ô tô; máy kéo; rơ moóc hoặc sơ mi rơ moóc được kéo bởi xe ô tô, máy kéo; xe mô tô hai bánh; xe mô tô ba bánh, xe gắn máy (kể cả xe máy điện) và các loại xe tương tự"};
		Answer answer_1_set7 = new Answer(answers_1_set7, 1, idList.get(150));
		answerRepo.save(answer_1_set7);

		String[] answers_2_set7 = {"Diễn ra trên đường phố không có người qua lại", "Được người dân ủng hộ", "Được cơ quan có thẩm quyền cấp phép"};
		Answer answer_2_set7 = new Answer(answers_2_set7, 2, idList.get(151));
		answerRepo.save(answer_2_set7);

		String[] answers_3_set7 = {"Được phép", "Không được phép", "Tùy từng trường hợp"};
		Answer answer_3_set7 = new Answer(answers_3_set7, 1, idList.get(152));
		answerRepo.save(answer_3_set7);

		String[] answers_4_set7 = {"Được sử dụng", "Chỉ người ngồi sau được sử dụng", "Không được sử dụng", "Được sử dụng nếu không có áo mưa"};
		Answer answer_4_set7 = new Answer(answers_4_set7, 2, idList.get(153));
		answerRepo.save(answer_4_set7);

		String[] answers_5_set7 = {"Biển báo nguy hiểm", "Biển báo cấm", "Biển báo hiệu lệnh", "Biển báo chỉ dẫn"};
		Answer answer_5_set7 = new Answer(answers_5_set7, 1, idList.get(154));
		answerRepo.save(answer_5_set7);

		String[] answers_6_set7 = {"Cho xe đi trên bất kỳ làn đường nào hoặc giữa 02 làn đường nếu không có xe phía trước; khi cần thiết phải chuyển làn đường, người lái xe phải quan sát xe phía trước để bảo đảm an toàn", "Phải cho xe đi trong một làn đường và chỉ được chuyển làn đường ở những nơi cho phép; khi chuyển làn phải có tín hiệu báo trước và phải bảo đảm an toàn", "Phải cho xe đi trong một làn đường, khi cần thiết phải chuyển làn đường, người lái xe phải quan sát xe phía trước để bảo đảm an toàn"};
		Answer answer_6_set7 = new Answer(answers_6_set7, 1, idList.get(155));
		answerRepo.save(answer_6_set7);

		String[] answers_7_set7 = {"Phải nhường đường cho xe đi đến từ bên phải", "Xe báo hiệu xin đường trước xe đó được đi trước", "Phải nhường đường cho xe đi đến từ bên trái"};
		Answer answer_7_set7 = new Answer(answers_7_set7, 0, idList.get(156));
		answerRepo.save(answer_7_set7);

		String[] answers_8_set7 = {"Nhường đường cho người đi bộ đang đi trên phần đường dành cho người đi bộ sang đường; nhường đường cho xe đi trên đường ưu tiên, đường chính từ bất kỳ hướng nào tới; nhường đường cho xe ưu tiên, xe đi từ bên phải đến", "Nhường đường cho người đi bộ đang đứng chờ đi qua phần đường dành cho người đi bộ sang đường; nhường đường cho xe đi trên đường ngược chiều, đường nhánh từ bất kỳ hướng nào tới; nhường đường cho xe đi từ bên trái đế", "Không phải nhường đường"};
		Answer answer_8_set7 = new Answer(answers_8_set7, 0, idList.get(157));
		answerRepo.save(answer_8_set7);

		String[] answers_9_set7 = {"60 km/h", "50 km/h", "40 km/h"};
		Answer answer_9_set7 = new Answer(answers_9_set7, 1, idList.get(158));
		answerRepo.save(answer_9_set7);

		String[] answers_10_set7 = {"Đỏ – Vàng – Xanh", "Cam – Vàng – Xanh", "Vàng – Xanh dương – Xanh lá", "Đỏ – Cam – Xanh"};
		Answer answer_10_set7 = new Answer(answers_10_set7, 0, idList.get(159));
		answerRepo.save(answer_10_set7);

		String[] answers_11_set7 = {"Thực hiện sơ cứu ban đầu trong trường hợp khẩn cấp; thông báo vụ tai nạn đến cơ quan thi hành pháp luật", "Nhanh chóng lái xe gây tai nạn hoặc đi nhờ xe khác ra khỏi hiện trường vụ tai nạn", "Cả ý 1 và ý 2"};
		Answer answer_11_set7 = new Answer(answers_11_set7, 0, idList.get(160));
		answerRepo.save(answer_11_set7);

		String[] answers_12_set7 = {"Sử dụng còi", "Phanh đồng thời cả phanh trước và phanh sau", "Chỉ sử dụng phanh trước"};
		Answer answer_12_set7 = new Answer(answers_12_set7, 2, idList.get(161));
		answerRepo.save(answer_12_set7);

		String[] answers_13_set7 = {"Không biển nào.", "Biển 1 và 2.", "Biển 2 và 3.", "Cả 3 biển."};
		Answer answer_13_set7 = new Answer(answers_13_set7, 2, idList.get(162));
		answerRepo.save(answer_13_set7);

		String[] answers_14_set7 = {"Biển 1.", "Biển 2.", "Cả 2 biển."};
		Answer answer_14_set7 = new Answer(answers_14_set7, 0, idList.get(163));
		answerRepo.save(answer_14_set7);

		String[] answers_15_set7 = {"Được phép.", "Không được phép."};
		Answer answer_15_set7 = new Answer(answers_15_set7, 1, idList.get(164));
		answerRepo.save(answer_15_set7);

		String[] answers_16_set7 = {"Biển 1.", "Biển 2 và 3.", "Biển 3."};
		Answer answer_16_set7 = new Answer(answers_16_set7, 0, idList.get(165));
		answerRepo.save(answer_16_set7);

		String[] answers_17_set7 = {"Biển 1 và 2.", "Biển 1 và 3.", "Biển 2 và 3.", "Cả ba biển."};
		Answer answer_17_set7 = new Answer(answers_17_set7, 0, idList.get(166));
		answerRepo.save(answer_17_set7);

		String[] answers_18_set7 = {"Biển 1", "Biển 2 và 3", "Cả ba biển."};
		Answer answer_18_set7 = new Answer(answers_18_set7, 1, idList.get(167));
		answerRepo.save(answer_18_set7);

		String[] answers_19_set7 = {"Biển 1", "Biển 2", "Biển 3", "Biển 1 và 2."};
		Answer answer_19_set7 = new Answer(answers_19_set7, 3, idList.get(168));
		answerRepo.save(answer_19_set7);

		String[] answers_20_set7 = {"Đi thẳng hoặc rẽ trái trên cầu vượt", "Đi thẳng hoặc rẽ phải trên cầu vượt", "Báo hiệu cầu vượt liên thông."};
		Answer answer_20_set7 = new Answer(answers_20_set7, 2, idList.get(169));
		answerRepo.save(answer_20_set7);

		String[] answers_21_set7 = {"Xe tải, xe con, mô tô", "Xe con, xe tải, mô tô", "Mô tô, xe con, xe tải", "Xe con, mô tô, xe tải."};
		Answer answer_21_set7 = new Answer(answers_21_set7, 2, idList.get(170));
		answerRepo.save(answer_21_set7);

		String[] answers_22_set7 = {"Cả hai xe", "Không xe nào vi phạm", "Chỉ xe mô tô vi phạm", "Chỉ xe tải vi phạm."};
		Answer answer_22_set7 = new Answer(answers_22_set7, 0, idList.get(171));
		answerRepo.save(answer_22_set7);

		String[] answers_23_set7 = {"Mô tô, xe con", "Xe con, xe tải", "Mô tô, xe tải", "Cả ba xe."};
		Answer answer_23_set7 = new Answer(answers_23_set7, 2, idList.get(172));
		answerRepo.save(answer_23_set7);

		String[] answers_24_set7 = {"Xe con", "Xe tải", "Xe con và xe tải."};
		Answer answer_24_set7 = new Answer(answers_24_set7, 1, idList.get(173));
		answerRepo.save(answer_24_set7);

		String[] answers_25_set7 = {"Quan sát nếu thấy không có tàu thì tăng tốc, cho xe vượt qua đường sắt", "Dừng lại trước rào chắn một khoảng cách an toàn", "Ra tín hiệu, yêu cầu người gác chắn tàu kéo chậm Barie để xe bạn qua."};
		Answer answer_25_set7 = new Answer(answers_25_set7, 1, idList.get(174));
		answerRepo.save(answer_25_set7);

		String[] answers_1_set8 = {"Gồm xe đạp (kể cả xe đạp máy, xe đạp điện), xe xích lô, xe lăn dùng cho người khuyết tật, xe súc vật kéo và các loại xe tương tự", "Gồm xe đạp (kể cả xe đạp máy, xe đạp điện), xe gắn máy, xe cơ giới dùng cho người khuyết tật và xe máy chuyên dùng.", "Gồm xe ô tô, máy kéo, rơ moóc hoặc sơ mi rơ moóc được kéo bởi xe ô tô, máy kéo."};
		Answer answer_1_set8 = new Answer(answers_1_set8, 0, idList.get(175));
		answerRepo.save(answer_1_set8);

		String[] answers_2_set8 = {"Bị nghiêm cấm", "Không bị nghiêm cấm", "Không bị nghiêm cấm, nếu có chất ma tuý ở mức nhẹ, có thể điều khiển phương tiện tham gia giao thông"};
		Answer answer_2_set8 = new Answer(answers_2_set8, 0, idList.get(176));
		answerRepo.save(answer_2_set8);

		String[] answers_3_set8 = {"Không được vượt", "Được vượt khi đang đi trên cầu", "Được phép vượt khi đi qua nơi giao nhau có ít phương tiện cùng tham gia giao thông", "Được vượt khi đảm bảo an toàn"};
		Answer answer_3_set8 = new Answer(answers_3_set8, 3, idList.get(177));
		answerRepo.save(answer_3_set8);

		String[] answers_4_set8 = {"Chỉ được phép nếu cả hai đội mũ bảo hiểm", "Không được phép", "Chỉ được thực hiện trên đường thật vắng", "Chỉ được phép khi người đi xe đạp đã quá mệt"};
		Answer answer_4_set8 = new Answer(answers_4_set8, 1, idList.get(178));
		answerRepo.save(answer_4_set8);

		String[] answers_5_set8 = {"Biển báo nguy hiểm", "Biển báo cấm", "Biển báo hiệu lệnh", "Biển báo chỉ dẫn"};
		Answer answer_5_set8 = new Answer(answers_5_set8, 0, idList.get(179));
		answerRepo.save(answer_5_set8);

		String[] answers_6_set8 = {"Xe thô sơ phải đi trên làn đường bên trái trong cùng, xe cơ giới, xe máy chuyên dùng đi trên làn đường bên phải", "Xe thô sơ phải đi trên làn đường bên phải trong cùng; xe cơ giới, xe máy chuyên dùng đi trên làn đường bên trá", "Xe thô sơ đi trên làn đường phù hợp không gây cản trở giao thông, xe cơ giới, xe máy chuyên dùng đi trên làn đường bên phải"};
		Answer answer_6_set8 = new Answer(answers_6_set8, 1, idList.get(180));
		answerRepo.save(answer_6_set8);

		String[] answers_7_set8 = {"5 mét", "3 mét", "4 mét"};
		Answer answer_7_set8 = new Answer(answers_7_set8, 0, idList.get(181));
		answerRepo.save(answer_7_set8);

		String[] answers_8_set8 = {"Khi cho xe chạy thẳng", "Trước khi thay đổi làn đường", "Sau khi thay đổi làn đường"};
		Answer answer_8_set8 = new Answer(answers_8_set8, 1, idList.get(182));
		answerRepo.save(answer_8_set8);

		String[] answers_9_set8 = {"Ô tô con, ô tô tải, ô tô chở người trên 30 chỗ", "Xe gắn máy, xe máy chuyên dùng", "Cả ý 1 và ý 2"};
		Answer answer_9_set8 = new Answer(answers_9_set8, 0, idList.get(183));
		answerRepo.save(answer_9_set8);

		String[] answers_10_set8 = {"Phải cho xe dừng lại trước vạch dừng, trường hợp đã đi quá vạch dừng hoặc đã quá gần vạch dừng nếu dừng lại thấy nguy hiểm thì được đi tiếp", "Trong trường hợp tín hiệu vàng nhấp nháy là được đi nhưng phải giảm tốc độ chú ý quan sát nhường đường cho người đi bộ qua đườn", "Nhanh chóng tăng tốc độ, vượt qua nút giao và chú ý đảm bảo an toàn", "Cả ý 1 và ý 2"};
		Answer answer_10_set8 = new Answer(answers_10_set8, 3, idList.get(184));
		answerRepo.save(answer_10_set8);

		String[] answers_11_set8 = {"Bấm còi liên tục thúc giục các phương tiện phía trước nhường đường", "Đi lên vỉa hè, tận dùng mọi khoảng trống để nhanh chóng thoát khỏi nơi ùn tắc", "Lấn sang trái đường cố gắng vượt lên xe khác", "Tất cả các ý nêu trên"};
		Answer answer_11_set8 = new Answer(answers_11_set8, 3, idList.get(185));
		answerRepo.save(answer_11_set8);

		String[] answers_12_set8 = {"Bật tín hiệu báo rẽ trước khi quay đầu, từ từ giảm tốc độ đến mức có thể dừng lại", "Chỉ quay đầu xe tại những nơi được phép quay đầu", "Quan sát an toàn các phương tiện tới từ phía trước, phía sau, hai bên đồng thời nhường đường cho xe từ bên phải và phía trước đi tới", "Tất cả các ý nêu trên"};
		Answer answer_12_set8 = new Answer(answers_12_set8, 3, idList.get(186));
		answerRepo.save(answer_12_set8);

		String[] answers_13_set8 = {"Biển 1.", "Biển 2.", "Không biển nào.", "Cả hai biển."};
		Answer answer_13_set8 = new Answer(answers_13_set8, 1, idList.get(187));
		answerRepo.save(answer_13_set8);

		String[] answers_14_set8 = {"Biển 1.", "Biển 2.", "Cả ba biển."};
		Answer answer_14_set8 = new Answer(answers_14_set8, 1, idList.get(188));
		answerRepo.save(answer_14_set8);

		String[] answers_15_set8 = {"Biển 1 và 2.", "Biển 1 và 3.", "Biển 2 và 3."};
		Answer answer_15_set8 = new Answer(answers_15_set8, 1, idList.get(189));
		answerRepo.save(answer_15_set8);

		String[] answers_16_set8 = {"Biển 1 và 2.", "Biển 1 và 3.", "Biển 2 và 3.", "Cả ba biển."};
		Answer answer_16_set8 = new Answer(answers_16_set8, 1, idList.get(190));
		answerRepo.save(answer_16_set8);

		String[] answers_17_set8 = {"Biển 1.", "Biển 1 và 3.", "Biển 2 và 3.", "Cả ba biển."};
		Answer answer_17_set8 = new Answer(answers_17_set8, 2, idList.get(191));
		answerRepo.save(answer_17_set8);

		String[] answers_18_set8 = {"Biển 1", "Biển 2"};
		Answer answer_18_set8 = new Answer(answers_18_set8, 1, idList.get(192));
		answerRepo.save(answer_18_set8);

		String[] answers_19_set8 = {"Biển 1", "Biển 2", "Biển 3", "Cả ba biển."};
		Answer answer_19_set8 = new Answer(answers_19_set8, 2, idList.get(193));
		answerRepo.save(answer_19_set8);

		String[] answers_20_set8 = {"Vạch 1", "Vạch 2", "Vạch 3", "Vạch 1 và 2."};
		Answer answer_20_set8 = new Answer(answers_20_set8, 3, idList.get(194));
		answerRepo.save(answer_20_set8);

		String[] answers_21_set8 = {"Xe mô tô", "Xe con."};
		Answer answer_21_set8 = new Answer(answers_21_set8, 1, idList.get(195));
		answerRepo.save(answer_21_set8);

		String[] answers_22_set8 = {"Chỉ mô tô", "Chỉ xe tải", "Cả 3 xe", "Chỉ mô tô và xe tải."};
		Answer answer_22_set8 = new Answer(answers_22_set8, 2, idList.get(196));
		answerRepo.save(answer_22_set8);

		String[] answers_23_set8 = {"Chỉ xe khách, mô tô", "Tất cả các loại xe trên", "Không xe nào chấp hành đúng quy tắc giao thông."};
		Answer answer_23_set8 = new Answer(answers_23_set8, 1, idList.get(197));
		answerRepo.save(answer_23_set8);

		String[] answers_24_set8 = {"Xe tải, xe con", "Xe khách, xe con", "Xe khách, xe tải."};
		Answer answer_24_set8 = new Answer(answers_24_set8, 2, idList.get(198));
		answerRepo.save(answer_24_set8);

		String[] answers_25_set8 = {"Vượt về phía bên phải để đi tiếp", "Giảm tốc độ chờ xe container rẽ xong rồi tiếp tục đi", "Vượt về phía bên trái để đi tiếp"};
		Answer answer_25_set8 = new Answer(answers_25_set8, 1, idList.get(199));
		answerRepo.save(answer_25_set8);
	}
	
	private void LoadQuestion() {
		License a1 = licenseRepo.findByName("A1");
		QuestionType kn = questionTypeRepo.findByCode("KN");
		QuestionType vh = questionTypeRepo.findByCode("VH");
		QuestionType kt = questionTypeRepo.findByCode("KT");
		QuestionType bb = questionTypeRepo.findByCode("BB");
		QuestionType sh = questionTypeRepo.findByCode("SH");
		QuestionSet set1 = questionSetRepo.findByName("Đề 1");
		QuestionSet set2 = questionSetRepo.findByName("Đề 2");
		QuestionSet set3 = questionSetRepo.findByName("Đề 3");
		QuestionSet set4 = questionSetRepo.findByName("Đề 4");
		QuestionSet set5 = questionSetRepo.findByName("Đề 5");
		QuestionSet set6 = questionSetRepo.findByName("Đề 6");
		QuestionSet set7 = questionSetRepo.findByName("Đề 7");
		QuestionSet set8 = questionSetRepo.findByName("Đề 8");
		
		questionRepo.deleteAll();
		Question questionA1_1 = new Question(1, "Phần của đường bộ được sử dụng cho các phương tiện giao thông qua lại là gì?", true, "", a1.getId(), set1.getId(), kn.getId());
		questionRepo.save(questionA1_1);

		Question questionA1_9 = new Question(2, "\"Phương tiện tham gia giao thông đường bộ\" gồm những loại nào?", true, "", a1.getId(), set1.getId(), kn.getId());
		questionRepo.save(questionA1_9);

		Question questionA1_17 = new Question(3, "Sử dụng rượu bia khi lái xe, nếu bị phát hiện thì bị xử lý như thế nào?", true, "", a1.getId(), set1.getId(), kn.getId());
		questionRepo.save(questionA1_17);

		Question questionA1_25 = new Question(4, "Bạn đang lái xe phía trước có một xe cứu thương đang phát tín hiệu ưu tiên bạn có được phép vượt hay không?", true, "", a1.getId(), set1.getId(), kn.getId());
		questionRepo.save(questionA1_25);

		Question questionA1_33 = new Question(5, "Hành vi sử dụng xe mô tô để kéo, đẩy xe mô tô khác bị hết xăng đến trạm mua xăng có được phép hay không?", true, "", a1.getId(), set1.getId(), kn.getId());
		questionRepo.save(questionA1_33);

		Question questionA1_41 = new Question(6, "Biển báo hiệu hình tròn có nền xanh lam có hình vẽ màu trắng là loại biển gì dưới đây?", true, "a1_41.jpg", a1.getId(), set1.getId(), kn.getId());
		questionRepo.save(questionA1_41);

		Question questionA1_49 = new Question(7, "Bạn đang lái xe trong khu vực đô thị từ 22 giờ đến 5 giờ sáng hôm sau và cần vượt một xe khác, bạn cần báo hiệu như thế nào để đảm bảo an toàn giao thông?", true, "", a1.getId(), set1.getId(), kn.getId());
		questionRepo.save(questionA1_49);

		Question questionA1_57 = new Question(8, "Người điều khiển phương tiện tham gia giao thông trong hầm đường bộ ngoài việc phải tuân thủ các quy tắc giao thông còn phải thực hiện những quy định nào dưới đây?", true, "", a1.getId(), set1.getId(), kn.getId());
		questionRepo.save(questionA1_57);

		Question questionA1_65 = new Question(9, "Trên đoạn đường hai chiều không có giải phân cách giữa, người lái xe không được vượt xe khác trong các trường hợp nào dưới đây?", true, "", a1.getId(), set1.getId(), kn.getId());
		questionRepo.save(questionA1_65);

		Question questionA1_73 = new Question(10, "Khi điều khiển xe chạy với tốc độ dưới 60 km/h, để đảm bảo khoảng cách an toàn giữa hai xe, người lái xe phải điều khiển xe như thế nào?", true, "", a1.getId(), set1.getId(), kn.getId());
		questionRepo.save(questionA1_73);

		Question questionA1_81 = new Question(11, "Để báo hiệu cho xe phía trước biết xe mô tô của bạn muốn vượt, bạn phải có tín hiệu như thế nào dưới đây?", true, "", a1.getId(), set1.getId(), kn.getId());
		questionRepo.save(questionA1_81);

		Question questionA1_89 = new Question(12, "Khi điều khiển xe mô tô tay ga xuống đường dốc dài, độ dốc cao, người lái xe cần thực hiện các thao tác nào dưới đây để đảm bảo an toàn?", true, "", a1.getId(), set1.getId(), kt.getId());
		questionRepo.save(questionA1_89);

		Question questionA1_97 = new Question(13, "Tay ga trên xe mô tô hai bánh có tác dụng gì trong các trường hợp dưới đây?", true, "", a1.getId(), set1.getId(), kt.getId());
		questionRepo.save(questionA1_97);

		Question questionA1_105 = new Question(14, "Biển nào báo hiệu sắp đến chỗ giao nhau nguy hiểm?", true, "a1_105.jpg", a1.getId(), set1.getId(), bb.getId());
		questionRepo.save(questionA1_105);

		Question questionA1_113 = new Question(15, "Biển nào báo hiệu \"Đường đôi\"?", true, "a1_113.jpg", a1.getId(), set1.getId(), bb.getId());
		questionRepo.save(questionA1_113);

		Question questionA1_121 = new Question(16, "Biển báo này có ý nghĩa gì?", true, "a1_121.jpg", a1.getId(), set1.getId(), bb.getId());
		questionRepo.save(questionA1_121);

		Question questionA1_129 = new Question(17, "Biển nào cấm các phương tiện giao thông đường bộ rẽ trái?", true, "a1_129.jpg", a1.getId(), set1.getId(), bb.getId());
		questionRepo.save(questionA1_129);

		Question questionA1_137 = new Question(18, "Biển báo này có ý nghĩa như thế nào?", true, "a1_137.jpg", a1.getId(), set1.getId(), bb.getId());
		questionRepo.save(questionA1_137);

		Question questionA1_145 = new Question(19, "Trong các biển dưới đây biển nào là biển \"Hết tốc độ tối đa cho phép\"?", true, "a1_145.jpg", a1.getId(), set1.getId(), bb.getId());
		questionRepo.save(questionA1_145);

		Question questionA1_153 = new Question(20, "Biển báo nào báo hiệu bắt đầu đoạn đường vào phạm vi khu dân cư, các phương tiện tham gia giao thông phải tuân thủ các quy định đi đường được áp dụng ở khu đông dân cư?", true, "a1_153.jpg", a1.getId(), set1.getId(), bb.getId());
		questionRepo.save(questionA1_153);

		Question questionA1_161 = new Question(21, "Vạch kẻ đường nào dưới đây là vạch phân chia hai chiều xe chạy ( vạch tim đường), xe không được lấn làn, không được đè lên vạch?", true, "a1_161.jpg", a1.getId(), set1.getId(), bb.getId());
		questionRepo.save(questionA1_161);

		Question questionA1_169 = new Question(22, "Xe nào được quyền đi trước trong trường hợp này?", true, "a1_169.jpg", a1.getId(), set1.getId(), sh.getId());
		questionRepo.save(questionA1_169);

		Question questionA1_177 = new Question(23, "Các xe đi theo hướng mũi tên, xe nào vi phạm quy tắc giao thông?", true, "a1_177.jpg", a1.getId(), set1.getId(), sh.getId());
		questionRepo.save(questionA1_177);

		Question questionA1_185 = new Question(24, "Thứ tự các xe đi như thế nào là đúng quy tắc giao thông?", true, "a1_185.jpg", a1.getId(), set1.getId(), sh.getId());
		questionRepo.save(questionA1_185);

		Question questionA1_193 = new Question(25, "Trong hình dưới, những xe nào vi phạm quy tắc giao thông?", true, "a1_193.jpg", a1.getId(), set1.getId(), sh.getId());
		questionRepo.save(questionA1_193);

		Question questionA1_2 = new Question(1, "\"Làn đường\" là gì?", true, "", a1.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA1_2);

		Question questionA1_10 = new Question(2, "\"Người tham gia giao thông đường bộ\" gồm những đối tượng nào?", true, "", a1.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA1_10);

		Question questionA1_18 = new Question(3, "Theo Luật phòng chống tác hại của rượu, bia, đối tượng nào dưới đây bị cấm sử dụng rượu bia khi tham gia giao thông?", true, "", a1.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA1_18);

		Question questionA1_26 = new Question(4, "Người điều khiển xe mô tô hai bánh, ba bánh, xe gắn máy có được phép sử dụng xe để kéo hoặc đẩy các phương tiện khác khi tham gia giao thông không?", true, "", a1.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA1_26);

		Question questionA1_34 = new Question(5, "Hành vi vận chuyển đồ vật cồng kềnh bằng xe mô tô, xe gắn máy khi tham gia giao thông có được phép hay không?", true, "", a1.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA1_34);

		Question questionA1_42 = new Question(6, "Biển báo hiệu hình chữ nhật hoặc hình vuông hoặc hình mũi tên nền xanh lam là loại biển gì dưới đây?", true, "a1_42.jpg", a1.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA1_42);

		Question questionA1_50 = new Question(7, "Khi điều khiển xe chạy trên đường biết có xe sau xin vượt nếu đủ điều kiện an toàn người lái xe phải làm gì?", true, "", a1.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA1_50);

		Question questionA1_58 = new Question(8, "Người ngồi trên xe mô tô 2 bánh, xe gắn máy phải đội mũ bảo hiểm có cài quai đúng quy cách khi nào?", true, "", a1.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA1_58);

		Question questionA1_66 = new Question(9, "Người lái xe mô tô xử lý như thế nào khi cho xe mô tô phía sau vượt?", true, "", a1.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA1_66);

		Question questionA1_74 = new Question(10, "Người lái xe phải giảm tốc độ thấp hơn tốc độ tối đa cho phép đến mức cần thiết, chú ý quan sát và chuẩn bị sẵn sàng những tình huống có thế xảy ra để phòng ngừa tai nạn trong các trường hợp nào dưới đây?", true, "", a1.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA1_74);

		Question questionA1_82 = new Question(11, "Người điều khiển xe mô tô phải giảm tốc độ và hết sức thận trọng khi qua những đoạn đường nào dưới đây?", true, "", a1.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA1_82);

		Question questionA1_90 = new Question(12, "Khi quay đầu xe, người lái xe cần phải quan sát và thực hiện các thao tác nào để đảm bảo an toàn giao thông?", true, "", a1.getId(), set2.getId(), kt.getId());
		questionRepo.save(questionA1_90);

		Question questionA1_98 = new Question(13, "Gương chiếu hậu của xe mô tô hai bánh, có tác dụng gì trong các trường hợp dưới đây?", true, "", a1.getId(), set2.getId(), kt.getId());
		questionRepo.save(questionA1_98);

		Question questionA1_106 = new Question(14, "Biển nào báo hiệu \"Giao nhau có tín hiệu đèn\"?", true, "a1_106.jpg", a1.getId(), set2.getId(), bb.getId());
		questionRepo.save(questionA1_106);

		Question questionA1_114 = new Question(15, "Biển nào báo hiệu \"Đường đôi\"?", true, "a1_114.jpg", a1.getId(), set2.getId(), bb.getId());
		questionRepo.save(questionA1_114);

		Question questionA1_122 = new Question(16, "Biển nào dưới đây xe gắn máy được phép đi vào?", true, "a1_122.jpg", a1.getId(), set2.getId(), bb.getId());
		questionRepo.save(questionA1_122);

		Question questionA1_130 = new Question(17, "Biển nào xe được phép quay đầu nhưng không được rẽ trái?", true, "a1_130.jpg", a1.getId(), set2.getId(), bb.getId());
		questionRepo.save(questionA1_130);

		Question questionA1_138 = new Question(18, "Chiều dài đoạn đường 500 m từ nơi đặt biển này, người lái xe có được phép bấm còi không?", true, "a1_138.jpg", a1.getId(), set2.getId(), bb.getId());
		questionRepo.save(questionA1_138);

		Question questionA1_146 = new Question(19, "Hiệu lực của biển \"Tốc độ tối đa cho phép\" hết tác dụng khi gặp biển nào dưới đây?", true, "a1_146.jpg", a1.getId(), set2.getId(), bb.getId());
		questionRepo.save(questionA1_146);

		Question questionA1_154 = new Question(20, "Biển nào báo hiệu \"Hướng đi thẳng phải theo\"?", true, "a1_154.jpg", a1.getId(), set2.getId(), bb.getId());
		questionRepo.save(questionA1_154);

		Question questionA1_162 = new Question(21, "Vạch kẻ đường nào dưới đây là vạch phân chia hai chiều xe chạy ( vạch tim đường)?", true, "a1_162.jpg", a1.getId(), set2.getId(), bb.getId());
		questionRepo.save(questionA1_162);

		Question questionA1_170 = new Question(22, "Theo tín hiệu đèn, xe nào được phép đi?", true, "a1_170.jpg", a1.getId(), set2.getId(), sh.getId());
		questionRepo.save(questionA1_170);

		Question questionA1_178 = new Question(23, "Thứ tự các xe đi như thế nào là đúng quy tắc giao thông?", true, "a1_178.jpg", a1.getId(), set2.getId(), sh.getId());
		questionRepo.save(questionA1_178);

		Question questionA1_186 = new Question(24, "Thứ tự các xe đi như thế nào là đúng quy tắc giao thông?", true, "a1_186.jpg", a1.getId(), set2.getId(), sh.getId());
		questionRepo.save(questionA1_186);

		Question questionA1_194 = new Question(25, "Trong hình dưới những xe nào vi phạm quy tắc giao thông?", true, "a1_194.jpg", a1.getId(), set2.getId(), sh.getId());
		questionRepo.save(questionA1_194);

		Question questionA1_3 = new Question(1, "Trong các khái niệm dưới đây, \"dải phân cách\" được hiểu như thế nào là đúng?", true, "", a1.getId(), set3.getId(), kn.getId());
		questionRepo.save(questionA1_3);

		Question questionA1_11 = new Question(2, "\"Người điều khiển phương tiện tham gia giao thông đường bộ\" gồm những đối tượng nào dưới đây?", true, "", a1.getId(), set3.getId(), kn.getId());
		questionRepo.save(questionA1_11);

		Question questionA1_19 = new Question(3, "Hành vi điều khiển xe cơ giới chạy quá tốc độ quy định, giành đường, vượt ẩu có bị nghiêm cấm hay không?", true, "", a1.getId(), set3.getId(), kn.getId());
		questionRepo.save(questionA1_19);

		Question questionA1_27 = new Question(4, "Khi điều khiển xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy, những hành vi buông cả hai tay; sử dụng xe để kéo, đẩy xe khác, vật khác; sử dụng chân chống của xe quệt xuống đường khi xe đang chạy có được phép hay không?", true, "", a1.getId(), set3.getId(), kn.getId());
		questionRepo.save(questionA1_27);

		Question questionA1_35 = new Question(5, "Người đủ bao nhiêu tuổi trở lên thì được điều khiển xe mô tô hai bánh, xe mô tô ba bánh có dung tích xi lanh từ 50 cm3 trở lên và các loại xe có kết cấu tương tự; xe ô tô tải, máy kéo có trọng tải dưới 3,5 tấn; xe ô tô chở người đến 9 chỗ ngồi?", true, "", a1.getId(), set3.getId(), kn.getId());
		questionRepo.save(questionA1_35);

		Question questionA1_43 = new Question(6, "Khi sử dụng giấy phép lái xe đã khai báo mất để điều khiển phương tiện cơ giới đường bộ, ngoài việc bị thu hồi giấy phép lái xe, chịu trách nhiệm trước pháp luật, người lái xe không được cấp giấy phép lái xe trong thời gian bao nhiêu năm?", true, "", a1.getId(), set3.getId(), kn.getId());
		questionRepo.save(questionA1_43);

		Question questionA1_51 = new Question(7, "Khi muốn chuyển hướng, người lái xe phải thực hiện như thế nào để đảm bảo an toàn giao thông?", true, "", a1.getId(), set3.getId(), kn.getId());
		questionRepo.save(questionA1_51);

		Question questionA1_59 = new Question(8, "Người điểu khiển xe mô tô hai bánh, xe gắn máy được phép chở tối đa 2 người trong những trường hợp nào?", true, "", a1.getId(), set3.getId(), kn.getId());
		questionRepo.save(questionA1_59);

		Question questionA1_67 = new Question(9, "Trong các trường hợp dưới đây, để đảm bảo an toàn khi tham gia giao thông, người lái xe mô tô cần thực hiện như thế nào?", true, "", a1.getId(), set3.getId(), kn.getId());
		questionRepo.save(questionA1_67);

		Question questionA1_75 = new Question(10, "Các phương tiện tham gia giao thông đường bộ (kể cả những xe có quyền ưu tiên) đều phải dừng lại bên phải đường của mình và trước vạch \"dừng xe\" tại các điểm giao cắt giữa đường bộ và đường sắt khi có báo hiệu dừng nào dưới đây?", true, "", a1.getId(), set3.getId(), kn.getId());
		questionRepo.save(questionA1_75);

		Question questionA1_83 = new Question(11, "Khi gặp xe buýt đang đang dừng đón, trả khách, người điều khiển xe mô tô phải xử lý như thế nào dưới đây để đảm bảo an toàn giao thông?", true, "", a1.getId(), set3.getId(), kn.getId());
		questionRepo.save(questionA1_83);

		Question questionA1_91 = new Question(12, "Khi tránh nhau trên đường hẹp, người lái xe cần phải chú ý những điểm nào để đảm bảo an toàn giao thông?", true, "", a1.getId(), set3.getId(), kt.getId());
		questionRepo.save(questionA1_91);

		Question questionA1_99 = new Question(13, "Để đảm bảo an toàn khi tham gia giao thông, người lái xe lái xe mô tô hai bánh cần điều khiển tay ga như thế nào trong các trường hợp dưới đây?", true, "", a1.getId(), set3.getId(), kt.getId());
		questionRepo.save(questionA1_99);

		Question questionA1_107 = new Question(14, "Biển nào báo hiệu \"Giao nhau với đường sắt có rào chắn\"?", true, "a1_107.jpg", a1.getId(), set3.getId(), bb.getId());
		questionRepo.save(questionA1_107);

		Question questionA1_115 = new Question(15, "Biển nào báo hiệu \"Giao nhau với đường hai chiều\"?", true, "a1_115.jpg", a1.getId(), set3.getId(), bb.getId());
		questionRepo.save(questionA1_115);

		Question questionA1_123 = new Question(16, "Biển nào báo hiệu cấm xe mô tô hai bánh đi vào?", true, "a1_123.jpg", a1.getId(), set3.getId(), bb.getId());
		questionRepo.save(questionA1_123);

		Question questionA1_131 = new Question(17, "Biển nào là biển \"Cấm đi ngược chiều\"?", true, "a1_131.jpg", a1.getId(), set3.getId(), bb.getId());
		questionRepo.save(questionA1_131);

		Question questionA1_139 = new Question(18, "Biển nào xe mô tô hai bánh không được đi vào?", true, "a1_139.jpg", a1.getId(), set3.getId(), bb.getId());
		questionRepo.save(questionA1_139);

		Question questionA1_147 = new Question(19, "Trong các biển dưới đây biển nào là biển \" Hết tốc độ tối thiểu\"?", true, "a1_147.jpg", a1.getId(), set3.getId(), bb.getId());
		questionRepo.save(questionA1_147);

		Question questionA1_155 = new Question(20, "Biển nào báo hiệu \"Đường một chiều\"?", true, "a1_155.jpg", a1.getId(), set3.getId(), bb.getId());
		questionRepo.save(questionA1_155);

		Question questionA1_163 = new Question(21, "Các vạch dưới đây có tác dụng gì?", true, "a1_163.jpg", a1.getId(), set3.getId(), bb.getId());
		questionRepo.save(questionA1_163);

		Question questionA1_171 = new Question(22, "Xe nào được quyền đi trước trong trường hợp này?", true, "a1_171.jpg", a1.getId(), set3.getId(), sh.getId());
		questionRepo.save(questionA1_171);

		Question questionA1_179 = new Question(23, "Xe nào được quyền đi trước trong trường hợp này?", true, "a1_179.jpg", a1.getId(), set3.getId(), sh.getId());
		questionRepo.save(questionA1_179);

		Question questionA1_187 = new Question(24, "Theo hướng mũi tên, những hướng nào xe gắn máy đi được?", true, "a1_187.jpg", a1.getId(), set3.getId(), sh.getId());
		questionRepo.save(questionA1_187);

		Question questionA1_195 = new Question(25, "Xe nào vi phạm quy tắc giao thông?", true, "a1_195.jpg", a1.getId(), set3.getId(), sh.getId());
		questionRepo.save(questionA1_195);

		Question questionA1_4 = new Question(1, "\"Dải phân cách\" trên đường bộ gồm những loại nào?", true, "", a1.getId(), set4.getId(), kn.getId());
		questionRepo.save(questionA1_4);

		Question questionA1_12 = new Question(2, "Khái niệm \"người điều khiển giao thông\" được hiểu như thế nào là đúng?", true, "", a1.getId(), set4.getId(), kn.getId());
		questionRepo.save(questionA1_12);

		Question questionA1_20 = new Question(3, "Khi lái xe trong khu đô thị và đông dân cư trừ các khu vực có biển cấm sử dụng còi, người lái xe được sử dụng còi như thế nào trong các trường hợp dưới đây?", true, "", a1.getId(), set4.getId(), kn.getId());
		questionRepo.save(questionA1_20);

		Question questionA1_28 = new Question(4, "Khi điều khiển xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy, những hành vi nào không được phép?", true, "", a1.getId(), set4.getId(), kn.getId());
		questionRepo.save(questionA1_28);

		Question questionA1_36 = new Question(5, "Người đủ 16 tuổi được điều khiển các loại xe nào dưới đây?", true, "", a1.getId(), set4.getId(), kn.getId());
		questionRepo.save(questionA1_36);

		Question questionA1_44 = new Question(6, "Khi gặp hiệu lệnh như dưới đây của cảnh sát giao thông thì người tham gia giao thông phải đi như thế nào là đúng quy tắc giao thông?", true, "a1_44.jpg", a1.getId(), set4.getId(), kn.getId());
		questionRepo.save(questionA1_44);

		Question questionA1_52 = new Question(7, "Khi tránh xe đi ngược chiều, các xe phải nhường đường như thế nào là đúng quy tắc giao thông?", true, "", a1.getId(), set4.getId(), kn.getId());
		questionRepo.save(questionA1_52);

		Question questionA1_60 = new Question(8, "Người điều khiển xe mô tô hai bánh, xe gắn máy không được thực hiện những hành vi nào dưới đây?", true, "", a1.getId(), set4.getId(), kn.getId());
		questionRepo.save(questionA1_60);

		Question questionA1_68 = new Question(9, "Đường bộ trong khu vực đông dân cư gồm những đoạn đường nào dưới đây?", true, "", a1.getId(), set4.getId(), kn.getId());
		questionRepo.save(questionA1_68);

		Question questionA1_76 = new Question(10, "Tác dụng của mũ bảo hiểm đối với người ngồi trên xe mô tô hai bánh trong trường hợp xảy ra tai nạn giao thông là gì?", true, "", a1.getId(), set4.getId(), kn.getId());
		questionRepo.save(questionA1_76);

		Question questionA1_84 = new Question(11, "Khái niệm về văn hóa giao thông được hiểu như thế nào là đúng?", true, "", a1.getId(), set4.getId(), vh.getId());
		questionRepo.save(questionA1_84);

		Question questionA1_92 = new Question(12, "Khi điều khiển xe trên đường vòng người lái xe cần phải làm gì để đảm bảo an toàn?", true, "", a1.getId(), set4.getId(), kt.getId());
		questionRepo.save(questionA1_92);

		Question questionA1_100 = new Question(13, "Kỹ thuật cơ bản để giữ thăng bằng khi điều khiển xe mô tô đi trên đường gồ ghề như thế nào trong các trường hợp dưới đây?", true, "", a1.getId(), set4.getId(), kt.getId());
		questionRepo.save(questionA1_100);

		Question questionA1_108 = new Question(14, "Biển nào báo hiệu nguy hiểm giao nhau với đường sắt?", true, "a1_108.jpg", a1.getId(), set4.getId(), bb.getId());
		questionRepo.save(questionA1_108);

		Question questionA1_116 = new Question(15, "Biển nào báo hiệu \"Đường hai chiều\"?", true, "a1_116.jpg", a1.getId(), set4.getId(), bb.getId());
		questionRepo.save(questionA1_116);

		Question questionA1_124 = new Question(16, "khi gặp biển nào thì xe mô tô hai bánh được đi vào?", true, "a1_124.jpg", a1.getId(), set4.getId(), bb.getId());
		questionRepo.save(questionA1_124);

		Question questionA1_132 = new Question(17, "Biển nào dưới đây các phương tiện không được phép đi vào?", true, "a1_132.jpg", a1.getId(), set4.getId(), bb.getId());
		questionRepo.save(questionA1_132);

		Question questionA1_140 = new Question(18, "Biển nào xe mô tô hai bánh được đi vào?", true, "a1_140.jpg", a1.getId(), set4.getId(), bb.getId());
		questionRepo.save(questionA1_140);

		Question questionA1_148 = new Question(19, "Biển nào dưới đây báo hiệu hết cấm vượt?", true, "a1_148.jpg", a1.getId(), set4.getId(), bb.getId());
		questionRepo.save(questionA1_148);

		Question questionA1_156 = new Question(20, "Biển nào chỉ dẫn cho người đi bộ sử dụng cầu vượt qua đường?", true, "a1_156.jpg", a1.getId(), set4.getId(), bb.getId());
		questionRepo.save(questionA1_156);

		Question questionA1_164 = new Question(21, "Khi gặp vạch kẻ đường nào các xe được phép đè vạch?", true, "a1_164.jpg", a1.getId(), set4.getId(), bb.getId());
		questionRepo.save(questionA1_164);

		Question questionA1_172 = new Question(22, "Các xe đi theo hướng mũi tên, xe nào chấp hành đúng quy tắc giao thông?", true, "a1_172.jpg", a1.getId(), set4.getId(), sh.getId());
		questionRepo.save(questionA1_172);

		Question questionA1_180 = new Question(23, "Các xe đi theo thứ tự nào là đúng quy tắc giao thông đường bộ?", true, "a1_180.jpg", a1.getId(), set4.getId(), sh.getId());
		questionRepo.save(questionA1_180);

		Question questionA1_188 = new Question(24, "Theo hướng mũi tên, những hướng nào xe mô tô được phép đi?", true, "a1_188.jpg", a1.getId(), set4.getId(), sh.getId());
		questionRepo.save(questionA1_188);

		Question questionA1_196 = new Question(25, "Các xe đi theo thứ nào là đúng quy tắc giao thông đường bộ?", true, "a1_196.jpg", a1.getId(), set4.getId(), sh.getId());
		questionRepo.save(questionA1_196);

		Question questionA1_5 = new Question(1, "Người lái xe được hiểu như thế nào trong các khái niệm dưới đây?", true, "", a1.getId(), set5.getId(), kn.getId());
		questionRepo.save(questionA1_5);

		Question questionA1_13 = new Question(2, "Trong các khái niệm dưới đây khái niệm \"dừng xe\" được hiểu như thế nào là đúng?", true, "", a1.getId(), set5.getId(), kn.getId());
		questionRepo.save(questionA1_13);

		Question questionA1_21 = new Question(3, "Người lái xe sử dụng đèn như thế nào khi lái xe trong khu đô thị và đông dân cư vào ban đêm?", true, "", a1.getId(), set5.getId(), kn.getId());
		questionRepo.save(questionA1_21);

		Question questionA1_29 = new Question(4, "Người ngồi trên xe mô tô hai bánh, ba bánh, xe gắn máy khi tham gia giao thông có được mang, vác vật cồng kềnh hay không?", true, "", a1.getId(), set5.getId(), kn.getId());
		questionRepo.save(questionA1_29);

		Question questionA1_37 = new Question(5, "Người có GPLX mô tô hạng A1 không được phép điều khiển loại xe nào dưới đây?", true, "", a1.getId(), set5.getId(), kn.getId());
		questionRepo.save(questionA1_37);

		Question questionA1_45 = new Question(6, "Khi gặp hiệu lệnh như dưới đây của cảnh sát giao thông thì người tham gia giao thông phải đi như thế nào là đúng quy tắc giao thông?", true, "a1_45.jpg", a1.getId(), set5.getId(), kn.getId());
		questionRepo.save(questionA1_45);

		Question questionA1_53 = new Question(7, "Bạn đang lái xe trên đường hẹp, xuống dốc và gặp một xe đang đi lên dốc, bạn cần làm gì?", true, "", a1.getId(), set5.getId(), kn.getId());
		questionRepo.save(questionA1_53);

		Question questionA1_61 = new Question(8, "Người điều khiển xe mô tô hai bánh, xe gắn máy có được đi xe dàn hàng ngang; đi xe vào phần đường dành cho người đi bộ và phương tiện khác; sử dụng ô, điện thoại di động, thiết bị âm thanh (trừ thiết bị trợ thính) hay không?", true, "", a1.getId(), set5.getId(), kn.getId());
		questionRepo.save(questionA1_61);

		Question questionA1_69 = new Question(9, "Tốc độ tối đa cho phép đối với xe máy chuyên dùng, xe gắn máy (kể cả xe máy điện) và các loại xe tương tự trên đường bộ (trừ đường cao tốc) không được vượt quá bao nhiêu km/h?", true, "", a1.getId(), set5.getId(), kn.getId());
		questionRepo.save(questionA1_69);

		Question questionA1_77 = new Question(10, "Tại nơi đường giao nhau, người lái xe đang đi trên đường không ưu tiên phải xử lý như thế nào là đúng quy tắc giao thông?", true, "", a1.getId(), set5.getId(), kn.getId());
		questionRepo.save(questionA1_77);

		Question questionA1_85 = new Question(11, "Trong các hành vi dưới đây, người lái xe mô tô có văn hóa giao thông phải ứng xử như thế nào?", true, "", a1.getId(), set5.getId(), vh.getId());
		questionRepo.save(questionA1_85);

		Question questionA1_93 = new Question(12, "Để đạt được hiệu quả phanh cao nhất, người lái xe mô tô phải sử dụng các kỹ năng như thế nào dưới đây?", true, "", a1.getId(), set5.getId(), kt.getId());
		questionRepo.save(questionA1_93);

		Question questionA1_101 = new Question(13, "Biển nào báo hiệu \"Đường giao nhau\" của các tuyến đường cùng cấp?", true, "a1_101.jpg", a1.getId(), set5.getId(), bb.getId());
		questionRepo.save(questionA1_101);

		Question questionA1_109 = new Question(14, "Biển nào báo hiệu đường sắt giao nhau với đường bộ không có rào chắn?", true, "a1_109.jpg", a1.getId(), set5.getId(), bb.getId());
		questionRepo.save(questionA1_109);

		Question questionA1_117 = new Question(15, "Biển nào báo hiệu \"Giao nhau với đường hai chiều\"?", true, "a1_117.jpg", a1.getId(), set5.getId(), bb.getId());
		questionRepo.save(questionA1_117);

		Question questionA1_125 = new Question(16, "Biển nào cấm quay đầu xe?", true, "a1_125.jpg", a1.getId(), set5.getId(), bb.getId());
		questionRepo.save(questionA1_125);

		Question questionA1_133 = new Question(17, "Khi gặp biển nào xe ưu tiên theo luật định vẫn phải dừng lại?", true, "a1_133.jpg", a1.getId(), set5.getId(), bb.getId());
		questionRepo.save(questionA1_133);

		Question questionA1_141 = new Question(18, "Gặp biển nào người lái xe phải nhường đường cho người đi bộ?", true, "a1_141.jpg", a1.getId(), set5.getId(), bb.getId());
		questionRepo.save(questionA1_141);

		Question questionA1_149 = new Question(19, "Trong các biển dưới đây biển nào là biển \"Hết mọi lệnh cấm\"?", true, "a1_149.jpg", a1.getId(), set5.getId(), bb.getId());
		questionRepo.save(questionA1_149);

		Question questionA1_157 = new Question(20, "Biển nào chỉ dẫn cho người đi bộ sử dụng hầm chui qua đường?", true, "a1_157.jpg", a1.getId(), set5.getId(), bb.getId());
		questionRepo.save(questionA1_157);

		Question questionA1_165 = new Question(21, "Vạch dưới đây có ý nghĩa gì?", true, "a1_165.jpg", a1.getId(), set5.getId(), bb.getId());
		questionRepo.save(questionA1_165);

		Question questionA1_173 = new Question(22, "Trong hình dưới đây, xe nào chấp hành đúng quy tắc giao thông?", true, "a1_173.jpg", a1.getId(), set5.getId(), sh.getId());
		questionRepo.save(questionA1_173);

		Question questionA1_181 = new Question(23, "Trong trường hợp này xe nào đỗ vi phạm quy tắc giao thông?", true, "a1_181.jpg", a1.getId(), set5.getId(), sh.getId());
		questionRepo.save(questionA1_181);

		Question questionA1_189 = new Question(24, "Trường hợp này xe nào được quyền đi trước?", true, "a1_189.jpg", a1.getId(), set5.getId(), sh.getId());
		questionRepo.save(questionA1_189);

		Question questionA1_197 = new Question(25, "Bạn xử lý như thế nào trong trường hợp này?", true, "a1_197.jpg", a1.getId(), set5.getId(), sh.getId());
		questionRepo.save(questionA1_197);

		Question questionA1_6 = new Question(1, "Đường mà trên đó phương tiện tham gia giao thông được các phương tiện giao thông đến từ hướng khác nhường đường khi qua nơi đường giao nhau, được cắm biển báo hiệu đường ưu tiên là loại đường gì?", true, "", a1.getId(), set6.getId(), kn.getId());
		questionRepo.save(questionA1_6);

		Question questionA1_14 = new Question(2, "Khái niệm \"đỗ xe\" được hiểu như thế nào là đúng?", true, "", a1.getId(), set6.getId(), kn.getId());
		questionRepo.save(questionA1_14);

		Question questionA1_22 = new Question(3, "Trong trường hợp đặc biệt, để được lắp đặt, sử dụng còi, đèn không đúng với thiết kế của nhà sản xuất đối với từng loại xe cơ giới bạn phải đảm bảo yêu cầu nào dưới đây?", true, "", a1.getId(), set6.getId(), kn.getId());
		questionRepo.save(questionA1_22);

		Question questionA1_30 = new Question(4, "Người ngồi trên xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy khi tham gia giao thông có được bám, kéo hoặc đẩy các phương tiện khác không?", true, "", a1.getId(), set6.getId(), kn.getId());
		questionRepo.save(questionA1_30);

		Question questionA1_38 = new Question(5, "Người có GPLX mô tô hạng A1 được phép điều khiển loại xe nào dưới đây?", true, "", a1.getId(), set6.getId(), kn.getId());
		questionRepo.save(questionA1_38);

		Question questionA1_46 = new Question(6, "Tại nơi có biển báo hiệu cố định lại có báo hiệu tạm thời thì người tham gia giao thông phải chấp hành hiệu lệnh của báo hiệu nào?", true, "", a1.getId(), set6.getId(), kn.getId());
		questionRepo.save(questionA1_46);

		Question questionA1_54 = new Question(7, "Tại nơi đường giao nhau, người lái xe đang đi trên đường không ưu tiên phải nhường đường như thế nào là đúng quy tắc giao thông?", true, "", a1.getId(), set6.getId(), kn.getId());
		questionRepo.save(questionA1_54);

		Question questionA1_62 = new Question(8, "Người lái xe phải giảm tốc độ thấp hơn tốc độ tối đa cho phép (có thể dừng lại một cách an toàn) trong trường hợp nào dưới đây?", true, "", a1.getId(), set6.getId(), kn.getId());
		questionRepo.save(questionA1_62);

		Question questionA1_70 = new Question(9, "Trên đường bộ (trừ đường cao tốc) trong khu vực đông dân cư, đường đôi có dải phân cách giữa, xe mô tô hai bánh, ô tô chở người đến 30 chỗ tham gia giao thông với tốc độ tối đa cho phép là bao nhiêu?", true, "", a1.getId(), set6.getId(), kn.getId());
		questionRepo.save(questionA1_70);

		Question questionA1_78 = new Question(10, "Người lái xe phải xử lý như thế nào khi quan sát phía trước thấy người đi bộ đang sang đường tại nơi có vạch đường dành cho người đi bộ để đảm bảo an toàn?", true, "", a1.getId(), set6.getId(), kn.getId());
		questionRepo.save(questionA1_78);

		Question questionA1_86 = new Question(11, "Trong các hành vi dưới đây, người lái xe ô tô, mô tô có văn hóa giao thông phải ứng xử như thế nào?", true, "", a1.getId(), set6.getId(), vh.getId());
		questionRepo.save(questionA1_86);

		Question questionA1_94 = new Question(12, "Khi đang lái xe mô tô và ô tô, nếu có nhu cầu sử dụng điện thoại để nhắn tin hoặc gọi điện, người lái xe phải thực hiện như thế nào trong các tình huống nêu dưới đây?", true, "", a1.getId(), set6.getId(), kt.getId());
		questionRepo.save(questionA1_94);

		Question questionA1_102 = new Question(13, "Biển nào báo hiệu \"Giao nhau với đường không ưu tiên\"?", true, "a1_102.jpg", a1.getId(), set6.getId(), bb.getId());
		questionRepo.save(questionA1_102);

		Question questionA1_110 = new Question(14, "Biển nào báo hiệu sắp đến chỗ giao nhau giữa đường bộ và đường sắt?", true, "a1_110.jpg", a1.getId(), set6.getId(), bb.getId());
		questionRepo.save(questionA1_110);

		Question questionA1_118 = new Question(15, "Biển nào báo hiệu \"Chú ý chướng ngại vật\"?", true, "a1_118.jpg", a1.getId(), set6.getId(), bb.getId());
		questionRepo.save(questionA1_118);

		Question questionA1_126 = new Question(16, "Biển nào cấm xe rẽ trái?", true, "a1_126.jpg", a1.getId(), set6.getId(), bb.getId());
		questionRepo.save(questionA1_126);

		Question questionA1_134 = new Question(17, "Biển nào cấm tất cả các loại xe cơ giới và thô sơ đi lại trên đường, trừ xe ưu tiên theo luật định (nếu đường vẫn cho xe chạy được)?", true, "a1_134.jpg", a1.getId(), set6.getId(), bb.getId());
		questionRepo.save(questionA1_134);

		Question questionA1_142 = new Question(18, "Biển nào chỉ đường dành cho người đi bộ, các loại xe không được đi vào khi gặp biển này?", true, "a1_142.jpg", a1.getId(), set6.getId(), bb.getId());
		questionRepo.save(questionA1_142);

		Question questionA1_150 = new Question(19, "Biển nào báo hiệu \"Nơi đỗ xe cho người tàn tật\"?", true, "a1_150.jpg", a1.getId(), set6.getId(), bb.getId());
		questionRepo.save(questionA1_150);

		Question questionA1_158 = new Question(20, "Gặp biển báo này, người tham gia giao thông phải xử lý như thế nào?", true, "a1_158.jpg", a1.getId(), set6.getId(), bb.getId());
		questionRepo.save(questionA1_158);

		Question questionA1_166 = new Question(21, "Các xe đi như thế nào là đúng quy tắc giao thông?", true, "a1_166.jpg", a1.getId(), set6.getId(), sh.getId());
		questionRepo.save(questionA1_166);

		Question questionA1_174 = new Question(22, "Các xe đi theo hướng mũi tên, xe nào vi phạm quy tắc giao thông?", true, "a1_174.jpg", a1.getId(), set6.getId(), sh.getId());
		questionRepo.save(questionA1_174);

		Question questionA1_182 = new Question(23, "Xe nào đỗ vi phạm quy tắc giao thông?", true, "a1_182.jpg", a1.getId(), set6.getId(), sh.getId());
		questionRepo.save(questionA1_182);

		Question questionA1_190 = new Question(24, "Bạn có được phép vượt xe mô tô phía trước không?", true, "a1_190.jpg", a1.getId(), set6.getId(), sh.getId());
		questionRepo.save(questionA1_190);

		Question questionA1_198 = new Question(25, "Xe nào dừng đúng theo quy tắc giao thông?", true, "a1_198.jpg", a1.getId(), set6.getId(), sh.getId());
		questionRepo.save(questionA1_198);

		Question questionA1_7 = new Question(1, "Khái niệm \"phương tiện giao thông cơ giới đường bộ\" được hiểu thế nào là đúng?", true, "", a1.getId(), set7.getId(), kn.getId());
		questionRepo.save(questionA1_7);

		Question questionA1_15 = new Question(2, "Cuộc đua xe chỉ được thực hiện khi nào?", true, "", a1.getId(), set7.getId(), kn.getId());
		questionRepo.save(questionA1_15);

		Question questionA1_23 = new Question(3, "Ở phần đường dành cho người đi bộ qua đường, trên cầu, đầu cầu, đường cao tốc, đường hẹp, đường dốc, tại nơi đường bộ giao nhau cùng mức với đường sắt có được quay đầu xe hay không?", true, "", a1.getId(), set7.getId(), kn.getId());
		questionRepo.save(questionA1_23);

		Question questionA1_31 = new Question(4, "Người ngồi trên xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy khi tham gia giao thông có được sử dụng ô khi trời mưa hay không?", true, "", a1.getId(), set7.getId(), kn.getId());
		questionRepo.save(questionA1_31);

		Question questionA1_39 = new Question(5, "Biển báo hiệu có dạng hình tròn, viền đỏ, nền trắng, trên nền có hình vẽ hoặc chữ số, chữ viết màu đen là loại biển gì dưới đây?", true, "a1_39.jpg", a1.getId(), set7.getId(), kn.getId());
		questionRepo.save(questionA1_39);

		Question questionA1_47 = new Question(6, "Trên đường có nhiều làn đường cho xe đi cùng chiều được phân biệt bằng vạch kẻ phân làn đường, người điều khiển phương tiện phải cho xe đi như thế nào?", true, "", a1.getId(), set7.getId(), kn.getId());
		questionRepo.save(questionA1_47);

		Question questionA1_55 = new Question(7, "Tại nơi đường giao nhau không có báo hiệu đi theo vòng xuyến, người điều khiển phương tiện phải nhường đường như thế nào là đúng quy tắc giao thông?", true, "", a1.getId(), set7.getId(), kn.getId());
		questionRepo.save(questionA1_55);

		Question questionA1_63 = new Question(8, "Tại ngã ba hoặc ngã tư không có đảo an toàn, người lái xe phải nhường đường như thế nào là đúng trong các trường hợp dưới đây?", true, "", a1.getId(), set7.getId(), kn.getId());
		questionRepo.save(questionA1_63);

		Question questionA1_71 = new Question(9, "Trên đường bộ (trừ đường cao tốc) trong khu vực đông dân cư, đường hai chiều không có dải phân cách giữa, xe mô tô hai bánh, ô tô chở người đến 30 chỗ tham gia giao thông với tốc độ tối đa cho phép là bao nhiêu?", true, "", a1.getId(), set7.getId(), kn.getId());
		questionRepo.save(questionA1_71);

		Question questionA1_79 = new Question(10, "Theo Luật Giao thông đường bộ, tín hiệu đèn giao thông gồm 3 màu nào dưới đây?", true, "", a1.getId(), set7.getId(), kn.getId());
		questionRepo.save(questionA1_79);

		Question questionA1_87 = new Question(11, "Khi xảy ra tai nạn giao thông, có người bị thương nghiêm trọng, người lái xe và người có mặt tại hiện trường vụ tai nạn phải thực hiện các công việc gì dưới đây?", true, "", a1.getId(), set7.getId(), vh.getId());
		questionRepo.save(questionA1_87);

		Question questionA1_95 = new Question(12, "Những thói quen nào dưới đây khi điều khiển xe mô tô tay ga tham gia giao thông dễ gây tai nạn nguy hiểm?", true, "", a1.getId(), set7.getId(), kt.getId());
		questionRepo.save(questionA1_95);

		Question questionA1_103 = new Question(13, "Biển nào báo hiệu \"Giao nhau với đường ưu tiên\"?", true, "a1_103.jpg", a1.getId(), set7.getId(), bb.getId());
		questionRepo.save(questionA1_103);

		Question questionA1_111 = new Question(14, "Biển nào báo hiệu \"Đường bị thu hẹp\"?", true, "a1_111.jpg", a1.getId(), set7.getId(), bb.getId());
		questionRepo.save(questionA1_111);

		Question questionA1_119 = new Question(15, "Gặp biển nào người tham gia giao thông phải đi chậm và thận trọng đề phòng khả năng xuất hiện và di chuyển bất ngờ của trẻ em trên mặt đường?", true, "a1_119.jpg", a1.getId(), set7.getId(), bb.getId());
		questionRepo.save(questionA1_119);

		Question questionA1_127 = new Question(16, "Khi gặp biển nào xe được rẽ trái?", true, "a1_127.jpg", a1.getId(), set7.getId(), bb.getId());
		questionRepo.save(questionA1_127);

		Question questionA1_135 = new Question(17, "Gặp biển nào xe xích lô được phép đi vào?", true, "a1_135.jpg", a1.getId(), set7.getId(), bb.getId());
		questionRepo.save(questionA1_135);

		Question questionA1_143 = new Question(18, "Biển nào báo hiệu \"Đường dành cho xe thô sơ\"?", true, "a1_143.jpg", a1.getId(), set7.getId(), bb.getId());
		questionRepo.save(questionA1_143);

		Question questionA1_151 = new Question(19, "Biển nào cho phép xe rẽ trái?", true, "a1_151.jpg", a1.getId(), set7.getId(), bb.getId());
		questionRepo.save(questionA1_151);

		Question questionA1_159 = new Question(20, "Biển số 1 có ý nghĩa gì?", true, "a1_159.jpg", a1.getId(), set7.getId(), bb.getId());
		questionRepo.save(questionA1_159);

		Question questionA1_167 = new Question(21, "Theo hướng mũi tên, xe nào được phép đi?", true, "a1_167.jpg", a1.getId(), set7.getId(), sh.getId());
		questionRepo.save(questionA1_167);

		Question questionA1_175 = new Question(22, "Các xe đi theo hướng mũi tên, xe nào vi phạm quy tắc giao thông?", true, "a1_175.jpg", a1.getId(), set7.getId(), sh.getId());
		questionRepo.save(questionA1_175);

		Question questionA1_183 = new Question(23, "Xe nào đỗ vi phạm quy tắc giao thông?", true, "a1_183.jpg", a1.getId(), set7.getId(), sh.getId());
		questionRepo.save(questionA1_183);

		Question questionA1_191 = new Question(24, "Theo tín hiệu đèn của xe cơ giới, xe nào vi phạm quy tắc giao thông?", true, "a1_191.jpg", a1.getId(), set7.getId(), sh.getId());
		questionRepo.save(questionA1_191);

		Question questionA1_199 = new Question(25, "Xe của bạn đang di chuyển gần đến khu vực giao cắt với đường sắt, khi rào chắn đang dịch chuyển, bạn điều khiển xe như thế nào là đúng quy tắc giao thông?", true, "a1_199.jpg", a1.getId(), set7.getId(), sh.getId());
		questionRepo.save(questionA1_199);

		Question questionA1_8 = new Question(1, "Khái niệm \"phương tiện giao thông thô sơ đường bộ\" được hiểu thế nào là đúng?", true, "", a1.getId(), set8.getId(), kn.getId());
		questionRepo.save(questionA1_8);

		Question questionA1_16 = new Question(2, "Người điều khiển phương tiện giao thông đường bộ mà trong cơ thể có chất ma tuý có bị nghiêm cấm hay không?", true, "", a1.getId(), set8.getId(), kn.getId());
		questionRepo.save(questionA1_16);

		Question questionA1_24 = new Question(3, "Bạn đang lái xe phía trước có một xe cảnh sát giao thông không phát tín hiệu ưu tiên bạn có được phép vượt hay không?", true, "", a1.getId(), set8.getId(), kn.getId());
		questionRepo.save(questionA1_24);

		Question questionA1_32 = new Question(4, "Khi đang lên dốc người ngồi trên xe mô tô có được kéo theo người đang điều khiển xe đạp hay không?", true, "", a1.getId(), set8.getId(), kn.getId());
		questionRepo.save(questionA1_32);

		Question questionA1_40 = new Question(5, "Biển báo hiệu có dạng tam giác đều, viền đỏ, viền màu vàng, trên có hình vẽ màu đen là loại biển gì dưới đây?", true, "a1_40.jpg", a1.getId(), set8.getId(), kn.getId());
		questionRepo.save(questionA1_40);

		Question questionA1_48 = new Question(6, "Trên đường một chiều có vạch kẻ phân làn đường, xe thô sơ và xe cơ giới phải đi như thế nào là đúng quy tắc giao thông?", true, "", a1.getId(), set8.getId(), kn.getId());
		questionRepo.save(questionA1_48);

		Question questionA1_56 = new Question(7, "Tại nơi đường bộ giao nhau cùng mức với đường sắt chỉ có đèn tín hiệu hoặc chuông báo hiệu, khi đèn tín hiệu màu đỏ đã bật sáng hoặc có tiếng chuông báo hiệu, người tham gia giao thông phải dừng lại ngay và giữ khoảng cách tối thiểu bao nhiêu mét tính từ ray gần nhất?", true, "", a1.getId(), set8.getId(), kn.getId());
		questionRepo.save(questionA1_56);

		Question questionA1_64 = new Question(8, "Khi điều khiển xe cơ giới, người lái xe phải bật đèn tín hiệu báo rẽ trong trường hợp nào sau đây?", true, "", a1.getId(), set8.getId(), kn.getId());
		questionRepo.save(questionA1_64);

		Question questionA1_72 = new Question(9, "Trên đường bộ (trừ đường cao tốc) trong khu vực đông dân cư, đường hai chiều hoặc đường một chiều có một làn xe cơ giới, loại xe nào dưới đây được tham gia giao thông với tốc độ tối đa cho phép là 50 km/h?", true, "", a1.getId(), set8.getId(), kn.getId());
		questionRepo.save(questionA1_72);

		Question questionA1_80 = new Question(10, "Tại nơi giao nhau, khi đèn điều khiển giao thông có tín hiệu màu vàng, người điều khiển giao thông phải chấp hành như thế nào là đúng quy tắc giao thông?", true, "", a1.getId(), set8.getId(), kn.getId());
		questionRepo.save(questionA1_80);

		Question questionA1_88 = new Question(11, "Trên đường đang xảy ra ùn tắc những hành vi nào sau đây là thiếu văn hóa khi tham gia giao thông?", true, "", a1.getId(), set8.getId(), vh.getId());
		questionRepo.save(questionA1_88);

		Question questionA1_96 = new Question(12, "Khi điều khiển xe mô tô quay đầu người lái xe cần thực hiện như thế nào để đảm bảo an toàn?", true, "", a1.getId(), set8.getId(), kt.getId());
		questionRepo.save(questionA1_96);

		Question questionA1_104 = new Question(13, "Biển nào báo hiệu, chỉ dẫn xe đi trên đường này được quyền ưu tiên qua nơi giao nhau?", true, "a1_104.jpg", a1.getId(), set8.getId(), bb.getId());
		questionRepo.save(questionA1_104);

		Question questionA1_112 = new Question(14, "Khi gặp biển nào, người lái xe phải giảm tốc độ, chú ý xe đi ngược chiều, xe đi ở phía đường bị hẹp phải nhường đường cho xe đi ngược chiều?", true, "a1_112.jpg", a1.getId(), set8.getId(), bb.getId());
		questionRepo.save(questionA1_112);

		Question questionA1_120 = new Question(15, "Biển nào chỉ dẫn nới bắt đầu đoạn đường dành cho người đi bộ?", true, "a1_120.jpg", a1.getId(), set8.getId(), bb.getId());
		questionRepo.save(questionA1_120);

		Question questionA1_128 = new Question(16, "Biển nào cấm các phương tiện giao thông đường bộ rẽ phải?", true, "a1_128.jpg", a1.getId(), set8.getId(), bb.getId());
		questionRepo.save(questionA1_128);

		Question questionA1_136 = new Question(17, "Gặp biển nào xe lam, xe xích lô máy được phép đi vào?", true, "a1_136.jpg", a1.getId(), set8.getId(), bb.getId());
		questionRepo.save(questionA1_136);

		Question questionA1_144 = new Question(18, "Biển nào (đặt trước ngã ba, ngã tư) cho phép xe được rẽ sang hướng khác?", true, "a1_144.jpg", a1.getId(), set8.getId(), bb.getId());
		questionRepo.save(questionA1_144);

		Question questionA1_152 = new Question(19, "Biển nào xe quay đầu không bị cấm?", true, "a1_152.jpg", a1.getId(), set8.getId(), bb.getId());
		questionRepo.save(questionA1_152);

		Question questionA1_160 = new Question(20, "Vạch kẻ đường nào dưới đây là vạch phân chia các làn xe cùng chiều?", true, "a1_160.jpg", a1.getId(), set8.getId(), bb.getId());
		questionRepo.save(questionA1_160);

		Question questionA1_168 = new Question(21, "Trong trường hợp này, thứ tự các xe đi như thế nào là đúng quy tắc giao thông?", true, "a1_168.jpg", a1.getId(), set8.getId(), sh.getId());
		questionRepo.save(questionA1_168);

		Question questionA1_176 = new Question(22, "Các xe đi theo hướng mũi tên xe nào vi phạm quy tắc giao thông?", true, "a1_176.jpg", a1.getId(), set8.getId(), sh.getId());
		questionRepo.save(questionA1_176);

		Question questionA1_184 = new Question(23, "Thứ tự các xe đi như thế nào là đúng quy tắc giao thông?", true, "a1_184.jpg", a1.getId(), set8.getId(), sh.getId());
		questionRepo.save(questionA1_184);

		Question questionA1_192 = new Question(24, "Xe tải kéo mô tô ba bánh như hình này có đúng quy tắc giao thông không?", true, "a1_192.jpg", a1.getId(), set8.getId(), sh.getId());
		questionRepo.save(questionA1_192);

		Question questionA1_200 = new Question(25, "Trong tình huống dưới đây, xe đầu kéo kéo rơ móoc (xe container) đang rẽ phải, xe con màu xanh và xe máy phía sau xe container đi như thế nào để đảm bảo an toàn?", true, "a1_200.jpg", a1.getId(), set8.getId(), sh.getId());
		questionRepo.save(questionA1_200);
	}
	
	private void LoadRole() {
		if(roleRepo.count() == 0){
			Role admin = new Role("Admin", "Administrator");
			roleRepo.save(admin);
			Role user = new Role("User", "User");
			roleRepo.save(user);
		}
		
	}
	private void LoadAccount() {
		if(accountRepo.count() == 0) {
			Role admin = roleRepo.findByRoleName("Admin");
			Role user = roleRepo.findByRoleName("User");
			Account account1 = new Account("admin", "admin123", "admin@gmail.com", "Nhật", "Võ", "", AccountStatus.ACTIVE, admin.getId());
			accountRepo.save(account1);
			Account account2 = new Account("user", "user123", "user@gmail.com", "Nhật", "Võ", "", AccountStatus.ACTIVE, user.getId());
			accountRepo.save(account2);
		}
		
	}
	private void LoadLicenseType() {
		if(licenseTypeRepo.count() == 0) {
			LicenseType xe2Banh = new LicenseType("Xe mô tô", Status.ACTIVE, "Bằng lái xe mô tô");
			licenseTypeRepo.save(xe2Banh);
			LicenseType xe4Banh = new LicenseType("Xe ô tô", Status.ACTIVE, "Bằng lái xe ô tô");
			licenseTypeRepo.save(xe4Banh);
		}
		
	}
	private void LoadLicense() {
		if(licenseRepo.count() == 0) {
			LicenseType xe2Banh = licenseTypeRepo.findByName("Xe mô tô");
			License a1 = new License("A1", Status.ACTIVE, "Bằng lái xe A1", xe2Banh.getId());
			licenseRepo.save(a1);
			License a2 = new License("A2", Status.ACTIVE, "Bằng lái xe A2", xe2Banh.getId());
			licenseRepo.save(a2);
			LicenseType xe4Banh = licenseTypeRepo.findByName("Xe ô tô");
			License b1 = new License("B1", Status.ACTIVE, "Bằng lái xe B1", xe4Banh.getId());
			licenseRepo.save(b1);
			License b2 = new License("B2", Status.ACTIVE, "Bằng lái xe B2", xe4Banh.getId());
			licenseRepo.save(b2);
		}
		
	}
	private void LoadTrafficSignType() {
		if(trafficSignTypeRepo.count() == 0) {
			TrafficSignType prohibitionSign = new TrafficSignType("C", "Biển báo cấm");
			trafficSignTypeRepo.save(prohibitionSign);
			TrafficSignType dangerSign = new TrafficSignType("NH", "Biển báo nguy hiểm");
			trafficSignTypeRepo.save(dangerSign);
			TrafficSignType commandSign = new TrafficSignType("HL", "Biển báo hiệu lệnh");
			trafficSignTypeRepo.save(commandSign);
			TrafficSignType directionSign = new TrafficSignType("CD", "Biển báo chỉ dẫn");
			trafficSignTypeRepo.save(directionSign);
			TrafficSignType subSign = new TrafficSignType("P", "Biển báo phụ");
			trafficSignTypeRepo.save(subSign);
			TrafficSignType roadMarking = new TrafficSignType("V" ,"Vạch kẻ đường");
			trafficSignTypeRepo.save(roadMarking);
		}
	}
	private void LoadTrafficSign() {
		if(trafficSignRepo.count() == 0) {
			String probihitionSignId = trafficSignTypeRepo.findByCode("C").getId();
			String dangerSignId = trafficSignTypeRepo.findByCode("NH").getId();
			String commandSignId = trafficSignTypeRepo.findByCode("HL").getId();
			String directionSignId = trafficSignTypeRepo.findByCode("CD").getId();
			String subSignId = trafficSignTypeRepo.findByCode("P").getId();
			String roadMarkingId = trafficSignTypeRepo.findByCode("V").getId();
			// Biển báo cấm
			TrafficSign p101 = new TrafficSign("P.101", "Đường cấm", "Cấm các loại phương tiện di chuyển cả 2 hướng (ngoại trừ xe ưu tiên theo quy định).", "p101.png", probihitionSignId);
			trafficSignRepo.save(p101);
			TrafficSign p102 = new TrafficSign("P.102", "Cấm đi ngược chiều", "Cấm các loại phương tiện đi vào chiều đặt biển (ngoại trừ xe ưu tiên theo quy định).", "p102.png", probihitionSignId);
			trafficSignRepo.save(p102);
			TrafficSign p103a = new TrafficSign("P.103a", "Cấm ô tô", "Cấm các loại xe cơ giới đi vào, trừ xe máy 2 bánh, xe gắn máy và xe ưu tiên theo quy định.", "p103a.png", probihitionSignId);
			trafficSignRepo.save(p103a);
			TrafficSign p103b = new TrafficSign("P.103b", "Cấm ô tô rẽ phải", "Cấm các loại xe cơ giới rẽ phải, trừ xe máy 2 bánh, xe gắn máy và xe ưu tiên theo quy định.", "p103b.png", probihitionSignId);
			trafficSignRepo.save(p103b);
			TrafficSign p103c = new TrafficSign("P.103c", "Cấm ô tô rẽ trái", "Cấm các loại xe cơ giới rẽ trái, trừ xe máy 2 bánh, xe gắn máy và xe ưu tiên theo quy định.", "p103c.png", probihitionSignId);
			trafficSignRepo.save(p103c);
			TrafficSign p104 = new TrafficSign("P.104", "Cấm mô tô", "Cấm các loại xe máy đi vào (ngoại trừ xe máy được ưu tiên theo quy định). Biển không áp dụng với người dắt xe máy.", "p104.png", probihitionSignId);
			trafficSignRepo.save(p104);
			TrafficSign p105 = new TrafficSign("P.105", "Cấm ô tô và mô tô", "Cấm các loại xe cơ giới và xe máy đi vào, ngoại trừ xe gắn máy và xe ưu tiên theo quy định.", "p105.png", probihitionSignId);
			trafficSignRepo.save(p105);
			TrafficSign p106a = new TrafficSign("P.106a", "Cấm ô tô tải", "Cấm xe tải, xe máy kéo, các xe máy chuyên dùng đi vào (trừ xe ưu tiên theo quy định).", "p106a.png", probihitionSignId);
			trafficSignRepo.save(p106a);
			TrafficSign p106b = new TrafficSign("P.106b", "Cấm ô tô tải theo trọng lượng", "Cấm xe tải có khối lượng chuyên chở lớn hơn giá trị ghi trong biển báo đi vào.", "p106b.png", probihitionSignId);
			trafficSignRepo.save(p106b);
			TrafficSign p106c = new TrafficSign("P.106c", "Cấm ô tô tải chở hàng nguy hiểm", "Cấm xe tải chở hàng nguy hiểm đi vào.", "p106c.png", probihitionSignId);
			trafficSignRepo.save(p106c);
			TrafficSign p107 = new TrafficSign("P.107", "Cấm ô tô khách và ô tô tải", "Cấm các loại xe ô tô chở khách, xe tải, xe máy kéo, xe máy thi công chuyên dụng đi vào (trừ xe ưu tiên theo quy định).", "p107.png", probihitionSignId);
			trafficSignRepo.save(p107);
			TrafficSign p107a = new TrafficSign("P.107a", "Cấm ô tô khách", "Cấm các loại ô tô chở khách đi vào (trừ xe ưu tiên theo quy định). Không cấm xe buýt. Trong trường hợp cấm xe khách theo số chỗ ngồi sẽ có biển phụ bên dưới.", "p107a.png", probihitionSignId);
			trafficSignRepo.save(p107a);
			TrafficSign p107b = new TrafficSign("P.107b", "Cấm xe taxi", "Cấm xe taxi đi vào. Trong trường hợp cấm xe taxi theo giờ sẽ có biển phụ bên dưới.", "p107b.png", probihitionSignId);
			trafficSignRepo.save(p107b);
			TrafficSign p108 = new TrafficSign("P.108", "Cấm ô tô kéo rơ-mooc", "Cấm các loại xe cơ giới kéo rơ-mooc, kể cả xe khách – máy kéo – xe máy kéo theo rơ-mooc đi vào, trừ loại ô tô sơ-mi-rơ-mooc và xe ưu tiên theo quy định.", "p108.png", probihitionSignId);
			trafficSignRepo.save(p108);
			TrafficSign p108a = new TrafficSign("P.108a", "Cấm xe sơ-mi-rơ-mooc", "Cấm các loại xe sơ-mi-rơ-mooc, xe kéo rơ-mooc đi vào (trừ xe ưu tiên theo quy định).", "p108a.png", probihitionSignId);
			trafficSignRepo.save(p108a);
			TrafficSign p109 = new TrafficSign("P.109", "Cấm máy kéo", "Cấm các loại máy kéo, máy kéo bánh xích/bánh hơi đi vào.", "p109.png", probihitionSignId);
			trafficSignRepo.save(p109);
			TrafficSign p110a = new TrafficSign("P.110a", "Cấm xe đạp", "Cấm xe đạp đi vào. Không áp dụng cấm người dẫn xe đạp.", "p110a.png", probihitionSignId);
			trafficSignRepo.save(p110a);
			TrafficSign p110b = new TrafficSign("P.110b", "Cấm xe đạp thồ", "Cấm xe đạp thô. Không áp dụng cấm người dẫn xe đạp thô.", "p110b.png", probihitionSignId);
			trafficSignRepo.save(p110b);
			TrafficSign p111a = new TrafficSign("P.111a", "Cấm xe máy", "Cấm các loại xe máy, xe gắn máy đi vào. Không áp dụng cấm người đi xe đạp.", "p111a.png", probihitionSignId);
			trafficSignRepo.save(p111a);
			TrafficSign p111b = new TrafficSign("P.111b", "Cấm xe 3 bánh có động cơ", "Cấm xe 3 bánh có gắn động cơ như xe xích lô, xe lam, xe lôi máy…", "p111b.png", probihitionSignId);
			trafficSignRepo.save(p111b);
			TrafficSign p111c = new TrafficSign("P.111c", "Cấm xe 3 bánh không động cơ", "Cấm xe 3 bánh không gắn động cơ như xe xích lô, xe lôi đạp…", "p111c.png", probihitionSignId);
			trafficSignRepo.save(p111c);
			TrafficSign p112 = new TrafficSign("P.112", "Cấm người đi bộ", "Cấm người đi bộ đi vào.", "p112.png", probihitionSignId);
			trafficSignRepo.save(p112);
			TrafficSign p113 = new TrafficSign("P.113", "Cấm xe người kéo, đẩy", "Cấm xe thô sơ, xe do người đẩy/kéo đi vào. Không áp dụng cấm xe nôi trẻ em, phương tiện chuyên dùng của người khuyết tật.", "p113.png", probihitionSignId);
			trafficSignRepo.save(p113);
			TrafficSign p114 = new TrafficSign("P.114", "Cấm xe súc vật kéo", "Cấm xe sử dụng súc vật kéo hay chở trên lưng đi vào.", "p114.png", probihitionSignId);
			trafficSignRepo.save(p114);
			TrafficSign p115 = new TrafficSign("P.115", "Hạn chế tải trọng toàn bộ xe", "Cấm các loại xe cơ giới/thô sơ (kể cả xe ưu tiên) có tải trọng toàn bộ xe vượt quá trị số ghi trên biển đi vào.", "p115.png", probihitionSignId);
			trafficSignRepo.save(p115);
			TrafficSign p116 = new TrafficSign("P.116", "Hạn chế tải trọng trục xe", "Cấm các loại xe cơ giới/thô sơ (kể cả xe ưu tiên) có tải trọng toàn bộ xe phân bổ trên một trục xe vượt quá trị số ghi trên biển đi vào.", "p116.png", probihitionSignId);
			trafficSignRepo.save(p116);
			TrafficSign p117 = new TrafficSign("P.117", "Hạn chế chiều cao xe", "Cấm các loại xe cơ giới/thô sơ (kể cả xe ưu tiên) có chiều cao vượt quá trị số ghi trên biển đi vào.", "p117.png", probihitionSignId);
			trafficSignRepo.save(p117);
			TrafficSign p118 = new TrafficSign("P.118", "Hạn chế chiều ngang xe", "Cấm các loại xe cơ giới/thô sơ (kể cả xe ưu tiên) có chiều ngang vượt quá trị số ghi trên biển đi vào.", "p118.png", probihitionSignId);
			trafficSignRepo.save(p118);
			TrafficSign p119 = new TrafficSign("P.119", "Hạn chế chiều dài xe", "Cấm các loại xe cơ giới/thô sơ (kể cả xe ưu tiên) có chiều dài vượt quá trị số ghi trên biển đi vào.", "p119.png", probihitionSignId);
			trafficSignRepo.save(p119);
			TrafficSign p120 = new TrafficSign("P.120", "Hạn chế chiều dài xe kéo moóc", "Cấm các loại xe cơ giới kéo mooc, xe sơ-mi-rơ-mooc có chiều dài vượt quá trị số ghi trên biển (kể cả xe ưu tiên) đi vào.", "p120.png", probihitionSignId);
			trafficSignRepo.save(p120);
			TrafficSign p121 = new TrafficSign("P.121", "Cự ly tối thiểu giữa hai xe", "Các xe ô tô phải di chuyển cách nhau một khoảng tối thiểu ghi trên biển.", "p121.png", probihitionSignId);
			trafficSignRepo.save(p121);
			TrafficSign p123a = new TrafficSign("P.123a", "Cấm rẽ trái", "Cấm xe các loại xe cơ giới/thô sơ rẽ trái (trừ xe ưu tiên theo quy định). Không áp dụng cấm quay đầu xe.", "p123a.png", probihitionSignId);
			trafficSignRepo.save(p123a);
			TrafficSign p123b = new TrafficSign("P.123b", "Cấm rẽ phải", "Cấm xe các loại xe cơ giới/thô sơ rẽ phải (trừ xe ưu tiên theo quy định). Không áp dụng cấm quay đầu xe.", "p123b.png", probihitionSignId);
			trafficSignRepo.save(p123b);
			TrafficSign p124a = new TrafficSign("P.124a", "Cấm quay đầu xe", "Cấm các loại xe quay đầu kiểu chữ U theo chiều mũi tên trên biển.", "p124a.png", probihitionSignId);
			trafficSignRepo.save(p124a);
			TrafficSign p124b = new TrafficSign("P.124b", "Cấm xe ô tô quay đầu", "Cấm xe ô tô quay đầu kiểu chữ U theo chiều mũi tên trên biển.", "p124b.png", probihitionSignId);
			trafficSignRepo.save(p124b);
			TrafficSign p124c = new TrafficSign("P.124c", "Cấm rẽ trái và quay đầu xe", "Cấm các loại xe rẽ trái và quay đầu trái theo chiều mũi tên trên biển.", "p124c.png", probihitionSignId);
			trafficSignRepo.save(p124c);
			TrafficSign p124d = new TrafficSign("P.124d", "Cấm rẽ phải và quay đầu xe", "Cấm các loại xe rẽ phải và quay đầu phải theo chiều mũi tên trên biển.", "p124d.png", probihitionSignId);
			trafficSignRepo.save(p124d);
			TrafficSign p124e = new TrafficSign("P.124e", "Cấm xe ô tô rẽ trái và quay đầu xe", "Cấm xe ô tô rẽ trái và quay đầu trái theo chiều mũi tên trên biển.", "p124e.png", probihitionSignId);
			trafficSignRepo.save(p124e);
			TrafficSign p124f = new TrafficSign("P.124f", "Cấm xe ô tô rẽ phải và quay đầu xe", "Cấm xe ô tô rẽ phải và quay đầu phải theo chiều mũi tên trên biển.", "p124f.png", probihitionSignId);
			trafficSignRepo.save(p124f);
			TrafficSign p125 = new TrafficSign("P.125", "Cấm vượt", "Cấm các loại xe cơ giới vượt nhau (kể cả xe ưu tiên theo quy định), nhưng được phép vượt xe máy 2 bánh, xe gắn máy.", "p125.png", probihitionSignId);
			trafficSignRepo.save(p125);
			TrafficSign p126 = new TrafficSign("P.126", "Cấm ô tô tải vượt", "Cấm các loại xe tải vượt xe cơ giới khác, được phép vượt xe máy 2 bánh, xe gắn máy. Không áp dụng các loại xe cơ giới khác vượt xe nhau và vượt xe tải.", "p126.png", probihitionSignId);
			trafficSignRepo.save(p126);
			TrafficSign p127 = new TrafficSign("P.127", "Tốc độ tối đa cho phép", "Các xe cơ giới chạy không vượt quá tốc độ ghi trên biển (trừ xe ưu tiên theo quy định).", "p127.png", probihitionSignId);
			trafficSignRepo.save(p127);
			TrafficSign p127a = new TrafficSign("P.127a", "Tốc độ tối đa cho phép vào ban đêm", "Các xe cơ giới chạy không vượt quá tốc độ ghi trên biển (trừ xe ưu tiên theo quy định) chỉ áp dụng vào ban đêm.", "p127a.png", probihitionSignId);
			trafficSignRepo.save(p127a);
			TrafficSign p127b = new TrafficSign("P.127b", "Tốc độ tối đa trên từng làn đường", "Biển ghép tốc độ tối đa cho phép trên từng làn đường.", "p127b.png", probihitionSignId);
			trafficSignRepo.save(p127b);
			TrafficSign p127c = new TrafficSign("P.127c", "Tốc độ tối đa phương tiện theo từng làn đường", "Biển ghép tốc độ tối đa cho phép theo phương tiện, trên từng làn đường.", "p127c.png", probihitionSignId);
			trafficSignRepo.save(p127c);
			TrafficSign p127d = new TrafficSign("P.127d", "Biển hết hạn chế tốc độ tối đa", "Biển hết hạn chế tốc độ tối đa cho phép theo biển ghép.", "p127d.png", probihitionSignId);
			trafficSignRepo.save(p127d);
			TrafficSign p128 = new TrafficSign("P.128", "Cấm sử dụng còi", "Cấm các loại xe sử dụng còi.", "p128.png", probihitionSignId);
			trafficSignRepo.save(p128);
			TrafficSign p129 = new TrafficSign("P.129", "Kiểm tra", "Báo nơi đặt trạm kiểm tra, các loại xe vận tải đi qua phải dừng lại để kiểm tra theo quy định.", "p129.png", probihitionSignId);
			trafficSignRepo.save(p129);
			TrafficSign p130 = new TrafficSign("P.130", "Cấm dừng xe và đỗ xe", "Cấm các loại xe cơ giới dừng và đỗ xe phía đường có đặt biển (trừ xe ưu tiên theo quy định).", "p130.png", probihitionSignId);
			trafficSignRepo.save(p130);
			TrafficSign p131a = new TrafficSign("P.131a", "Cấm đỗ xe", "Cấm các loại xe cơ giới đỗ xe phía đường có đặt biển (trừ xe ưu tiên).", "p131a.png", probihitionSignId);
			trafficSignRepo.save(p131a);
			TrafficSign p131b = new TrafficSign("P.131b", "Cấm đỗ xe ngày lẻ", "Cấm các loại xe cơ giới đỗ xe phía đường có đặt biển (trừ xe ưu tiên) vào những ngày lẻ.", "p131b.png", probihitionSignId);
			trafficSignRepo.save(p131b);
			TrafficSign p131c = new TrafficSign("P.131c", "Cấm đỗ xe ngày chẵn", "Cấm các loại xe cơ giới đỗ xe phía đường có đặt biển (trừ xe ưu tiên) vào những ngày chẵn.", "p131c.png", probihitionSignId);
			trafficSignRepo.save(p131c);
			TrafficSign p132 = new TrafficSign("P.132", "Nhường đường cho xe cơ giới đi ngược chiều qua đường hẹp", "Các loại xe cơ giới/thô sơ (kể cả xe ưu tiên theo quy định) phải nhường đường cho các loại xe cơ giới đang di chuyển chiều ngược lại.", "p132.png", probihitionSignId);
			trafficSignRepo.save(p132);
			TrafficSign p133 = new TrafficSign("P.133", "Hết cấm vượt", "Biển thông báo hết đoạn đường cấm vượt.", "p133.png", probihitionSignId);
			trafficSignRepo.save(p133);
			TrafficSign p134 = new TrafficSign("P.134", "Hết hạn chế tốc độ tối đa", "Biển thông báo hết đoạn đường hạn chế tốc độ tối đa.", "p134.png", probihitionSignId);
			trafficSignRepo.save(p134);
			TrafficSign p135 = new TrafficSign("P.135", "Hết tất cả các lệnh cấm", "Biển thông báo hết đoạn đường áp dụng tất cả các lệnh cấm.", "p135.png", probihitionSignId);
			trafficSignRepo.save(p135);
			TrafficSign p136 = new TrafficSign("P.136", "Cấm đi thẳng", "Cấm các loại xe cơ giới/thô sơ đi thẳng vào đoạn đường phía trước.", "p136.png", probihitionSignId);
			trafficSignRepo.save(p136);
			TrafficSign p137 = new TrafficSign("P.137", "Cấm rẽ trái và rẽ phải", "Cấm các loại xe cơ giới rẽ trái, rẽ phải. Biển đặt ở những vị trí ngay trước nút giao đường. Trong trường hợp có quy định thời gian cấm sẽ có biển phụ ở dưới.", "p137.png", probihitionSignId);
			trafficSignRepo.save(p137);
			TrafficSign p138 = new TrafficSign("P.138", "Cấm đi thẳng và rẽ trái", "Cấm các loại xe cơ giới đi thẳng, rẽ trái. Biển đặt ở những vị trí ngay trước nút giao đường. Trong trường hợp có quy định thời gian cấm sẽ có biển phụ ở dưới.", "p138.png", probihitionSignId);
			trafficSignRepo.save(p138);
			TrafficSign p139 = new TrafficSign("P.139", "Cấm đi thẳng và rẽ phải", "Cấm các loại xe cơ giới đi thẳng, rẽ phải. Biển đặt ở những vị trí ngay trước nút giao đường. Trong trường hợp có quy định thời gian cấm sẽ có biển phụ ở dưới.", "p139.png", probihitionSignId);
			trafficSignRepo.save(p139);
			TrafficSign p140 = new TrafficSign("P.140", "Cấm xe công nông và các loại xe tương tự", "Cấm các loại xe công nông, xe tương tự công nông đi vào.", "p140.png", probihitionSignId);
			trafficSignRepo.save(p140);
			TrafficSign s508a = new TrafficSign("S.508a", "Biển cấm theo giờ", "Khi cần báo hiệu cấm các loại phương tiện giao thông đường bộ theo giờ trong thành phố, thị xã phải đặt biển phụ số S508(a,b) dưới biển cấm và có chú thích bằng tiếng Việt, phụ đề tiếng Anh trong biển này.", "s508a.png", probihitionSignId);
			trafficSignRepo.save(s508a);
			TrafficSign s508b = new TrafficSign("S.508b", "Biển cấm theo giờ", "Khi cần báo hiệu cấm các loại phương tiện giao thông đường bộ theo giờ trong thành phố, thị xã phải đặt biển phụ số S508(a,b) dưới biển cấm và có chú thích bằng tiếng Việt, phụ đề tiếng Anh trong biển này.", "s508b.png", probihitionSignId);
			trafficSignRepo.save(s508b);
			
			// Biển báo nguy hiểm
			TrafficSign w201a = new TrafficSign("W.201a", "Chỗ ngoặc nguy hiểm bên trái", "Báo trước sắp đến một chỗ ngoặt nguy hiểm phía bên trái.", "w201a.png", dangerSignId);
			trafficSignRepo.save(w201a);
			TrafficSign w201b = new TrafficSign("W.201b", "Chỗ ngoặc nguy hiểm bên phải", "Báo trước sắp đến một chỗ ngoặt nguy hiểm phía bên phải.", "w201b.png", dangerSignId);
			trafficSignRepo.save(w201b);
			TrafficSign w201c = new TrafficSign("W.201c", "Chỗ ngoặt nguy hiểm có nguy cơ lật xe bên phải", "Báo trước chỗ ngoặc nguy hiểm có nguy cơ lật xe bên phải.", "w201c.png", dangerSignId);
			trafficSignRepo.save(w201c);
			TrafficSign w201d = new TrafficSign("W.201d", "Chỗ ngoặt nguy hiểm có nguy cơ lật xe bên trái", "Báo trước chỗ ngoặc nguy hiểm có nguy cơ lật xe bên trái.", "w201d.png", dangerSignId);
			trafficSignRepo.save(w201d);
			TrafficSign w202a = new TrafficSign("W.202a", "Nhiều chỗ ngoặt nguy hiểm liên tiếp", "Báo trước sắp đến nhiều chỗ ngoặt nguy hiểm liên tiếp trong đó chỗ ngoặt đầu tiên hướng vòng bên trái.", "w202a.png", dangerSignId);
			trafficSignRepo.save(w202a);
			TrafficSign w202b = new TrafficSign("W.202b", "Nhiều chỗ ngoặt nguy hiểm liên tiếp", "Báo trước sắp đến nhiều chỗ ngoặt nguy hiểm liên tiếp trong đó chỗ ngoặt đầu tiên hướng vòng bên phải.", "w202b.png", dangerSignId);
			trafficSignRepo.save(w202b);
			TrafficSign w203a = new TrafficSign("W.203a", "Đường bị hẹp cả hai bên", "Báo trước sắp đến một đoạn đường bị hẹp đột ngột cả hai bên.", "w203a.png", dangerSignId);
			trafficSignRepo.save(w203a);
			TrafficSign w203b = new TrafficSign("W.203b", "Đường bị hẹp về phía trái", "Báo trước sắp đến một đoạn đường bị hẹp đột ngột cả hai bên.", "w203b.png", dangerSignId);
			trafficSignRepo.save(w203b);
			TrafficSign w203c = new TrafficSign("W.203c", "Đường bị hẹp về phía phải", "Báo trước sắp đến một đoạn đường bị hẹp đột ngột phía bên phải.", "w203c.png", dangerSignId);
			trafficSignRepo.save(w203c);
			TrafficSign w204 = new TrafficSign("W.204", "Đường hai chiều", "Báo trước sắp đến một đoạn đường do sửa chữa hoặc có trở ngại ở một phía đường mà phải tổ chức đi lại cho phương tiện cả hai chiều trên phía đường còn lại hoặc để báo trước đoạn đường đôi tạm thời hoặc đoạn đường có chiều xe đi và về đi chung.", "w204.png", dangerSignId);
			trafficSignRepo.save(w204);
			TrafficSign w205a = new TrafficSign("W.205a", "Đường giao nhau cùng cấp", "Báo trước sắp đến nơi giao nhau cùng mức của các tuyến đường cùng cấp (không có đường nào ưu tiên)  trên cùng một mặt bằng.", "w205a.png", dangerSignId);
			trafficSignRepo.save(w205a);
			TrafficSign w205b = new TrafficSign("W.205b", "Đường giao nhau cùng cấp", "Báo trước sắp đến nơi giao nhau cùng mức của các tuyến đường cùng cấp (không có đường nào ưu tiên)  trên cùng một mặt bằng.", "w205b.png", dangerSignId);
			trafficSignRepo.save(w205b);
			TrafficSign w205c = new TrafficSign("W.205c", "Đường giao nhau cùng cấp", "Báo trước sắp đến nơi giao nhau cùng mức của các tuyến đường cùng cấp (không có đường nào ưu tiên)  trên cùng một mặt bằng.", "w205c.png", dangerSignId);
			trafficSignRepo.save(w205c);
			TrafficSign w205d = new TrafficSign("W.205d", "Đường giao nhau cùng cấp", "Báo trước sắp đến nơi giao nhau cùng mức của các tuyến đường cùng cấp (không có đường nào ưu tiên)  trên cùng một mặt bằng.", "w205d.png", dangerSignId);
			trafficSignRepo.save(w205d);
			TrafficSign w205e = new TrafficSign("W.205e", "Đường giao nhau cùng cấp", "Báo trước sắp đến nơi giao nhau cùng mức của các tuyến đường cùng cấp (không có đường nào ưu tiên)  trên cùng một mặt bằng.", "w205e.png", dangerSignId);
			trafficSignRepo.save(w205e);
			TrafficSign w206 = new TrafficSign("W.206", "Giao nhau chạy theo vòng xuyến", "Báo trước sắp đến nơi giao nhau có bố trí đảo an toàn ở giữa nút giao, các loại xe qua nút giao phải đi vòng xuyến quanh đảo an toàn theo chiều mũi tên.", "w206.png", dangerSignId);
			trafficSignRepo.save(w206);
			TrafficSign w207a = new TrafficSign("W.207a", "Giao nhau với đường không ưu tiên", "Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207a.png", dangerSignId);
			trafficSignRepo.save(w207a);
			TrafficSign w207b = new TrafficSign("W.207b", "Giao nhau với đường không ưu tiên", "Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207b.png", dangerSignId);
			trafficSignRepo.save(w207b);
			TrafficSign w207c = new TrafficSign("W.207c", "Giao nhau với đường không ưu tiên", "Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207c.png", dangerSignId);
			trafficSignRepo.save(w207c);
			TrafficSign w207d = new TrafficSign("W.207d", "Giao nhau với đường không ưu tiên", "Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207d.png", dangerSignId);
			trafficSignRepo.save(w207d);
			TrafficSign w207e = new TrafficSign("W.207e", "Giao nhau với đường không ưu tiên", "Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207e.png", dangerSignId);
			trafficSignRepo.save(w207e);
			TrafficSign w207f = new TrafficSign("W.207f", "Giao nhau với đường không ưu tiên", "Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207f.png", dangerSignId);
			trafficSignRepo.save(w207f);
			TrafficSign w207g = new TrafficSign("W.207g", "Giao nhau với đường không ưu tiên", "Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207g.png", dangerSignId);
			trafficSignRepo.save(w207g);
			TrafficSign w207h = new TrafficSign("W.207h", "Giao nhau với đường không ưu tiên", "Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207h.png", dangerSignId);
			trafficSignRepo.save(w207h);
			TrafficSign w207i = new TrafficSign("W.207i", "Giao nhau với đường không ưu tiên", "Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207i.png", dangerSignId);
			trafficSignRepo.save(w207i);
			TrafficSign w207k = new TrafficSign("W.207k", "Giao nhau với đường không ưu tiên", "Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207k.png", dangerSignId);
			trafficSignRepo.save(w207k);
			TrafficSign w208 = new TrafficSign("W.208", "Giao nhau với đường ưu tiên", "Báo trước sắp đến nơi giao nhau với đường ưu tiên.", "w208.png", dangerSignId);
			trafficSignRepo.save(w208);
			TrafficSign w209 = new TrafficSign("W.209", "Giao nhau có tín hiệu đèn", "Báo trước nơi giao nhau có điều khiển giao thông bằng tín hiệu đèn trong trường hợp người lái xe khó quan sát để kịp thời xử lý.", "w209.png", dangerSignId);
			trafficSignRepo.save(w209);
			TrafficSign w210 = new TrafficSign("W.210", "Giao nhau với đường sắt có rào chắn", "Báo trước sắp đến chỗ giao nhau giữa đường bộ và đường sắt có rào chắn kín hay rào chắn nửa kín và có nhân viên ngành đường sắt điều khiển giao thông.", "w210.png", dangerSignId);
			trafficSignRepo.save(w210);
			TrafficSign w211a = new TrafficSign("W.211a", "Giao nhau với đường sắt không có rào chắn", "Báo trước sắp đến chỗ giao nhau giữa đường bộ và đường sắt không có rào chắn, không có người điều khiển giao thông.", "w211a.png", dangerSignId);
			trafficSignRepo.save(w211a);
			TrafficSign w211b = new TrafficSign("W.211b", "Giao nhau với đường tàu điện", "Chỉ nơi đường bộ giao nhau cùng mức với đường tàu điện.", "w211b.png", dangerSignId);
			trafficSignRepo.save(w211b);
			TrafficSign w212 = new TrafficSign("W.212", "Cầu hẹp", "Báo trước sắp đến cầu hẹp là loại cầu có chiều rộng phần xe chạy nhỏ hơn hoặc bằng 4,5m.", "w212.png", dangerSignId);
			trafficSignRepo.save(w212);
			TrafficSign w213 = new TrafficSign("W.213", "Cầu tạm", "Báo trước sắp đến cầu tạm là loại cầu được làm để sử dụng tạm thời cho xe cộ qua lại.", "w213.png", dangerSignId);
			trafficSignRepo.save(w213);
			TrafficSign w214 = new TrafficSign("W.214", "Cầu quay-cầu cất", "Báo trước sắp đến cầu xoay, cầu cất là loại cầu trong từng thời gian có cắt giao thông đường bộ bằng cách quay hoặc nâng nhịp thông thuyền để cho tàu thuyền qua lại.", "w214.png", dangerSignId);
			trafficSignRepo.save(w214);
			TrafficSign w215 = new TrafficSign("W.215", "Kè, vực sâu phía trước", "Báo trước sắp đến những vị trí có kè chắn vực sâu, hoặc sông suối đi sát đường, cần đề phòng tình huống nguy hiểm rơi xuống vực sâu hoặc sông su ối (thường có ở những chỗ ngoặt nguy hiểm).", "w215.png", dangerSignId);
			trafficSignRepo.save(w215);
			TrafficSign w216 = new TrafficSign("W.216", "Đường ngầm", "Báo trước sắp đến những vị trí có đường ngầm (đường tràn) phải đặt biển số 216 \"Đường ngầm\".", "w216.png", dangerSignId);
			trafficSignRepo.save(w216);
			TrafficSign w217 = new TrafficSign("W.217", "Bến phà", "Báo trước sắp đến bến phà.", "w217.png", dangerSignId);
			trafficSignRepo.save(w217);
			TrafficSign w218 = new TrafficSign("W.218", "Cửa chui", "Báo trước sắp đến đường có cổng chắn ngang, kiểu cổng như đường hầm, cổng thành, cầu vượt đường bộ dạng cầu vòm v.v....", "w218.png", dangerSignId);
			trafficSignRepo.save(w218);
			TrafficSign w219 = new TrafficSign("W.219", "Dốc xuống nguy hiểm", "Báo trước sắp đến đoạn đường xuống dốc nguy hiểm.", "w219.png", dangerSignId);
			trafficSignRepo.save(w219);
			TrafficSign w220 = new TrafficSign("W.220", "Dốc lên nguy hiểm", "Báo trước sắp đến đoạn đường lên dốc nguy hiểm.", "w220.png", dangerSignId);
			trafficSignRepo.save(w220);
			TrafficSign w221a = new TrafficSign("W.221a", "Đường có ổ gà, lồi lõm", "Đặt trong trường hợp đường đang tốt, xe chạy nhanh, chuyển sang những đoạn lồi lõm, gập ghềnh, ổ gà, lượn sóng.", "w221a.png", dangerSignId);
			trafficSignRepo.save(w221a);
			TrafficSign w221b = new TrafficSign("W.221b", "Đường có sóng mấp mô nhân tạo", "Để hạn chế tốc độ xe chạy (biển được cắm kèm theo biển số 227 \"Hạn chế tốc độ tối đa\"), bắt buộc lái xe phải chạy với tốc độ chậm trước khi qua những điểm cần kiểm soát, kiểm tra hoặc giảm tốc độ trước khi vào đoạn đường hạn chế tốc độ tối đa.", "w221b.png", dangerSignId);
			trafficSignRepo.save(w221b);
			TrafficSign w222a = new TrafficSign("W.222a", "Đường trơn", "Báo trước sắp tới đoạn đường có thể xảy ra trơn trượt đặc biệt là khi thời tiết xấu, mưa phùn (hệ số bám của lốp với mặt đường < 0,3) cần tránh hãm phanh, tăng ga, sang số đột ngột hoặc xe chạy với tốc độ cao sẽ bị nguy hiểm.", "w222a.png", dangerSignId);
			trafficSignRepo.save(w222a);
			TrafficSign w222b = new TrafficSign("W.222b", "Lề đường nguy hiểm", "Báo những nơi lề đường không ổn định, khi xe đi vào dễ gây văng đất đá hoặc bánh xe quay tại chỗ.", "w222b.png", dangerSignId);
			trafficSignRepo.save(w222b);
			TrafficSign w223a = new TrafficSign("W.223a", "Vách núi nguy hiểm", "Báo hiệu sắp đi vào đoạn đường đi sát vách núi nằm ở bên tay trái, đường vừa hẹp vừa hạn chế tầm nhìn, người lái xe phải cẩn thận.", "w223a.png", dangerSignId);
			trafficSignRepo.save(w223a);
			TrafficSign w223b = new TrafficSign("W.223b", "Vách núi nguy hiểm", "Báo hiệu sắp đi vào đoạn đường đi sát vách núi nằm ở bên tay phải, đường vừa hẹp vừa hạn chế tầm nhìn, người lái xe phải cẩn thận.", "w223b.png", dangerSignId);
			trafficSignRepo.save(w223b);
			TrafficSign w224 = new TrafficSign("W.224", "Đường người đi bộ cắt ngang", "Báo trước sắp tới phần đường dành cho người đi bộ sang qua đường. Gặp biển này các xe phải giảm tốc độ, nhường ưu tiên cho người đi bộ và chỉ được chạy xe khi không gây nguy hiểm cho người đi bộ.", "w224.png", dangerSignId);
			trafficSignRepo.save(w224);
			TrafficSign w225 = new TrafficSign("W.225", "Trẻ em", "Báo trước là gần đến đoạn đường thường có trẻ em đi ngang qua hoặc tụ tập trên đường như ở vườn trẻ, trường học, câu lạc bộ.", "w225.png", dangerSignId);
			trafficSignRepo.save(w225);
			TrafficSign w226 = new TrafficSign("W.226", "Đường người đi xe đạp cắt ngang", "Báo trước là gần tới vị trí thường có người đi xe đạp từ những đường nhỏ cắt ngang qua hoặc từ đường dành cho xe đạp đi nhập vào đường ôtô.", "w226.png", dangerSignId);
			trafficSignRepo.save(w226);
			TrafficSign w227 = new TrafficSign("W.227", "Công trường", "Báo trước gần tới đoạn đường đang tiến hành thi công sửa chữa, cải tạo, nâng cấp có người và máy móc đang làm việc trên mặt đường. Khi gặp biển báo này tốc độ xe chạy phải giảm cho thích hợp, không gây nguy hiểm cho người và máy móc trên đoạn đường đó.", "w227.png", dangerSignId);
			trafficSignRepo.save(w227);
			TrafficSign w228a = new TrafficSign("W.228a", "Đá lở", "Báo trước gần tới đoạn đường có hiện tượng đất đá từ trên ta luy dương sụt lở bất ngờ gây nguy hiểm cho xe cộ và người đi đường, đặc biệt là ở những đoạn đường miền núi. Gặp biển này, người lái xe phải chú ý; đặc biệt khi thời tiết xấu, hạn chế tầm nhìn, không dừng hay đỗ xe trong khu vực đá lở sau những trận mưa lớn.", "w228a.png", dangerSignId);
			trafficSignRepo.save(w228a);
			TrafficSign w228b = new TrafficSign("W.228b", "Đá lở", "Báo trước gần tới đoạn đường có hiện tượng đất đá từ trên ta luy dương sụt lở bất ngờ gây nguy hiểm cho xe cộ và người đi đường, đặc biệt là ở những đoạn đường miền núi. Gặp biển này, người lái xe phải chú ý; đặc biệt khi thời tiết xấu, hạn chế tầm nhìn, không dừng hay đỗ xe trong khu vực đá lở sau những trận mưa lớn.", "w228b.png", dangerSignId);
			trafficSignRepo.save(w228b);
			TrafficSign w228c = new TrafficSign("W.228c", "Sỏi đá bắn lên", "Báo trước nơi có kết cấu mặt đường rời rạc, khi phương tiện đi qua, làm cho các viên đá, sỏi băng lên gây nguy hiểm và mất an toàn cho người và phương tiện tham gia giao thông.", "w228c.png", dangerSignId);
			trafficSignRepo.save(w228c);
			TrafficSign w229 = new TrafficSign("W.229", "Giải máy bay lên xuống", "Báo trước đoạn đường ở vùng sát đường băng sân bay và cắt ngang qua hướng máy bay lên xuống ở độ cao không lớn.", "w229.png", dangerSignId);
			trafficSignRepo.save(w229);
			TrafficSign w230 = new TrafficSign("W.230", "Gia súc", "Báo trước gần tới đoạn đường thường có gia súc thả rông hoặc lùa qua ngang đường, đường ở vùng đồng cỏ của nông trường chăn nuôi, vùng thảo nguyên... Người lái xe có trách nhiệm đi chậm, quan sát và dừng lại bảo đảm cho gia súc có thể qua đường không bị nguy hiểm.", "w230.png", dangerSignId);
			trafficSignRepo.save(w230);
			TrafficSign w231 = new TrafficSign("W.231", "Thú rừng vượt qua đường", "Báo trước gần tới đoạn đường thường có thú rừng qua đường như đường đi qua rừng hay khu vực bảo tồn thiên nhiên cấm săn bắn. Người lái xe phải đi chậm, chú ý quan sát hai bên đường và thận trọng đề phòng tai nạn.", "w231.png", dangerSignId);
			trafficSignRepo.save(w231);
			TrafficSign w232 = new TrafficSign("W.232", "Gió ngang", "Báo trước gần tới đoạn đường thường có gió ngang thổi mạnh gây nguy hiểm. Người lái xe cần phải điều chỉnh tốc độ xe chạy cho thích hợp, đề phòng gió thổi mạnh gây lật xe.", "w232.png", dangerSignId);
			trafficSignRepo.save(w232);
			TrafficSign w233 = new TrafficSign("W.233", "Nguy hiểm khác", "Báo trên đường có những nguy hiểm mà không thể vận dụng được các kiểu biển từ biển số 201a đến biển số 232.", "w233.png", dangerSignId);
			trafficSignRepo.save(w233);
			TrafficSign w234 = new TrafficSign("W.234", "Giao nhau với đường hai chiều", "Đặt trên đường một chiều, để báo trước sắp đến vị trí giao nhau với đường hai chiều.", "w234.png", dangerSignId);
			trafficSignRepo.save(w234);
			TrafficSign w235 = new TrafficSign("W.235", "Đường đôi", "Báo trước sắp đến đoạn đường có chiều đi và chiều về phân biệt bằng giải phân cách cứng.", "w235.png", dangerSignId);
			trafficSignRepo.save(w235);
			TrafficSign w236 = new TrafficSign("W.236", "Hết đường đôi", "Báo trước sắp kết thúc đoạn đường có chiều đi và chiều về phân biệt bằng giải phân cách cứng.", "w236.png", dangerSignId);
			trafficSignRepo.save(w236);
			TrafficSign w237 = new TrafficSign("W.237", "Cầu vồng", "Đặt ở trên đoạn đường sắp đến công trình có độ vồng lớn ảnh hưởng tới tầm nhìn, nhắc nhở lái xe lái cẩn thận.", "w237.png", dangerSignId);
			trafficSignRepo.save(w237);
			TrafficSign w238 = new TrafficSign("W.238", "Đường cao tốc phía trước", "Được đặt trên đường nhánh nhập vào đường cao tốc để báo cho các phương tiện đi trên đường này biết có Đường cao tốc ở phía trước.", "w238.png", dangerSignId);
			trafficSignRepo.save(w238);
			TrafficSign w239 = new TrafficSign("W.239", "Đường cáp điện ở phía trên", "Được đặt Ở những nơi có đường dây điện cắt ngang phía trên tuyến đường.", "w239.png", dangerSignId);
			trafficSignRepo.save(w239);
			TrafficSign w240 = new TrafficSign("W.240", "Đường hầm", "Nhắc lái xe chú ý chuẩn bị đi vào hầm đường bộ. Biển đặt ở bên phải chiều đi trước khi vào hầm.", "w240.png", dangerSignId);
			trafficSignRepo.save(w240);
			TrafficSign w241 = new TrafficSign("W.241", "Ùn tắc giao thông", "Báo đoạn đường hay xảy ra ùn tắc giao thông.", "w241.png", dangerSignId);
			trafficSignRepo.save(w241);
			TrafficSign w242a = new TrafficSign("W.242a", "Nơi đường sắt giao vuông góc với đường bộ", "Bổ sung cho biển số 211 \"Giao nhau với đường sắt không có rào chắn\", để chỉ chỗ đường sắt giao vuông góc đường bộ, và tại chỗ giao nhau đường sắt chỉ có một đường cắt ngang đường bộ.", "w242a.png", dangerSignId);
			trafficSignRepo.save(w242a);
			TrafficSign w242b = new TrafficSign("W.242b", "Nơi đường sắt giao vuông góc với đường bộ", "Bổ sung cho biển số 211 \"Giao nhau với đường sắt không có rào chắn\", để chỉ chỗ đường sắt giao vuông góc đường bộ, và tại chỗ giao nhau đường sắt có từ hai đường cắt ngang đường bộ.", "w242b.png", dangerSignId);
			trafficSignRepo.save(w242b);
			TrafficSign w243a = new TrafficSign("W.243a", "Nơi đường sắt giao không vuông góc với đường bộ", "Đặt ở nơi cách ray ngoài cùng nơi giao đường sắt 50m, báo trước sắp đến vị trí giao cắt đường bộ với đường sắt cùng mức, không vuông góc và không có người gác, không có rào chắn.", "w243a.png", dangerSignId);
			trafficSignRepo.save(w243a);
			TrafficSign w243b = new TrafficSign("W.243b", "Nơi đường sắt giao không vuông góc với đường bộ", "Đặt ở nơi cách ray ngoài cùng nơi giao đường sắt 100m, báo trước sắp đến vị trí giao cắt đường bộ với đường sắt cùng mức, không vuông góc và không có người gác, không có rào chắn.", "w243b.png", dangerSignId);
			trafficSignRepo.save(w243b);
			TrafficSign w243c = new TrafficSign("W.243c", "Nơi đường sắt giao không vuông góc với đường bộ", "Đặt ở nơi cách ray ngoài cùng nơi giao đường sắt 150m, báo trước sắp đến vị trí giao cắt đường bộ với đường sắt cùng mức, không vuông góc và không có người gác, không có rào chắn.", "w243c.png", dangerSignId);
			trafficSignRepo.save(w243c);
			TrafficSign w244 = new TrafficSign("W.244", "Đoạn đường hay xảy ra tai nạn", "Cảnh báo nguy hiểm đoạn đường phía trước thường xảy ra tai nạn để lái xe cần đặc biệt chú ý.", "w244.png", dangerSignId);
			trafficSignRepo.save(w244);
			TrafficSign w245a = new TrafficSign("W.245a", "Đi chậm", "Nhắc lái xe giảm tốc độ đi chậm.", "w245a.png", dangerSignId);
			trafficSignRepo.save(w245a);
			TrafficSign w245b = new TrafficSign("W.245b", "Đi chậm", "Nhắc lái xe giảm tốc độ đi chậm, đối với các tuyến đường đối ngoại.", "w245b.png", dangerSignId);
			trafficSignRepo.save(w245b);
			TrafficSign w246a = new TrafficSign("W.246a", "Chú ý chướng ngại vật - Vòng tránh ra hai bên", "Báo trước cho lái xe biết phía trước có chướng ngại vật, xe cần giảm tốc độ và đi vòng tránh ra hai bên.", "w246a.png", dangerSignId);
			trafficSignRepo.save(w246a);
			TrafficSign w246b = new TrafficSign("W.246b", "Chú ý chướng ngại vật - Vòng tránh sang bên trái", "Báo trước cho lái xe biết phía trước có chướng ngại vật, xe cần giảm tốc độ và đi vòng tránh sang bên trái.", "w246b.png", dangerSignId);
			trafficSignRepo.save(w246b);
			TrafficSign w246c = new TrafficSign("W.246c", "Chú ý chướng ngại vật - Vòng tránh sang bên phải", "Báo trước cho lái xe biết phía trước có chướng ngại vật, xe cần giảm tốc độ và đi vòng tránh sang bên phải.", "w246c.png", dangerSignId);
			trafficSignRepo.save(w246c);
			TrafficSign w247 = new TrafficSign("W.247", "Chú ý xe đỗ", "Cảnh báo có các loại xe ô tô; máy kéo; rơ moóc hoặc sơ mi rơ moóc được kéo bởi xe ô tô hoặc ôtô đầu kéo; xe máy chuyên dùng đang đỗ chiếm một phần đường xe chạy (biển này tương tự, chỉ lộn ngược đầu so với biển báo nguy hiểm số 208 \"Giao nhau với đường ưu tiên\").", "w247.png", dangerSignId);
			trafficSignRepo.save(w247);
			
			// Biển báo hiệu lệnh
			
			// Biển báo chỉ dẫn
			
			// Biển báo phụ
			
			// Vạch kẻ đường
		}
	}
	private void LoadQuestionType() {
		if(questionTypeRepo.count() == 0) {
			QuestionType conceptsAndRules = new QuestionType("KN", "Khái niệm và quy tắc giao thông", "Khái niệm và quy tắc giao thông");
			questionTypeRepo.save(conceptsAndRules);
			QuestionType cultureAndEthics = new QuestionType("VH", "Văn hóa và đạo đức", "Văn hóa và đạo đức");
			questionTypeRepo.save(cultureAndEthics);
			QuestionType drivingTechnique = new QuestionType("KT", "Kỹ thuật lái xe", "Kỹ thuật lái xe");
			questionTypeRepo.save(drivingTechnique);
			QuestionType roadSigns = new QuestionType("BB", "Biển báo đường bộ", "Biển báo đường bộ");
			questionTypeRepo.save(roadSigns);
			QuestionType pictures = new QuestionType("SH", "Sa hình", "Sa hình");
			questionTypeRepo.save(pictures);
		}
	}

}
