package team2.api.mobile.gplx.extension;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

	private void LoadRole() {
		if (roleRepo.count() == 0) {
			Role admin = new Role("Admin", "Administrator");
			roleRepo.save(admin);
			Role user = new Role("User", "User");
			roleRepo.save(user);
		}

	}

	private void LoadAccount() {
		if (accountRepo.count() == 0) {
			Role admin = roleRepo.findByRoleName("Admin");
			Role user = roleRepo.findByRoleName("User");
			Account account1 = new Account("admin", "admin123", "admin@gmail.com", "Nhật", "Võ", "",
					AccountStatus.ACTIVE, admin.getId());
			accountRepo.save(account1);
			Account account2 = new Account("user", "user123", "user@gmail.com", "Nhật", "Võ", "", AccountStatus.ACTIVE,
					user.getId());
			accountRepo.save(account2);
		}

	}

	private void LoadLicenseType() {
		licenseTypeRepo.deleteAll();
		LicenseType xe2Banh = new LicenseType("Xe mô tô", Status.ACTIVE, "Bằng lái xe mô tô");
		licenseTypeRepo.save(xe2Banh);
		LicenseType xe4Banh = new LicenseType("Xe ô tô", Status.ACTIVE, "Bằng lái xe ô tô");
		licenseTypeRepo.save(xe4Banh);

	}

	private void LoadLicense() {
		licenseRepo.deleteAll();
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

	private void LoadTrafficSignType() {
		trafficSignTypeRepo.deleteAll();
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
		TrafficSignType roadMarking = new TrafficSignType("V", "Vạch kẻ đường");
		trafficSignTypeRepo.save(roadMarking);

	}

	private void LoadTrafficSign() {
		trafficSignRepo.deleteAll();
		String probihitionSignId = trafficSignTypeRepo.findByCode("C").getId();
		String dangerSignId = trafficSignTypeRepo.findByCode("NH").getId();
		String commandSignId = trafficSignTypeRepo.findByCode("HL").getId();
		String directionSignId = trafficSignTypeRepo.findByCode("CD").getId();
		String subSignId = trafficSignTypeRepo.findByCode("P").getId();
		String roadMarkingId = trafficSignTypeRepo.findByCode("V").getId();
		// Biển báo cấm
		TrafficSign p101 = new TrafficSign("P.101", "Đường cấm",
				"Cấm các loại phương tiện di chuyển cả 2 hướng (ngoại trừ xe ưu tiên theo quy định).", "p101.png",
				probihitionSignId);
		trafficSignRepo.save(p101);
		TrafficSign p102 = new TrafficSign("P.102", "Cấm đi ngược chiều",
				"Cấm các loại phương tiện đi vào chiều đặt biển (ngoại trừ xe ưu tiên theo quy định).", "p102.png",
				probihitionSignId);
		trafficSignRepo.save(p102);
		TrafficSign p103a = new TrafficSign("P.103a", "Cấm ô tô",
				"Cấm các loại xe cơ giới đi vào, trừ xe máy 2 bánh, xe gắn máy và xe ưu tiên theo quy định.",
				"p103a.png", probihitionSignId);
		trafficSignRepo.save(p103a);
		TrafficSign p103b = new TrafficSign("P.103b", "Cấm ô tô rẽ phải",
				"Cấm các loại xe cơ giới rẽ phải, trừ xe máy 2 bánh, xe gắn máy và xe ưu tiên theo quy định.",
				"p103b.png", probihitionSignId);
		trafficSignRepo.save(p103b);
		TrafficSign p103c = new TrafficSign("P.103c", "Cấm ô tô rẽ trái",
				"Cấm các loại xe cơ giới rẽ trái, trừ xe máy 2 bánh, xe gắn máy và xe ưu tiên theo quy định.",
				"p103c.png", probihitionSignId);
		trafficSignRepo.save(p103c);
		TrafficSign p104 = new TrafficSign("P.104", "Cấm mô tô",
				"Cấm các loại xe máy đi vào (ngoại trừ xe máy được ưu tiên theo quy định). Biển không áp dụng với người dắt xe máy.",
				"p104.png", probihitionSignId);
		trafficSignRepo.save(p104);
		TrafficSign p105 = new TrafficSign("P.105", "Cấm ô tô và mô tô",
				"Cấm các loại xe cơ giới và xe máy đi vào, ngoại trừ xe gắn máy và xe ưu tiên theo quy định.",
				"p105.png", probihitionSignId);
		trafficSignRepo.save(p105);
		TrafficSign p106a = new TrafficSign("P.106a", "Cấm ô tô tải",
				"Cấm xe tải, xe máy kéo, các xe máy chuyên dùng đi vào (trừ xe ưu tiên theo quy định).", "p106a.png",
				probihitionSignId);
		trafficSignRepo.save(p106a);
		TrafficSign p106b = new TrafficSign("P.106b", "Cấm ô tô tải theo trọng lượng",
				"Cấm xe tải có khối lượng chuyên chở lớn hơn giá trị ghi trong biển báo đi vào.", "p106b.png",
				probihitionSignId);
		trafficSignRepo.save(p106b);
		TrafficSign p106c = new TrafficSign("P.106c", "Cấm ô tô tải chở hàng nguy hiểm",
				"Cấm xe tải chở hàng nguy hiểm đi vào.", "p106c.png", probihitionSignId);
		trafficSignRepo.save(p106c);
		TrafficSign p107 = new TrafficSign("P.107", "Cấm ô tô khách và ô tô tải",
				"Cấm các loại xe ô tô chở khách, xe tải, xe máy kéo, xe máy thi công chuyên dụng đi vào (trừ xe ưu tiên theo quy định).",
				"p107.png", probihitionSignId);
		trafficSignRepo.save(p107);
		TrafficSign p107a = new TrafficSign("P.107a", "Cấm ô tô khách",
				"Cấm các loại ô tô chở khách đi vào (trừ xe ưu tiên theo quy định). Không cấm xe buýt. Trong trường hợp cấm xe khách theo số chỗ ngồi sẽ có biển phụ bên dưới.",
				"p107a.png", probihitionSignId);
		trafficSignRepo.save(p107a);
		TrafficSign p107b = new TrafficSign("P.107b", "Cấm xe taxi",
				"Cấm xe taxi đi vào. Trong trường hợp cấm xe taxi theo giờ sẽ có biển phụ bên dưới.", "p107b.png",
				probihitionSignId);
		trafficSignRepo.save(p107b);
		TrafficSign p108 = new TrafficSign("P.108", "Cấm ô tô kéo rơ-mooc",
				"Cấm các loại xe cơ giới kéo rơ-mooc, kể cả xe khách – máy kéo – xe máy kéo theo rơ-mooc đi vào, trừ loại ô tô sơ-mi-rơ-mooc và xe ưu tiên theo quy định.",
				"p108.png", probihitionSignId);
		trafficSignRepo.save(p108);
		TrafficSign p108a = new TrafficSign("P.108a", "Cấm xe sơ-mi-rơ-mooc",
				"Cấm các loại xe sơ-mi-rơ-mooc, xe kéo rơ-mooc đi vào (trừ xe ưu tiên theo quy định).", "p108a.png",
				probihitionSignId);
		trafficSignRepo.save(p108a);
		TrafficSign p109 = new TrafficSign("P.109", "Cấm máy kéo",
				"Cấm các loại máy kéo, máy kéo bánh xích/bánh hơi đi vào.", "p109.png", probihitionSignId);
		trafficSignRepo.save(p109);
		TrafficSign p110a = new TrafficSign("P.110a", "Cấm xe đạp",
				"Cấm xe đạp đi vào. Không áp dụng cấm người dẫn xe đạp.", "p110a.png", probihitionSignId);
		trafficSignRepo.save(p110a);
		TrafficSign p110b = new TrafficSign("P.110b", "Cấm xe đạp thồ",
				"Cấm xe đạp thô. Không áp dụng cấm người dẫn xe đạp thô.", "p110b.png", probihitionSignId);
		trafficSignRepo.save(p110b);
		TrafficSign p111a = new TrafficSign("P.111a", "Cấm xe máy",
				"Cấm các loại xe máy, xe gắn máy đi vào. Không áp dụng cấm người đi xe đạp.", "p111a.png",
				probihitionSignId);
		trafficSignRepo.save(p111a);
		TrafficSign p111b = new TrafficSign("P.111b", "Cấm xe 3 bánh có động cơ",
				"Cấm xe 3 bánh có gắn động cơ như xe xích lô, xe lam, xe lôi máy…", "p111b.png", probihitionSignId);
		trafficSignRepo.save(p111b);
		TrafficSign p111c = new TrafficSign("P.111c", "Cấm xe 3 bánh không động cơ",
				"Cấm xe 3 bánh không gắn động cơ như xe xích lô, xe lôi đạp…", "p111c.png", probihitionSignId);
		trafficSignRepo.save(p111c);
		TrafficSign p112 = new TrafficSign("P.112", "Cấm người đi bộ", "Cấm người đi bộ đi vào.", "p112.png",
				probihitionSignId);
		trafficSignRepo.save(p112);
		TrafficSign p113 = new TrafficSign("P.113", "Cấm xe người kéo, đẩy",
				"Cấm xe thô sơ, xe do người đẩy/kéo đi vào. Không áp dụng cấm xe nôi trẻ em, phương tiện chuyên dùng của người khuyết tật.",
				"p113.png", probihitionSignId);
		trafficSignRepo.save(p113);
		TrafficSign p114 = new TrafficSign("P.114", "Cấm xe súc vật kéo",
				"Cấm xe sử dụng súc vật kéo hay chở trên lưng đi vào.", "p114.png", probihitionSignId);
		trafficSignRepo.save(p114);
		TrafficSign p115 = new TrafficSign("P.115", "Hạn chế tải trọng toàn bộ xe",
				"Cấm các loại xe cơ giới/thô sơ (kể cả xe ưu tiên) có tải trọng toàn bộ xe vượt quá trị số ghi trên biển đi vào.",
				"p115.png", probihitionSignId);
		trafficSignRepo.save(p115);
		TrafficSign p116 = new TrafficSign("P.116", "Hạn chế tải trọng trục xe",
				"Cấm các loại xe cơ giới/thô sơ (kể cả xe ưu tiên) có tải trọng toàn bộ xe phân bổ trên một trục xe vượt quá trị số ghi trên biển đi vào.",
				"p116.png", probihitionSignId);
		trafficSignRepo.save(p116);
		TrafficSign p117 = new TrafficSign("P.117", "Hạn chế chiều cao xe",
				"Cấm các loại xe cơ giới/thô sơ (kể cả xe ưu tiên) có chiều cao vượt quá trị số ghi trên biển đi vào.",
				"p117.png", probihitionSignId);
		trafficSignRepo.save(p117);
		TrafficSign p118 = new TrafficSign("P.118", "Hạn chế chiều ngang xe",
				"Cấm các loại xe cơ giới/thô sơ (kể cả xe ưu tiên) có chiều ngang vượt quá trị số ghi trên biển đi vào.",
				"p118.png", probihitionSignId);
		trafficSignRepo.save(p118);
		TrafficSign p119 = new TrafficSign("P.119", "Hạn chế chiều dài xe",
				"Cấm các loại xe cơ giới/thô sơ (kể cả xe ưu tiên) có chiều dài vượt quá trị số ghi trên biển đi vào.",
				"p119.png", probihitionSignId);
		trafficSignRepo.save(p119);
		TrafficSign p120 = new TrafficSign("P.120", "Hạn chế chiều dài xe kéo moóc",
				"Cấm các loại xe cơ giới kéo mooc, xe sơ-mi-rơ-mooc có chiều dài vượt quá trị số ghi trên biển (kể cả xe ưu tiên) đi vào.",
				"p120.png", probihitionSignId);
		trafficSignRepo.save(p120);
		TrafficSign p121 = new TrafficSign("P.121", "Cự ly tối thiểu giữa hai xe",
				"Các xe ô tô phải di chuyển cách nhau một khoảng tối thiểu ghi trên biển.", "p121.png",
				probihitionSignId);
		trafficSignRepo.save(p121);
		TrafficSign p123a = new TrafficSign("P.123a", "Cấm rẽ trái",
				"Cấm xe các loại xe cơ giới/thô sơ rẽ trái (trừ xe ưu tiên theo quy định). Không áp dụng cấm quay đầu xe.",
				"p123a.png", probihitionSignId);
		trafficSignRepo.save(p123a);
		TrafficSign p123b = new TrafficSign("P.123b", "Cấm rẽ phải",
				"Cấm xe các loại xe cơ giới/thô sơ rẽ phải (trừ xe ưu tiên theo quy định). Không áp dụng cấm quay đầu xe.",
				"p123b.png", probihitionSignId);
		trafficSignRepo.save(p123b);
		TrafficSign p124a = new TrafficSign("P.124a", "Cấm quay đầu xe",
				"Cấm các loại xe quay đầu kiểu chữ U theo chiều mũi tên trên biển.", "p124a.png", probihitionSignId);
		trafficSignRepo.save(p124a);
		TrafficSign p124b = new TrafficSign("P.124b", "Cấm xe ô tô quay đầu",
				"Cấm xe ô tô quay đầu kiểu chữ U theo chiều mũi tên trên biển.", "p124b.png", probihitionSignId);
		trafficSignRepo.save(p124b);
		TrafficSign p124c = new TrafficSign("P.124c", "Cấm rẽ trái và quay đầu xe",
				"Cấm các loại xe rẽ trái và quay đầu trái theo chiều mũi tên trên biển.", "p124c.png",
				probihitionSignId);
		trafficSignRepo.save(p124c);
		TrafficSign p124d = new TrafficSign("P.124d", "Cấm rẽ phải và quay đầu xe",
				"Cấm các loại xe rẽ phải và quay đầu phải theo chiều mũi tên trên biển.", "p124d.png",
				probihitionSignId);
		trafficSignRepo.save(p124d);
		TrafficSign p124e = new TrafficSign("P.124e", "Cấm xe ô tô rẽ trái và quay đầu xe",
				"Cấm xe ô tô rẽ trái và quay đầu trái theo chiều mũi tên trên biển.", "p124e.png", probihitionSignId);
		trafficSignRepo.save(p124e);
		TrafficSign p124f = new TrafficSign("P.124f", "Cấm xe ô tô rẽ phải và quay đầu xe",
				"Cấm xe ô tô rẽ phải và quay đầu phải theo chiều mũi tên trên biển.", "p124f.png", probihitionSignId);
		trafficSignRepo.save(p124f);
		TrafficSign p125 = new TrafficSign("P.125", "Cấm vượt",
				"Cấm các loại xe cơ giới vượt nhau (kể cả xe ưu tiên theo quy định), nhưng được phép vượt xe máy 2 bánh, xe gắn máy.",
				"p125.png", probihitionSignId);
		trafficSignRepo.save(p125);
		TrafficSign p126 = new TrafficSign("P.126", "Cấm ô tô tải vượt",
				"Cấm các loại xe tải vượt xe cơ giới khác, được phép vượt xe máy 2 bánh, xe gắn máy. Không áp dụng các loại xe cơ giới khác vượt xe nhau và vượt xe tải.",
				"p126.png", probihitionSignId);
		trafficSignRepo.save(p126);
		TrafficSign p127 = new TrafficSign("P.127", "Tốc độ tối đa cho phép",
				"Các xe cơ giới chạy không vượt quá tốc độ ghi trên biển (trừ xe ưu tiên theo quy định).", "p127.png",
				probihitionSignId);
		trafficSignRepo.save(p127);
		TrafficSign p127a = new TrafficSign("P.127a", "Tốc độ tối đa cho phép vào ban đêm",
				"Các xe cơ giới chạy không vượt quá tốc độ ghi trên biển (trừ xe ưu tiên theo quy định) chỉ áp dụng vào ban đêm.",
				"p127a.png", probihitionSignId);
		trafficSignRepo.save(p127a);
		TrafficSign p127b = new TrafficSign("P.127b", "Tốc độ tối đa trên từng làn đường",
				"Biển ghép tốc độ tối đa cho phép trên từng làn đường.", "p127b.png", probihitionSignId);
		trafficSignRepo.save(p127b);
		TrafficSign p127c = new TrafficSign("P.127c", "Tốc độ tối đa phương tiện theo từng làn đường",
				"Biển ghép tốc độ tối đa cho phép theo phương tiện, trên từng làn đường.", "p127c.png",
				probihitionSignId);
		trafficSignRepo.save(p127c);
		TrafficSign p127d = new TrafficSign("P.127d", "Biển hết hạn chế tốc độ tối đa",
				"Biển hết hạn chế tốc độ tối đa cho phép theo biển ghép.", "p127d.png", probihitionSignId);
		trafficSignRepo.save(p127d);
		TrafficSign p128 = new TrafficSign("P.128", "Cấm sử dụng còi", "Cấm các loại xe sử dụng còi.", "p128.png",
				probihitionSignId);
		trafficSignRepo.save(p128);
		TrafficSign p129 = new TrafficSign("P.129", "Kiểm tra",
				"Báo nơi đặt trạm kiểm tra, các loại xe vận tải đi qua phải dừng lại để kiểm tra theo quy định.",
				"p129.png", probihitionSignId);
		trafficSignRepo.save(p129);
		TrafficSign p130 = new TrafficSign("P.130", "Cấm dừng xe và đỗ xe",
				"Cấm các loại xe cơ giới dừng và đỗ xe phía đường có đặt biển (trừ xe ưu tiên theo quy định).",
				"p130.png", probihitionSignId);
		trafficSignRepo.save(p130);
		TrafficSign p131a = new TrafficSign("P.131a", "Cấm đỗ xe",
				"Cấm các loại xe cơ giới đỗ xe phía đường có đặt biển (trừ xe ưu tiên).", "p131a.png",
				probihitionSignId);
		trafficSignRepo.save(p131a);
		TrafficSign p131b = new TrafficSign("P.131b", "Cấm đỗ xe ngày lẻ",
				"Cấm các loại xe cơ giới đỗ xe phía đường có đặt biển (trừ xe ưu tiên) vào những ngày lẻ.", "p131b.png",
				probihitionSignId);
		trafficSignRepo.save(p131b);
		TrafficSign p131c = new TrafficSign("P.131c", "Cấm đỗ xe ngày chẵn",
				"Cấm các loại xe cơ giới đỗ xe phía đường có đặt biển (trừ xe ưu tiên) vào những ngày chẵn.",
				"p131c.png", probihitionSignId);
		trafficSignRepo.save(p131c);
		TrafficSign p132 = new TrafficSign("P.132", "Nhường đường cho xe cơ giới đi ngược chiều qua đường hẹp",
				"Các loại xe cơ giới/thô sơ (kể cả xe ưu tiên theo quy định) phải nhường đường cho các loại xe cơ giới đang di chuyển chiều ngược lại.",
				"p132.png", probihitionSignId);
		trafficSignRepo.save(p132);
		TrafficSign p133 = new TrafficSign("P.133", "Hết cấm vượt", "Biển thông báo hết đoạn đường cấm vượt.",
				"p133.png", probihitionSignId);
		trafficSignRepo.save(p133);
		TrafficSign p134 = new TrafficSign("P.134", "Hết hạn chế tốc độ tối đa",
				"Biển thông báo hết đoạn đường hạn chế tốc độ tối đa.", "p134.png", probihitionSignId);
		trafficSignRepo.save(p134);
		TrafficSign p135 = new TrafficSign("P.135", "Hết tất cả các lệnh cấm",
				"Biển thông báo hết đoạn đường áp dụng tất cả các lệnh cấm.", "p135.png", probihitionSignId);
		trafficSignRepo.save(p135);
		TrafficSign p136 = new TrafficSign("P.136", "Cấm đi thẳng",
				"Cấm các loại xe cơ giới/thô sơ đi thẳng vào đoạn đường phía trước.", "p136.png", probihitionSignId);
		trafficSignRepo.save(p136);
		TrafficSign p137 = new TrafficSign("P.137", "Cấm rẽ trái và rẽ phải",
				"Cấm các loại xe cơ giới rẽ trái, rẽ phải. Biển đặt ở những vị trí ngay trước nút giao đường. Trong trường hợp có quy định thời gian cấm sẽ có biển phụ ở dưới.",
				"p137.png", probihitionSignId);
		trafficSignRepo.save(p137);
		TrafficSign p138 = new TrafficSign("P.138", "Cấm đi thẳng và rẽ trái",
				"Cấm các loại xe cơ giới đi thẳng, rẽ trái. Biển đặt ở những vị trí ngay trước nút giao đường. Trong trường hợp có quy định thời gian cấm sẽ có biển phụ ở dưới.",
				"p138.png", probihitionSignId);
		trafficSignRepo.save(p138);
		TrafficSign p139 = new TrafficSign("P.139", "Cấm đi thẳng và rẽ phải",
				"Cấm các loại xe cơ giới đi thẳng, rẽ phải. Biển đặt ở những vị trí ngay trước nút giao đường. Trong trường hợp có quy định thời gian cấm sẽ có biển phụ ở dưới.",
				"p139.png", probihitionSignId);
		trafficSignRepo.save(p139);
		TrafficSign p140 = new TrafficSign("P.140", "Cấm xe công nông và các loại xe tương tự",
				"Cấm các loại xe công nông, xe tương tự công nông đi vào.", "p140.png", probihitionSignId);
		trafficSignRepo.save(p140);
		TrafficSign s508a = new TrafficSign("S.508a", "Biển cấm theo giờ",
				"Khi cần báo hiệu cấm các loại phương tiện giao thông đường bộ theo giờ trong thành phố, thị xã phải đặt biển phụ số S508(a,b) dưới biển cấm và có chú thích bằng tiếng Việt, phụ đề tiếng Anh trong biển này.",
				"s508a.png", probihitionSignId);
		trafficSignRepo.save(s508a);
		TrafficSign s508b = new TrafficSign("S.508b", "Biển cấm theo giờ",
				"Khi cần báo hiệu cấm các loại phương tiện giao thông đường bộ theo giờ trong thành phố, thị xã phải đặt biển phụ số S508(a,b) dưới biển cấm và có chú thích bằng tiếng Việt, phụ đề tiếng Anh trong biển này.",
				"s508b.png", probihitionSignId);
		trafficSignRepo.save(s508b);

		// Biển báo nguy hiểm
		TrafficSign w201a = new TrafficSign("W.201a", "Chỗ ngoặc nguy hiểm bên trái",
				"Báo trước sắp đến một chỗ ngoặt nguy hiểm phía bên trái.", "w201a.png", dangerSignId);
		trafficSignRepo.save(w201a);
		TrafficSign w201b = new TrafficSign("W.201b", "Chỗ ngoặc nguy hiểm bên phải",
				"Báo trước sắp đến một chỗ ngoặt nguy hiểm phía bên phải.", "w201b.png", dangerSignId);
		trafficSignRepo.save(w201b);
		TrafficSign w201c = new TrafficSign("W.201c", "Chỗ ngoặt nguy hiểm có nguy cơ lật xe bên phải",
				"Báo trước chỗ ngoặc nguy hiểm có nguy cơ lật xe bên phải.", "w201c.png", dangerSignId);
		trafficSignRepo.save(w201c);
		TrafficSign w201d = new TrafficSign("W.201d", "Chỗ ngoặt nguy hiểm có nguy cơ lật xe bên trái",
				"Báo trước chỗ ngoặc nguy hiểm có nguy cơ lật xe bên trái.", "w201d.png", dangerSignId);
		trafficSignRepo.save(w201d);
		TrafficSign w202a = new TrafficSign("W.202a", "Nhiều chỗ ngoặt nguy hiểm liên tiếp",
				"Báo trước sắp đến nhiều chỗ ngoặt nguy hiểm liên tiếp trong đó chỗ ngoặt đầu tiên hướng vòng bên trái.",
				"w202a.png", dangerSignId);
		trafficSignRepo.save(w202a);
		TrafficSign w202b = new TrafficSign("W.202b", "Nhiều chỗ ngoặt nguy hiểm liên tiếp",
				"Báo trước sắp đến nhiều chỗ ngoặt nguy hiểm liên tiếp trong đó chỗ ngoặt đầu tiên hướng vòng bên phải.",
				"w202b.png", dangerSignId);
		trafficSignRepo.save(w202b);
		TrafficSign w203a = new TrafficSign("W.203a", "Đường bị hẹp cả hai bên",
				"Báo trước sắp đến một đoạn đường bị hẹp đột ngột cả hai bên.", "w203a.png", dangerSignId);
		trafficSignRepo.save(w203a);
		TrafficSign w203b = new TrafficSign("W.203b", "Đường bị hẹp về phía trái",
				"Báo trước sắp đến một đoạn đường bị hẹp đột ngột cả hai bên.", "w203b.png", dangerSignId);
		trafficSignRepo.save(w203b);
		TrafficSign w203c = new TrafficSign("W.203c", "Đường bị hẹp về phía phải",
				"Báo trước sắp đến một đoạn đường bị hẹp đột ngột phía bên phải.", "w203c.png", dangerSignId);
		trafficSignRepo.save(w203c);
		TrafficSign w204 = new TrafficSign("W.204", "Đường hai chiều",
				"Báo trước sắp đến một đoạn đường do sửa chữa hoặc có trở ngại ở một phía đường mà phải tổ chức đi lại cho phương tiện cả hai chiều trên phía đường còn lại hoặc để báo trước đoạn đường đôi tạm thời hoặc đoạn đường có chiều xe đi và về đi chung.",
				"w204.png", dangerSignId);
		trafficSignRepo.save(w204);
		TrafficSign w205a = new TrafficSign("W.205a", "Đường giao nhau cùng cấp",
				"Báo trước sắp đến nơi giao nhau cùng mức của các tuyến đường cùng cấp (không có đường nào ưu tiên)  trên cùng một mặt bằng.",
				"w205a.png", dangerSignId);
		trafficSignRepo.save(w205a);
		TrafficSign w205b = new TrafficSign("W.205b", "Đường giao nhau cùng cấp",
				"Báo trước sắp đến nơi giao nhau cùng mức của các tuyến đường cùng cấp (không có đường nào ưu tiên)  trên cùng một mặt bằng.",
				"w205b.png", dangerSignId);
		trafficSignRepo.save(w205b);
		TrafficSign w205c = new TrafficSign("W.205c", "Đường giao nhau cùng cấp",
				"Báo trước sắp đến nơi giao nhau cùng mức của các tuyến đường cùng cấp (không có đường nào ưu tiên)  trên cùng một mặt bằng.",
				"w205c.png", dangerSignId);
		trafficSignRepo.save(w205c);
		TrafficSign w205d = new TrafficSign("W.205d", "Đường giao nhau cùng cấp",
				"Báo trước sắp đến nơi giao nhau cùng mức của các tuyến đường cùng cấp (không có đường nào ưu tiên)  trên cùng một mặt bằng.",
				"w205d.png", dangerSignId);
		trafficSignRepo.save(w205d);
		TrafficSign w205e = new TrafficSign("W.205e", "Đường giao nhau cùng cấp",
				"Báo trước sắp đến nơi giao nhau cùng mức của các tuyến đường cùng cấp (không có đường nào ưu tiên)  trên cùng một mặt bằng.",
				"w205e.png", dangerSignId);
		trafficSignRepo.save(w205e);
		TrafficSign w206 = new TrafficSign("W.206", "Giao nhau chạy theo vòng xuyến",
				"Báo trước sắp đến nơi giao nhau có bố trí đảo an toàn ở giữa nút giao, các loại xe qua nút giao phải đi vòng xuyến quanh đảo an toàn theo chiều mũi tên.",
				"w206.png", dangerSignId);
		trafficSignRepo.save(w206);
		TrafficSign w207a = new TrafficSign("W.207a", "Giao nhau với đường không ưu tiên",
				"Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207a.png", dangerSignId);
		trafficSignRepo.save(w207a);
		TrafficSign w207b = new TrafficSign("W.207b", "Giao nhau với đường không ưu tiên",
				"Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207b.png", dangerSignId);
		trafficSignRepo.save(w207b);
		TrafficSign w207c = new TrafficSign("W.207c", "Giao nhau với đường không ưu tiên",
				"Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207c.png", dangerSignId);
		trafficSignRepo.save(w207c);
		TrafficSign w207d = new TrafficSign("W.207d", "Giao nhau với đường không ưu tiên",
				"Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207d.png", dangerSignId);
		trafficSignRepo.save(w207d);
		TrafficSign w207e = new TrafficSign("W.207e", "Giao nhau với đường không ưu tiên",
				"Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207e.png", dangerSignId);
		trafficSignRepo.save(w207e);
		TrafficSign w207f = new TrafficSign("W.207f", "Giao nhau với đường không ưu tiên",
				"Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207f.png", dangerSignId);
		trafficSignRepo.save(w207f);
		TrafficSign w207g = new TrafficSign("W.207g", "Giao nhau với đường không ưu tiên",
				"Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207g.png", dangerSignId);
		trafficSignRepo.save(w207g);
		TrafficSign w207h = new TrafficSign("W.207h", "Giao nhau với đường không ưu tiên",
				"Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207h.png", dangerSignId);
		trafficSignRepo.save(w207h);
		TrafficSign w207i = new TrafficSign("W.207i", "Giao nhau với đường không ưu tiên",
				"Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207i.png", dangerSignId);
		trafficSignRepo.save(w207i);
		TrafficSign w207k = new TrafficSign("W.207k", "Giao nhau với đường không ưu tiên",
				"Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207k.png", dangerSignId);
		trafficSignRepo.save(w207k);
		TrafficSign w208 = new TrafficSign("W.208", "Giao nhau với đường ưu tiên",
				"Báo trước sắp đến nơi giao nhau với đường ưu tiên.", "w208.png", dangerSignId);
		trafficSignRepo.save(w208);
		TrafficSign w209 = new TrafficSign("W.209", "Giao nhau có tín hiệu đèn",
				"Báo trước nơi giao nhau có điều khiển giao thông bằng tín hiệu đèn trong trường hợp người lái xe khó quan sát để kịp thời xử lý.",
				"w209.png", dangerSignId);
		trafficSignRepo.save(w209);
		TrafficSign w210 = new TrafficSign("W.210", "Giao nhau với đường sắt có rào chắn",
				"Báo trước sắp đến chỗ giao nhau giữa đường bộ và đường sắt có rào chắn kín hay rào chắn nửa kín và có nhân viên ngành đường sắt điều khiển giao thông.",
				"w210.png", dangerSignId);
		trafficSignRepo.save(w210);
		TrafficSign w211a = new TrafficSign("W.211a", "Giao nhau với đường sắt không có rào chắn",
				"Báo trước sắp đến chỗ giao nhau giữa đường bộ và đường sắt không có rào chắn, không có người điều khiển giao thông.",
				"w211a.png", dangerSignId);
		trafficSignRepo.save(w211a);
		TrafficSign w211b = new TrafficSign("W.211b", "Giao nhau với đường tàu điện",
				"Chỉ nơi đường bộ giao nhau cùng mức với đường tàu điện.", "w211b.png", dangerSignId);
		trafficSignRepo.save(w211b);
		TrafficSign w212 = new TrafficSign("W.212", "Cầu hẹp",
				"Báo trước sắp đến cầu hẹp là loại cầu có chiều rộng phần xe chạy nhỏ hơn hoặc bằng 4,5m.", "w212.png",
				dangerSignId);
		trafficSignRepo.save(w212);
		TrafficSign w213 = new TrafficSign("W.213", "Cầu tạm",
				"Báo trước sắp đến cầu tạm là loại cầu được làm để sử dụng tạm thời cho xe cộ qua lại.", "w213.png",
				dangerSignId);
		trafficSignRepo.save(w213);
		TrafficSign w214 = new TrafficSign("W.214", "Cầu quay-cầu cất",
				"Báo trước sắp đến cầu xoay, cầu cất là loại cầu trong từng thời gian có cắt giao thông đường bộ bằng cách quay hoặc nâng nhịp thông thuyền để cho tàu thuyền qua lại.",
				"w214.png", dangerSignId);
		trafficSignRepo.save(w214);
		TrafficSign w215 = new TrafficSign("W.215", "Kè, vực sâu phía trước",
				"Báo trước sắp đến những vị trí có kè chắn vực sâu, hoặc sông suối đi sát đường, cần đề phòng tình huống nguy hiểm rơi xuống vực sâu hoặc sông su ối (thường có ở những chỗ ngoặt nguy hiểm).",
				"w215.png", dangerSignId);
		trafficSignRepo.save(w215);
		TrafficSign w216 = new TrafficSign("W.216", "Đường ngầm",
				"Báo trước sắp đến những vị trí có đường ngầm (đường tràn) phải đặt biển số 216 \"Đường ngầm\".",
				"w216.png", dangerSignId);
		trafficSignRepo.save(w216);
		TrafficSign w217 = new TrafficSign("W.217", "Bến phà", "Báo trước sắp đến bến phà.", "w217.png", dangerSignId);
		trafficSignRepo.save(w217);
		TrafficSign w218 = new TrafficSign("W.218", "Cửa chui",
				"Báo trước sắp đến đường có cổng chắn ngang, kiểu cổng như đường hầm, cổng thành, cầu vượt đường bộ dạng cầu vòm v.v....",
				"w218.png", dangerSignId);
		trafficSignRepo.save(w218);
		TrafficSign w219 = new TrafficSign("W.219", "Dốc xuống nguy hiểm",
				"Báo trước sắp đến đoạn đường xuống dốc nguy hiểm.", "w219.png", dangerSignId);
		trafficSignRepo.save(w219);
		TrafficSign w220 = new TrafficSign("W.220", "Dốc lên nguy hiểm",
				"Báo trước sắp đến đoạn đường lên dốc nguy hiểm.", "w220.png", dangerSignId);
		trafficSignRepo.save(w220);
		TrafficSign w221a = new TrafficSign("W.221a", "Đường có ổ gà, lồi lõm",
				"Đặt trong trường hợp đường đang tốt, xe chạy nhanh, chuyển sang những đoạn lồi lõm, gập ghềnh, ổ gà, lượn sóng.",
				"w221a.png", dangerSignId);
		trafficSignRepo.save(w221a);
		TrafficSign w221b = new TrafficSign("W.221b", "Đường có sóng mấp mô nhân tạo",
				"Để hạn chế tốc độ xe chạy (biển được cắm kèm theo biển số 227 \"Hạn chế tốc độ tối đa\"), bắt buộc lái xe phải chạy với tốc độ chậm trước khi qua những điểm cần kiểm soát, kiểm tra hoặc giảm tốc độ trước khi vào đoạn đường hạn chế tốc độ tối đa.",
				"w221b.png", dangerSignId);
		trafficSignRepo.save(w221b);
		TrafficSign w222a = new TrafficSign("W.222a", "Đường trơn",
				"Báo trước sắp tới đoạn đường có thể xảy ra trơn trượt đặc biệt là khi thời tiết xấu, mưa phùn (hệ số bám của lốp với mặt đường < 0,3) cần tránh hãm phanh, tăng ga, sang số đột ngột hoặc xe chạy với tốc độ cao sẽ bị nguy hiểm.",
				"w222a.png", dangerSignId);
		trafficSignRepo.save(w222a);
		TrafficSign w222b = new TrafficSign("W.222b", "Lề đường nguy hiểm",
				"Báo những nơi lề đường không ổn định, khi xe đi vào dễ gây văng đất đá hoặc bánh xe quay tại chỗ.",
				"w222b.png", dangerSignId);
		trafficSignRepo.save(w222b);
		TrafficSign w223a = new TrafficSign("W.223a", "Vách núi nguy hiểm",
				"Báo hiệu sắp đi vào đoạn đường đi sát vách núi nằm ở bên tay trái, đường vừa hẹp vừa hạn chế tầm nhìn, người lái xe phải cẩn thận.",
				"w223a.png", dangerSignId);
		trafficSignRepo.save(w223a);
		TrafficSign w223b = new TrafficSign("W.223b", "Vách núi nguy hiểm",
				"Báo hiệu sắp đi vào đoạn đường đi sát vách núi nằm ở bên tay phải, đường vừa hẹp vừa hạn chế tầm nhìn, người lái xe phải cẩn thận.",
				"w223b.png", dangerSignId);
		trafficSignRepo.save(w223b);
		TrafficSign w224 = new TrafficSign("W.224", "Đường người đi bộ cắt ngang",
				"Báo trước sắp tới phần đường dành cho người đi bộ sang qua đường. Gặp biển này các xe phải giảm tốc độ, nhường ưu tiên cho người đi bộ và chỉ được chạy xe khi không gây nguy hiểm cho người đi bộ.",
				"w224.png", dangerSignId);
		trafficSignRepo.save(w224);
		TrafficSign w225 = new TrafficSign("W.225", "Trẻ em",
				"Báo trước là gần đến đoạn đường thường có trẻ em đi ngang qua hoặc tụ tập trên đường như ở vườn trẻ, trường học, câu lạc bộ.",
				"w225.png", dangerSignId);
		trafficSignRepo.save(w225);
		TrafficSign w226 = new TrafficSign("W.226", "Đường người đi xe đạp cắt ngang",
				"Báo trước là gần tới vị trí thường có người đi xe đạp từ những đường nhỏ cắt ngang qua hoặc từ đường dành cho xe đạp đi nhập vào đường ôtô.",
				"w226.png", dangerSignId);
		trafficSignRepo.save(w226);
		TrafficSign w227 = new TrafficSign("W.227", "Công trường",
				"Báo trước gần tới đoạn đường đang tiến hành thi công sửa chữa, cải tạo, nâng cấp có người và máy móc đang làm việc trên mặt đường. Khi gặp biển báo này tốc độ xe chạy phải giảm cho thích hợp, không gây nguy hiểm cho người và máy móc trên đoạn đường đó.",
				"w227.png", dangerSignId);
		trafficSignRepo.save(w227);
		TrafficSign w228a = new TrafficSign("W.228a", "Đá lở",
				"Báo trước gần tới đoạn đường có hiện tượng đất đá từ trên ta luy dương sụt lở bất ngờ gây nguy hiểm cho xe cộ và người đi đường, đặc biệt là ở những đoạn đường miền núi. Gặp biển này, người lái xe phải chú ý; đặc biệt khi thời tiết xấu, hạn chế tầm nhìn, không dừng hay đỗ xe trong khu vực đá lở sau những trận mưa lớn.",
				"w228a.png", dangerSignId);
		trafficSignRepo.save(w228a);
		TrafficSign w228b = new TrafficSign("W.228b", "Đá lở",
				"Báo trước gần tới đoạn đường có hiện tượng đất đá từ trên ta luy dương sụt lở bất ngờ gây nguy hiểm cho xe cộ và người đi đường, đặc biệt là ở những đoạn đường miền núi. Gặp biển này, người lái xe phải chú ý; đặc biệt khi thời tiết xấu, hạn chế tầm nhìn, không dừng hay đỗ xe trong khu vực đá lở sau những trận mưa lớn.",
				"w228b.png", dangerSignId);
		trafficSignRepo.save(w228b);
		TrafficSign w228c = new TrafficSign("W.228c", "Sỏi đá bắn lên",
				"Báo trước nơi có kết cấu mặt đường rời rạc, khi phương tiện đi qua, làm cho các viên đá, sỏi băng lên gây nguy hiểm và mất an toàn cho người và phương tiện tham gia giao thông.",
				"w228c.png", dangerSignId);
		trafficSignRepo.save(w228c);
		TrafficSign w229 = new TrafficSign("W.229", "Giải máy bay lên xuống",
				"Báo trước đoạn đường ở vùng sát đường băng sân bay và cắt ngang qua hướng máy bay lên xuống ở độ cao không lớn.",
				"w229.png", dangerSignId);
		trafficSignRepo.save(w229);
		TrafficSign w230 = new TrafficSign("W.230", "Gia súc",
				"Báo trước gần tới đoạn đường thường có gia súc thả rông hoặc lùa qua ngang đường, đường ở vùng đồng cỏ của nông trường chăn nuôi, vùng thảo nguyên... Người lái xe có trách nhiệm đi chậm, quan sát và dừng lại bảo đảm cho gia súc có thể qua đường không bị nguy hiểm.",
				"w230.png", dangerSignId);
		trafficSignRepo.save(w230);
		TrafficSign w231 = new TrafficSign("W.231", "Thú rừng vượt qua đường",
				"Báo trước gần tới đoạn đường thường có thú rừng qua đường như đường đi qua rừng hay khu vực bảo tồn thiên nhiên cấm săn bắn. Người lái xe phải đi chậm, chú ý quan sát hai bên đường và thận trọng đề phòng tai nạn.",
				"w231.png", dangerSignId);
		trafficSignRepo.save(w231);
		TrafficSign w232 = new TrafficSign("W.232", "Gió ngang",
				"Báo trước gần tới đoạn đường thường có gió ngang thổi mạnh gây nguy hiểm. Người lái xe cần phải điều chỉnh tốc độ xe chạy cho thích hợp, đề phòng gió thổi mạnh gây lật xe.",
				"w232.png", dangerSignId);
		trafficSignRepo.save(w232);
		TrafficSign w233 = new TrafficSign("W.233", "Nguy hiểm khác",
				"Báo trên đường có những nguy hiểm mà không thể vận dụng được các kiểu biển từ biển số 201a đến biển số 232.",
				"w233.png", dangerSignId);
		trafficSignRepo.save(w233);
		TrafficSign w234 = new TrafficSign("W.234", "Giao nhau với đường hai chiều",
				"Đặt trên đường một chiều, để báo trước sắp đến vị trí giao nhau với đường hai chiều.", "w234.png",
				dangerSignId);
		trafficSignRepo.save(w234);
		TrafficSign w235 = new TrafficSign("W.235", "Đường đôi",
				"Báo trước sắp đến đoạn đường có chiều đi và chiều về phân biệt bằng giải phân cách cứng.", "w235.png",
				dangerSignId);
		trafficSignRepo.save(w235);
		TrafficSign w236 = new TrafficSign("W.236", "Hết đường đôi",
				"Báo trước sắp kết thúc đoạn đường có chiều đi và chiều về phân biệt bằng giải phân cách cứng.",
				"w236.png", dangerSignId);
		trafficSignRepo.save(w236);
		TrafficSign w237 = new TrafficSign("W.237", "Cầu vồng",
				"Đặt ở trên đoạn đường sắp đến công trình có độ vồng lớn ảnh hưởng tới tầm nhìn, nhắc nhở lái xe lái cẩn thận.",
				"w237.png", dangerSignId);
		trafficSignRepo.save(w237);
		TrafficSign w238 = new TrafficSign("W.238", "Đường cao tốc phía trước",
				"Được đặt trên đường nhánh nhập vào đường cao tốc để báo cho các phương tiện đi trên đường này biết có Đường cao tốc ở phía trước.",
				"w238.png", dangerSignId);
		trafficSignRepo.save(w238);
		TrafficSign w239 = new TrafficSign("W.239", "Đường cáp điện ở phía trên",
				"Được đặt Ở những nơi có đường dây điện cắt ngang phía trên tuyến đường.", "w239.png", dangerSignId);
		trafficSignRepo.save(w239);
		TrafficSign w240 = new TrafficSign("W.240", "Đường hầm",
				"Nhắc lái xe chú ý chuẩn bị đi vào hầm đường bộ. Biển đặt ở bên phải chiều đi trước khi vào hầm.",
				"w240.png", dangerSignId);
		trafficSignRepo.save(w240);
		TrafficSign w241 = new TrafficSign("W.241", "Ùn tắc giao thông", "Báo đoạn đường hay xảy ra ùn tắc giao thông.",
				"w241.png", dangerSignId);
		trafficSignRepo.save(w241);
		TrafficSign w242a = new TrafficSign("W.242a", "Nơi đường sắt giao vuông góc với đường bộ",
				"Bổ sung cho biển số 211 \"Giao nhau với đường sắt không có rào chắn\", để chỉ chỗ đường sắt giao vuông góc đường bộ, và tại chỗ giao nhau đường sắt chỉ có một đường cắt ngang đường bộ.",
				"w242a.png", dangerSignId);
		trafficSignRepo.save(w242a);
		TrafficSign w242b = new TrafficSign("W.242b", "Nơi đường sắt giao vuông góc với đường bộ",
				"Bổ sung cho biển số 211 \"Giao nhau với đường sắt không có rào chắn\", để chỉ chỗ đường sắt giao vuông góc đường bộ, và tại chỗ giao nhau đường sắt có từ hai đường cắt ngang đường bộ.",
				"w242b.png", dangerSignId);
		trafficSignRepo.save(w242b);
		TrafficSign w243a = new TrafficSign("W.243a", "Nơi đường sắt giao không vuông góc với đường bộ",
				"Đặt ở nơi cách ray ngoài cùng nơi giao đường sắt 50m, báo trước sắp đến vị trí giao cắt đường bộ với đường sắt cùng mức, không vuông góc và không có người gác, không có rào chắn.",
				"w243a.png", dangerSignId);
		trafficSignRepo.save(w243a);
		TrafficSign w243b = new TrafficSign("W.243b", "Nơi đường sắt giao không vuông góc với đường bộ",
				"Đặt ở nơi cách ray ngoài cùng nơi giao đường sắt 100m, báo trước sắp đến vị trí giao cắt đường bộ với đường sắt cùng mức, không vuông góc và không có người gác, không có rào chắn.",
				"w243b.png", dangerSignId);
		trafficSignRepo.save(w243b);
		TrafficSign w243c = new TrafficSign("W.243c", "Nơi đường sắt giao không vuông góc với đường bộ",
				"Đặt ở nơi cách ray ngoài cùng nơi giao đường sắt 150m, báo trước sắp đến vị trí giao cắt đường bộ với đường sắt cùng mức, không vuông góc và không có người gác, không có rào chắn.",
				"w243c.png", dangerSignId);
		trafficSignRepo.save(w243c);
		TrafficSign w244 = new TrafficSign("W.244", "Đoạn đường hay xảy ra tai nạn",
				"Cảnh báo nguy hiểm đoạn đường phía trước thường xảy ra tai nạn để lái xe cần đặc biệt chú ý.",
				"w244.png", dangerSignId);
		trafficSignRepo.save(w244);
		TrafficSign w245a = new TrafficSign("W.245a", "Đi chậm", "Nhắc lái xe giảm tốc độ đi chậm.", "w245a.png",
				dangerSignId);
		trafficSignRepo.save(w245a);
		TrafficSign w245b = new TrafficSign("W.245b", "Đi chậm",
				"Nhắc lái xe giảm tốc độ đi chậm, đối với các tuyến đường đối ngoại.", "w245b.png", dangerSignId);
		trafficSignRepo.save(w245b);
		TrafficSign w246a = new TrafficSign("W.246a", "Chú ý chướng ngại vật - Vòng tránh ra hai bên",
				"Báo trước cho lái xe biết phía trước có chướng ngại vật, xe cần giảm tốc độ và đi vòng tránh ra hai bên.",
				"w246a.png", dangerSignId);
		trafficSignRepo.save(w246a);
		TrafficSign w246b = new TrafficSign("W.246b", "Chú ý chướng ngại vật - Vòng tránh sang bên trái",
				"Báo trước cho lái xe biết phía trước có chướng ngại vật, xe cần giảm tốc độ và đi vòng tránh sang bên trái.",
				"w246b.png", dangerSignId);
		trafficSignRepo.save(w246b);
		TrafficSign w246c = new TrafficSign("W.246c", "Chú ý chướng ngại vật - Vòng tránh sang bên phải",
				"Báo trước cho lái xe biết phía trước có chướng ngại vật, xe cần giảm tốc độ và đi vòng tránh sang bên phải.",
				"w246c.png", dangerSignId);
		trafficSignRepo.save(w246c);
		TrafficSign w247 = new TrafficSign("W.247", "Chú ý xe đỗ",
				"Cảnh báo có các loại xe ô tô; máy kéo; rơ moóc hoặc sơ mi rơ moóc được kéo bởi xe ô tô hoặc ôtô đầu kéo; xe máy chuyên dùng đang đỗ chiếm một phần đường xe chạy (biển này tương tự, chỉ lộn ngược đầu so với biển báo nguy hiểm số 208 \"Giao nhau với đường ưu tiên\").",
				"w247.png", dangerSignId);
		trafficSignRepo.save(w247);

		// Biển báo hiệu lệnh
		TrafficSign r301a = new TrafficSign("R.301a", "Hướng đi phải theo",
				"Các xe chỉ được đi thẳng (trừ xe được quyền ưu tiên theo quy định).", "r301a.png", commandSignId);
		trafficSignRepo.save(r301a);
		TrafficSign r301b = new TrafficSign("R.301b", "Hướng đi phải theo",
				"Các xe chỉ được rẽ phải (trừ xe được quyền ưu tiên theo quy định). Biển này được đặt ở sau  nơi đường giao nhau.",
				"r301b.png", commandSignId);
		trafficSignRepo.save(r301b);
		TrafficSign r301c = new TrafficSign("R.301c", "Hướng đi phải theo",
				"Các xe chỉ được rẽ trái (trừ xe được quyền ưu tiên theo quy định). Biển này được đặt ở sau nơi đường giao nhau.",
				"r301c.png", commandSignId);
		trafficSignRepo.save(r301c);
		TrafficSign r301d = new TrafficSign("R.301d", "Hướng đi phải theo",
				"Các xe chỉ được rẽ phải (trừ xe được quyền ưu tiên theo quy định). Biển này được đặt ở trước nơi đường giao nhau, có tác dụng tại nơi giao nhau đằng sau mặt biển.",
				"r301d.png", commandSignId);
		trafficSignRepo.save(r301d);
		TrafficSign r301e = new TrafficSign("R.301e", "Hướng đi phải theo",
				"Các xe chỉ được rẽ trái (trừ xe được quyền ưu tiên theo quy định).  Biển này được đặt ở trước nơi đường giao nhau, có tác dụng tại nơi giao nhau đằng sau mặt biển.",
				"r301e.png", commandSignId);
		trafficSignRepo.save(r301e);
		TrafficSign r301f = new TrafficSign("R.301f", "Hướng đi phải theo",
				"Các xe chỉ được đi thẳng và rẽ phải (trừ xe được quyền ưu tiên theo quy định).", "r301f.png",
				commandSignId);
		trafficSignRepo.save(r301f);
		TrafficSign r301h = new TrafficSign("R.301h", "Hướng đi phải theo",
				"Các xe chỉ được đi thẳng và rẽ trái (trừ xe được quyền ưu tiên theo quy định).", "r301h.png",
				commandSignId);
		trafficSignRepo.save(r301h);
		TrafficSign r301i = new TrafficSign("R.301i", "Hướng đi phải theo",
				"Các xe chỉ được rẽ phải và rẽ trái (trừ xe được quyền ưu tiên theo quy định).", "r301i.png",
				commandSignId);
		trafficSignRepo.save(r301i);
		TrafficSign r302a = new TrafficSign("R.302a", "Hướng phải đi vòng chướng ngại vật",
				"Báo các loại xe (cơ giới và thô sơ) hướng đi vòng sang phải để qua một chướng ngại vật.", "r302a.png",
				commandSignId);
		trafficSignRepo.save(r302a);
		TrafficSign r302b = new TrafficSign("R.302b", "Hướng phải đi vòng chướng ngại vật",
				"Báo các loại xe (cơ giới và thô sơ) hướng đi vòng sang trái để qua một chướng ngại vật.", "r302b.png",
				commandSignId);
		trafficSignRepo.save(r302b);
		TrafficSign r303 = new TrafficSign("R.303", "Nơi giao nhau chạy theo vòng xuyến",
				"Báo cho các loại xe (thô sơ và cơ giới) phải chạy vòng theo đảo an toàn ở nơi đường giao nhau.",
				"r303.png", commandSignId);
		trafficSignRepo.save(r303);
		TrafficSign r304 = new TrafficSign("R.304", "Đường dành cho xe thô sơ",
				"Báo đường dành cho xe thô sơ (kể cả xe của người tàn tật) và người đi bộ. Biển bắt buộc các  loại xe thô sơ (kể cả xe của người tàn tật) và người đi bộ phải dùng đường dành riêng này để đi và cấm các xe cơ giới kể cả xe gắn máy, các xe được ưu tiên theo quy định đi vào đường đã đặt biển này.",
				"r304.png", commandSignId);
		trafficSignRepo.save(r304);
		TrafficSign r305 = new TrafficSign("R.305", "Đường dành cho người đi bộ",
				"Báo đường dành cho người đi bộ. Các loại xe cơ giới và thô sơ kể cả các xe được ưu tiên theo quy định không được phép đi vào đường đã đặt biển này, trừ trường hợp đi cắt ngang qua nhưng phải đảm bảo tuyệt đối an toàn cho người đi bộ.",
				"r305.png", commandSignId);
		trafficSignRepo.save(r305);
		TrafficSign r306 = new TrafficSign("R.306", "Tốc độ tối thiểu cho phép",
				"Báo tốc độ tối thiểu cho phép các xe cơ giới chạy. Biển cấm các loại xe cơ giới chạy với tốc độ nhỏ hơn trị số ghi trên biển.",
				"r306.png", commandSignId);
		trafficSignRepo.save(r306);
		TrafficSign r307 = new TrafficSign("R.307", "Hết hạn chế tốc độ tối thiểu",
				"Báo hết đoạn đường hạn chế tốc độ tối thiểu. Kể từ biển này các xe được phép chạy chậm hơn trị số ghi trên biển nhưng không được gây cản trở các xe khác.",
				"r307.png", commandSignId);
		trafficSignRepo.save(r307);
		TrafficSign r308a = new TrafficSign("R.308a", "Tuyến đường cầu vượt cắt qua",
				"Biểu thị phía trước có cầu vượt, xe có thể đi thẳng hoặc theo chỉ dẫn trên hình vẽ để rẽ trái.",
				"r308a.png", commandSignId);
		trafficSignRepo.save(r308a);
		TrafficSign r308b = new TrafficSign("R.308b", "Tuyến đường cầu vượt cắt qua",
				"Biểu thị phía trước có cầu vượt, xe có thể đi thẳng hoặc theo chỉ dẫn trên hình vẽ để rẽ phải.",
				"r308b.png", commandSignId);
		trafficSignRepo.save(r308b);
		TrafficSign r309 = new TrafficSign("R.309", "Ấn còi",
				"Biểu thị xe cộ đi đến vị trí cắm biển đó thì phải ấn còi.", "r309.png", commandSignId);
		trafficSignRepo.save(r309);
		TrafficSign r310a = new TrafficSign("R.310a", "Hướng đi phải theo cho các xe chở hàng nguy hiểm",
				"báo cho các loại xe chở hàng nguy hiểm phải đi theo hướng quy định (rẽ trái).", "r310a.png",
				commandSignId);
		trafficSignRepo.save(r310a);
		TrafficSign r310b = new TrafficSign("R.310b", "Hướng đi phải theo cho các xe chở hàng nguy hiểm",
				"báo cho các loại xe chở hàng nguy hiểm phải đi theo hướng quy định (đi thẳng).", "r310b.png",
				commandSignId);
		trafficSignRepo.save(r310b);
		TrafficSign r310c = new TrafficSign("R.310c", "Hướng đi phải theo cho các xe chở hàng nguy hiểm",
				"báo cho các loại xe chở hàng nguy hiểm phải đi theo hướng quy định (rẽ phải).", "r310c.png",
				commandSignId);
		trafficSignRepo.save(r310c);

		// Biển báo chỉ dẫn
		TrafficSign i401 = new TrafficSign("I.401", "Bắt đầu đường ưu tiên",
				"Để biểu thị ưu tiên cho các phương tiện trên đường có đặt biển này được đi trước. Biển đặt tại vị trí thích hợp trước khi đường nhánh sắp nhập vào trục đường chính, yêu cầu phương tiện từ đường nhánh ra phải dừng lại nhường cho phương tiện trên đường chính đi trước.",
				"i401.png", directionSignId);
		trafficSignRepo.save(i401);
		TrafficSign i402 = new TrafficSign("I.402", "Hết đoạn đường ưu tiên", "Báo hiệu hết đoạn đường được ưu tiên.",
				"i402.png", directionSignId);
		trafficSignRepo.save(i402);
		TrafficSign i403a = new TrafficSign("I.403a", "Đường dành cho ôtô",
				"Để chỉ dẫn bắt đầu đường dành cho các loại ôtô đi lại, các loại phương tiện giao thông khác không được phép đi vào đoạn đường có dặt biển này.",
				"i403a.png", directionSignId);
		trafficSignRepo.save(i403a);
		TrafficSign i403b = new TrafficSign("I.403b", "Đường dành cho ô tô, xe máy",
				"Để chỉ dẫn bắt đầu đường dành cho các loại ôtô, xe máy (kể cả xe gắn máy) đi lại, các loại phương tiện giao thông khác không được phép đi vào đoạn đường có đặt biển này.",
				"i403b.png", directionSignId);
		trafficSignRepo.save(i403b);
		TrafficSign i404a = new TrafficSign("I.404a", "Hết đường dành cho ô tô",
				"Để chỉ dẫn hết đoạn đường dành cho ôtô đi lại.", "i404a.png", directionSignId);
		trafficSignRepo.save(i404a);
		TrafficSign i404b = new TrafficSign("I.404b", "Hết đường dành cho ô tô, xe máy",
				"Để chỉ dẫn hết đoạn đường dành cho ôtô, xe máy đi lại.", "i404b.png", directionSignId);
		trafficSignRepo.save(i404b);
		TrafficSign i405a = new TrafficSign("I.405a", "Đường cụt",
				"Để chỉ dẫn đường cụt, lối rẽ vào đường cụt phía bên phải.", "i405a.png", directionSignId);
		trafficSignRepo.save(i405a);
		TrafficSign i405b = new TrafficSign("I.405b", "Đường cụt",
				"Để chỉ dẫn đường cụt, lối rẽ vào đường cụt phía bên trái.", "i405b.png", directionSignId);
		trafficSignRepo.save(i405b);
		TrafficSign i405c = new TrafficSign("I.405c", "Đường cụt",
				"Để chỉ dẫn phía trước là đường cụt, đặt trước đường cụt 300m đến 500m và cứ 100m phải đặt thêm một biển.",
				"i405c.png", directionSignId);
		trafficSignRepo.save(i405c);
		TrafficSign i406 = new TrafficSign("I.406", "Được ưu tiên qua đường hẹp",
				"Để chỉ dẫn cho người lái xe cơ giới biết mình được quyền ưu tiên đi trước trên đoạn đường hẹp. Nếu trên hướng đi ngược chiều có xe (cơ giới hoặc thô sơ) đã đi vào phạm vi đường hẹp thì xe đi theo chiều ưu tiên cũng phải nhường đường.",
				"i406.png", directionSignId);
		trafficSignRepo.save(i406);
		TrafficSign i407a = new TrafficSign("I.407a", "Đường một chiều",
				"Để chỉ dẫn những đoạn đường chạy một chiều. Biển số 407a đặt sau nơi đường giao nhau, biển số 407b,c đặt trước nơi đường giao nhau.",
				"i407a.png", directionSignId);
		trafficSignRepo.save(i407a);
		TrafficSign i407b = new TrafficSign("I.407b", "Đường một chiều",
				"Để chỉ dẫn những đoạn đường chạy một chiều. Biển số 407a đặt sau nơi đường giao nhau, biển số 407b,c đặt trước nơi đường giao nhau.",
				"i407b.png", directionSignId);
		trafficSignRepo.save(i407b);
		TrafficSign i407c = new TrafficSign("I.407c", "Đường một chiều",
				"Để chỉ dẫn những đoạn đường chạy một chiều. Biển số 407a đặt sau nơi đường giao nhau, biển số 407b,c đặt trước nơi đường giao nhau.",
				"i407c.png", directionSignId);
		trafficSignRepo.save(i407c);
		TrafficSign i408 = new TrafficSign("I.408", "Nơi đỗ xe",
				"Để chỉ dẫn những nơi được phép đỗ xe, những bãi đỗ xe, bến xe v.v...", "i408.png", directionSignId);
		trafficSignRepo.save(i408);
		TrafficSign i409 = new TrafficSign("I.409", "Chỗ quay xe",
				"Để chỉ dẫn vị trí được phép quay đầu xe. Biển không cho phép rẽ trái (trừ các xe được quyền ưu tiên theo quy định).",
				"i409.png", directionSignId);
		trafficSignRepo.save(i409);
		TrafficSign i410 = new TrafficSign("I.410", "Khu vực quay xe",
				"Để chỉ dẫn khu vực được phép quay đầu xe , phải đặt biển số 410 \"Khu vực quay xe\". Trên biển mô tả cách thức tiến hành quay xe. Biển không cho phép rẽ trái (trừ các xe được quyền ưu tiên theo quy định).",
				"i410.png", directionSignId);
		trafficSignRepo.save(i410);
		TrafficSign i411 = new TrafficSign("I.411", "Hướng đi trên mỗi làn đường theo vạch kẻ đường",
				"Để chỉ dẫn cho người lái xe biết số lượng làn đường trên mặt đường và hướng đi trên mỗi làn đường theo vạch kẻ đường. Biển sử dụng phối hợp với vạch kẻ đường (loại vạch 1.18 hình mũi tên màu trắng trên mặt đường). Tùy theo tình hình thực tế về số lượng làn đường và hướng đi trên mỗi làn đường mà có ký hiệu chỉ dẫn phù hợp. Biển có tác dụng bắt buộc người lái xe phải đi đúng làn đường đã được chỉ dẫn hướng phù hợp với hành trình cuả xe.",
				"i411.png", directionSignId);
		trafficSignRepo.save(i411);
		TrafficSign i412a = new TrafficSign("I.412a", "Làn đường dành cho ôtô khách",
				"Để chỉ dẫn cho người lái xe biết có làn đường dành riêng cho từng loại xe riêng biệt. Biển được đặt phía trên làn xe, ở đầu đường theo chiều xe chạy. Tùy loại phương tiện cần quy định mà bố trí biển cho phù hợp. Các loại xe khác không được đi vào làn đường có đặt biển này (trừ các xe được quyền ưu tiên theo quy định).",
				"i412a.png", directionSignId);
		trafficSignRepo.save(i412a);
		TrafficSign i412b = new TrafficSign("I.412b", "Làn đường dành cho ôtô con",
				"Để chỉ dẫn cho người lái xe biết có làn đường dành riêng cho từng loại xe riêng biệt. Biển được đặt phía trên làn xe, ở đầu đường theo chiều xe chạy. Tùy loại phương tiện cần quy định mà bố trí biển cho phù hợp. Các loại xe khác không được đi vào làn đường có đặt biển này (trừ các xe được quyền ưu tiên theo quy định).",
				"i412b.png", directionSignId);
		trafficSignRepo.save(i412b);
		TrafficSign i412c = new TrafficSign("I.412c", "Làn đường dành cho ôtô tải",
				"Để chỉ dẫn cho người lái xe biết có làn đường dành riêng cho từng loại xe riêng biệt. Biển được đặt phía trên làn xe, ở đầu đường theo chiều xe chạy. Tùy loại phương tiện cần quy định mà bố trí biển cho phù hợp. Các loại xe khác không được đi vào làn đường có đặt biển này (trừ các xe được quyền ưu tiên theo quy định).",
				"i412c.png", directionSignId);
		trafficSignRepo.save(i412c);
		TrafficSign i412d = new TrafficSign("I.412d", "Làn đường dành cho xe môtô",
				"Để chỉ dẫn cho người lái xe biết có làn đường dành riêng cho từng loại xe riêng biệt. Biển được đặt phía trên làn xe, ở đầu đường theo chiều xe chạy. Tùy loại phương tiện cần quy định mà bố trí biển cho phù hợp. Các loại xe khác không được đi vào làn đường có đặt biển này (trừ các xe được quyền ưu tiên theo quy định).",
				"i412d.png", directionSignId);
		trafficSignRepo.save(i412d);
		TrafficSign i413a = new TrafficSign("I.413a", "Đường có làn đường dành cho ô tô khách",
				"Để chỉ dẫn cho người lái xe biết đường có làn đường dành riêng cho ôtô khách theo chiều ngược lại.",
				"i413a.png", directionSignId);
		trafficSignRepo.save(i413a);
		TrafficSign i413b = new TrafficSign("I.413b", "Rẽ ra đường có làn đường dành cho ô tô khách",
				"Để chỉ dẫn cho người lái xe biết ở nơi đường giao nhau rẽ phải là rẽ ra đường có làn đường dành cho ôtô khách.",
				"i413b.png", directionSignId);
		trafficSignRepo.save(i413b);
		TrafficSign i413c = new TrafficSign("I.413c", "Rẽ ra đường có làn đường dành cho ô tô khách",
				"Để chỉ dẫn cho người lái xe biết ở nơi đường giao nhau rẽ trái là rẽ ra đường có làn đường dành cho ôtô khách.",
				"i413c.png", directionSignId);
		trafficSignRepo.save(i413c);
		TrafficSign i415 = new TrafficSign("I.415", "Mũi tên chỉ hướng đi",
				"Được đặt trong khu đông dân cư, ở các đường giao nhau để chỉ dẫn hướng đi đến một địa danh lân cận tiếp theo và khoảng cách (km) đến nơi đó. Nếu biển này đặt trên đường cao tốc thì phía bên trái biển có thêm hình vẽ đường cao tốc.",
				"i415.png", directionSignId);
		trafficSignRepo.save(i415);
		TrafficSign i416 = new TrafficSign("I.416", "Lối đi đường vòng tránh",
				"Đặt trước các đường giao nhau, để chỉ dẫn lối đi đường tránh, đường vòng trong trường hợp đường chính bị tắc, hoặc đường chính cấm một số loại xe đi qua.",
				"i416.png", directionSignId);
		trafficSignRepo.save(i416);
		TrafficSign i418 = new TrafficSign("I.418", "Lối đi ở những vị trí cấm rẽ",
				"Để chỉ lối đi ở các nơi đường giao nhau bị cấm rẽ. Biển được đặt ở nơi đường giao nhau trước đường cấm rẽ.",
				"i418.png", directionSignId);
		trafficSignRepo.save(i418);
		TrafficSign i419 = new TrafficSign("I.419", "Chỉ dẫn địa giới",
				"Để chỉ dẫn địa giới hành chính giữa các thành phố, tỉnh, huyện.", "i419.png", directionSignId);
		trafficSignRepo.save(i419);
		TrafficSign i423a = new TrafficSign("I.423a", "Đường người đi bộ sang ngang",
				"Để chỉ dẫn cho người đi bộ và người lái xe biết nơi dành cho người đi bộ sang ngang. Biển này được sử dụng độc lập ở những vị trí sang ngang, đường không có tổ chức điều khiển giao thông hoặc có thể sử dụng phối hợp với vạch kẻ đường. Gặp biển này người lái xe phải điều khiển xe chạy chậm, chú ý quan sát, ưu tiên cho người đi bộ sang ngang.",
				"i423a.png", directionSignId);
		trafficSignRepo.save(i423a);
		TrafficSign i437 = new TrafficSign("I.437", "Đường cao tốc", "Để chỉ dẫn bắt đầu đường cao tốc.", "i437.png",
				directionSignId);
		trafficSignRepo.save(i437);
		TrafficSign i438 = new TrafficSign("I.438", "Hết đường cao tốc", "Để chỉ hết đường cao tốc.", "i438.png",
				directionSignId);
		trafficSignRepo.save(i438);
		TrafficSign i443 = new TrafficSign("I.443", "Xe kéo moóc",
				"Để báo hiệu xe có kéo moóc hoặc xe kéo xe, biển này được đặt trên nóc xe kéo.", "i443.png",
				directionSignId);
		trafficSignRepo.save(i443);
		TrafficSign i446 = new TrafficSign("I.446", "Nơi đỗ xe dành cho người tàn tật",
				"Để báo hiệu nơi đỗ xe dành cho người tàn tật.", "i446.png", directionSignId);
		trafficSignRepo.save(i446);
		TrafficSign i447a = new TrafficSign("I.447a", "Biển báo cầu vượt liên thông",
				"Biển đặt tại vị trí trước khi vào cầu vượt có tổ chức giao thông liên thông giữa các tuyến. Tuỳ theo nút giao mà bố trí biển số 447a, 447b, 445c, 447d cho phù hợp.",
				"i447a.png", directionSignId);
		trafficSignRepo.save(i447a);

		// Biển báo phụ
		TrafficSign s501 = new TrafficSign("S.501", "Phạm vi tác dụng của biển",
				"Để thông báo chiều dài đoạn đường nguy hiểm hoặc cấm hoặc hạn chế bên dưới một số biển báo nguy hiểm, biển báo cấm hoặc hạn chế, chẳng hạn như: Nhiều chỗ ngoặt nguy hiểm liên tiếp; Dốc xuống nguy hiểm...",
				"s501.png", subSignId);
		trafficSignRepo.save(s501);
		TrafficSign s502 = new TrafficSign("S.502", "Khoảng cách đến đối tượng báo hiệu",
				"Bên dưới các loại biển báo nguy hiểm, biển báo cấm, biển hiệu lệnh và chỉ dẫn, thông báo khoảng cách thực tế từ vị trí đặt biển đến đối tượng báo hiệu ở phía trước.",
				"s502.png", subSignId);
		trafficSignRepo.save(s502);
		TrafficSign s503a = new TrafficSign("S.503a", "Hướng tác dụng của biển", "Hướng tác dụng của biển.",
				"s503a.png", subSignId);
		trafficSignRepo.save(s503a);
		TrafficSign s504 = new TrafficSign("S.504", "Làn đường",
				"Biển số 504 được đặt bên trên làn đường và dưới các biển báo cấm và biển hiệu lệnh hay bên dưới đèn tín hiệu để chỉ làn đường chịu hiệu lực của biển báo hay đèn tín hiệu.",
				"s504.png", subSignId);
		trafficSignRepo.save(s504);
		TrafficSign s505a = new TrafficSign("S.505a", "Loại xe",
				"Được đặt bên dưới các biển báo cấm và biển hiệu lệnh hay biển chỉ dẫn để chỉ loại xe chịu hiệu lực của biển báo cấm, biển hiệu lệnh hay biển chỉ dẫn. Tùy theo loại xe chịu hiệu lực mà bố trí hình vẽ cho phù hợp.",
				"s505a.png", subSignId);
		trafficSignRepo.save(s505a);
		TrafficSign s506a = new TrafficSign("S.506a", "Hướng đường ưu tiên",
				"Được đặt bên dưới biển chỉ dẫn số 401 trên đường ưu tiên để chỉ dẫn cho người lái xe trên đường này biết hướng đường ưu tiên ở ngã tư.",
				"s506a.png", subSignId);
		trafficSignRepo.save(s506a);

		// Vạch kẻ đường
		TrafficSign v1_1 = new TrafficSign("V.1_1", "Vạch số 1.1",
				"Vạch liền nét màu trắng, rộng 10cm, Phân chia 2 dòng phương tiện giao thông đi ngược chiều nhau, xe không được đè lên vạch.",
				"v1_1.png", roadMarkingId);
		trafficSignRepo.save(v1_1);
		TrafficSign v1_2 = new TrafficSign("V.1_2", "Vạch số 1.2",
				"Vạch liền nét màu trắng, rộng 20cm, Xác định mép phần xe cơ giới với phần xe thô sơ, người  đi bộ hoặc lề đường trên các trục đường, xe chạy được phép đè lên vạch khi cần thiết.",
				"v1_2.png", roadMarkingId);
		trafficSignRepo.save(v1_2);
		TrafficSign v1_3 = new TrafficSign("V.1_3", "Vạch số 1.3",
				"Hai vạch liên tục màu trắng (vạch kép) có chiều rộng bằng nhau và bằng 10cm cách nhau là 10cm tính từ 2 mép vạch kề nhau, phân  chia  2  dòng  phương  tiện  giao  thông  đi  ngược chiều nhau trên những đường có từ 4 làn xe trở lên, xe không được đè lên vạch.",
				"v1_3.png", roadMarkingId);
		trafficSignRepo.save(v1_3);
		TrafficSign v1_4 = new TrafficSign("V.1_4", "Vạch số 1.4",
				"Vạch liên tục màu vàng có chiều rộng 10cm, xác định nơi cấm dừng và cấm đỗ xe, áp dụng độc lập hoặc có thể kết hợp với biển báo cấm số 130 \"Cấm dừng xe và đỗ xe\" và kẻ ở mép đường hay ở trên hàng vỉa nơi có vỉa hè.",
				"v1_4.png", roadMarkingId);
		trafficSignRepo.save(v1_4);
		TrafficSign v1_5 = new TrafficSign("V.1_5", "Vạch số 1.5",
				"Vạch đứt quãng màu trắng, rộng 10cm, phân chia 2 dòng phương tiện giao thông đi ngược chiều nhau trên những đường có 2 hoặc 3 làn xe chạy hoặc xác định danh giới làn xe khi có từ 2 làn xe trở lên chạy theo một chiều.",
				"v1_5.png", roadMarkingId);
		trafficSignRepo.save(v1_5);

	}

	private void LoadQuestionSet() {
		questionSetRepo.deleteAll();
		for (int c = 1; c <= 18; c++) {
			QuestionSet set = new QuestionSet("Đề " + c, false, 25, 0, 0);
			questionSetRepo.save(set);
		}
	}

	private void LoadQuestionType() {
		questionTypeRepo.deleteAll();
		QuestionType conceptsAndRules = new QuestionType("KN", "Khái niệm và quy tắc giao thông",
				"Khái niệm và quy tắc giao thông");
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

	private void LoadQuestion() {
		License a1 = licenseRepo.findByName("A1");
		License a2 = licenseRepo.findByName("A2");
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
		QuestionSet set9 = questionSetRepo.findByName("Đề 9");
		QuestionSet set10 = questionSetRepo.findByName("Đề 10");
		QuestionSet set11 = questionSetRepo.findByName("Đề 11");
		QuestionSet set12 = questionSetRepo.findByName("Đề 12");
		QuestionSet set13 = questionSetRepo.findByName("Đề 13");
		QuestionSet set14 = questionSetRepo.findByName("Đề 14");
		QuestionSet set15 = questionSetRepo.findByName("Đề 15");
		QuestionSet set16 = questionSetRepo.findByName("Đề 16");
		QuestionSet set17 = questionSetRepo.findByName("Đề 17");
		QuestionSet set18 = questionSetRepo.findByName("Đề 18");

		questionRepo.deleteAll();

		// Câu hỏi cho bằng A1
		Question questionA1_1 = new Question(1,
				"Người điều khiển phương tiện giao thông đường bộ mà trong cơ thể có chất ma túy có bị nghiêm cấm hay không?",
				true, "", a1.getId(), set1.getId(), kn.getId());
		questionRepo.save(questionA1_1);

		Question questionA1_9 = new Question(2,
				"Người ngồi trên xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy khi tham gia giao thông có được sử dụng ô khi trười mưa hay không?",
				true, "", a1.getId(), set1.getId(), kn.getId());
		questionRepo.save(questionA1_9);

		Question questionA1_17 = new Question(3,
				"Đường mà trên đó phương tiện tham gia giao thông được các phương tiện giao thông đến từ hướng khác nhường đường khi qua nơi đường giao nhau, được cắm biển báo hiệu đường ưu tiên là loại đường gì?",
				true, "", a1.getId(), set1.getId(), kn.getId());
		questionRepo.save(questionA1_17);

		Question questionA1_25 = new Question(4,
				"Khái niệm \"người điều khiển giao thông\" được hiểu như thế nào là đúng?", true, "", a1.getId(),
				set1.getId(), kn.getId());
		questionRepo.save(questionA1_25);

		Question questionA1_33 = new Question(5,
				"Trong trường hợp đặc biệt, để được lắp đặt, sử dụng còi, đèn không đúng với thiết kế của nhà sản xuất đối với từng loại xe cơ giới bạn phải đảm bảo yêu cầu nào dưới đây?",
				true, "", a1.getId(), set1.getId(), kn.getId());
		questionRepo.save(questionA1_33);

		Question questionA1_41 = new Question(6,
				"Khi gặp hiệu lệnh như dưới đây của cảnh sát giao thông thì người tham gia giao thông phải đi như thế nào là đúng quy tắc giao thông?",
				true, "a1_41.jpg", a1.getId(), set1.getId(), kn.getId());
		questionRepo.save(questionA1_41);

		Question questionA1_49 = new Question(7,
				"Trên đường có nhiều làn đường cho xe đi cùng chiều được phân biệt bằng vạch kẻ phân làn đường, người điều khiển phương tiện phải cho xe đi như thế nào?",
				true, "", a1.getId(), set1.getId(), kn.getId());
		questionRepo.save(questionA1_49);

		Question questionA1_57 = new Question(8,
				"Tại nơi đường giao nhau không có báo hiệu đi theo vòng xuyến, người điều khiển phương tiện phải nhường đường như thế nào là đúng quy tắc giao thông?",
				true, "", a1.getId(), set1.getId(), kn.getId());
		questionRepo.save(questionA1_57);

		Question questionA1_65 = new Question(9, "Người lái xe mô tô xử lý như thế nào khi cho xe mô tô phía sau vượt?",
				true, "", a1.getId(), set1.getId(), kn.getId());
		questionRepo.save(questionA1_65);

		Question questionA1_73 = new Question(10,
				"Người lái xe phải giảm tốc độ thấp hơn tốc độ tối đa cho phép đến mức cần thiết, chú ý quan sát và chuẩn bị sẵn sàng những tình huống có thể xảy ra để phòng ngừa tai nạn trong các trường hợp nào dưới đây?",
				true, "", a1.getId(), set1.getId(), kn.getId());
		questionRepo.save(questionA1_73);

		Question questionA1_81 = new Question(11,
				"Tại nơi đường bộ giao nhau cùng mức với đường sắt chỉ có đèn tín hiệu hoặc chuông báo hiệu, khi đèn tín hiệu màu đỏ đã bất sáng hoặc có tiếng chuông báo hiệu, người tham gia giao thông phải dừng ngay và giữ khoảng cách tối thiểu bao nhiêu mét tính từ ray gần nhất?",
				true, "", a1.getId(), set1.getId(), kn.getId());
		questionRepo.save(questionA1_81);

		Question questionA1_89 = new Question(12,
				"Khi quay đầu xe, người lái xe cần phải quan sát và thực hiện các thao tác nào để đảm bảo an toàn giao thông?",
				true, "", a1.getId(), set1.getId(), kt.getId());
		questionRepo.save(questionA1_89);

		Question questionA1_97 = new Question(13,
				"Để đảm bảo an toàn khi tham gia giao thông, người lái xe lái xe mô tô hai bánh cần điều khiển tay ga như thế nào trong các trường hợp dưới đây?",
				true, "", a1.getId(), set1.getId(), kt.getId());
		questionRepo.save(questionA1_97);

		Question questionA1_105 = new Question(14, "Biển nào báo hiệu sắp đến chỗ giao nhau nguy hiểm?", true,
				"a1_105.jpg", a1.getId(), set1.getId(), bb.getId());
		questionRepo.save(questionA1_105);

		Question questionA1_113 = new Question(15, "Biển nào báo hiệu \"Đường đôi\"?", true, "a1_113.jpg", a1.getId(),
				set1.getId(), bb.getId());
		questionRepo.save(questionA1_113);

		Question questionA1_121 = new Question(16, "Biển báo này có ý nghĩa gì?", true, "a1_121.jpg", a1.getId(),
				set1.getId(), bb.getId());
		questionRepo.save(questionA1_121);

		Question questionA1_129 = new Question(17, "Biển nào cấm các phương tiện giao thông đường bộ rẽ trái?", true,
				"a1_129.jpg", a1.getId(), set1.getId(), bb.getId());
		questionRepo.save(questionA1_129);

		Question questionA1_137 = new Question(18, "Biển báo này có ý nghĩa như thế nào?", true, "a1_137.jpg",
				a1.getId(), set1.getId(), bb.getId());
		questionRepo.save(questionA1_137);

		Question questionA1_145 = new Question(19,
				"Trong các biển dưới đây biển nào là biển \"Hết tốc độ tối đa cho phép\"?", true, "a1_145.jpg",
				a1.getId(), set1.getId(), bb.getId());
		questionRepo.save(questionA1_145);

		Question questionA1_153 = new Question(20,
				"Biển báo nào báo hiệu bắt đầu đoạn đường vào phạm vi khu dân cư, các phương tiện tham gia giao thông phải tuân thủ các quy định đi đường được áp dụng ở khu đông dân cư?",
				true, "a1_153.jpg", a1.getId(), set1.getId(), bb.getId());
		questionRepo.save(questionA1_153);

		Question questionA1_161 = new Question(21,
				"Vạch kẻ đường nào dưới đây là vạch phân chia hai chiều xe chạy ( vạch tim đường), xe không được lấn làn, không được đè lên vạch?",
				true, "a1_161.jpg", a1.getId(), set1.getId(), bb.getId());
		questionRepo.save(questionA1_161);

		Question questionA1_169 = new Question(22, "Xe nào được quyền đi trước trong trường hợp này?", true,
				"a1_169.jpg", a1.getId(), set1.getId(), sh.getId());
		questionRepo.save(questionA1_169);

		Question questionA1_177 = new Question(23, "Các xe đi theo hướng mũi tên, xe nào vi phạm quy tắc giao thông?",
				true, "a1_177.jpg", a1.getId(), set1.getId(), sh.getId());
		questionRepo.save(questionA1_177);

		Question questionA1_185 = new Question(24, "Thứ tự các xe đi như thế nào là đúng quy tắc giao thông?", true,
				"a1_185.jpg", a1.getId(), set1.getId(), sh.getId());
		questionRepo.save(questionA1_185);

		Question questionA1_193 = new Question(25, "Trong hình dưới, những xe nào vi phạm quy tắc giao thông?", true,
				"a1_193.jpg", a1.getId(), set1.getId(), sh.getId());
		questionRepo.save(questionA1_193);

		Question questionA1_2 = new Question(1,
				"Hành vi điều khiển xe cơ giới chạy quá tốc độ quy định, giành đường, vượt ẩu có bị nghiêm cấm hay không?",
				true, "", a1.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA1_2);

		Question questionA1_10 = new Question(2,
				"Người điều khiển xe mô tô hai bánh, xe gắn máy có được đi xe dàn hàng ngang; đi xe vào phần đường dành cho người đi bộ và phương tiện khác; sử dụng ô, điện thoại di động, thiết bị âm thanh (trừ thiết bị trợ thính) hay không?",
				true, "", a1.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA1_10);

		Question questionA1_18 = new Question(3, "Người lái xe được hiểu như thế nào trong các khái niệm dưới đây?",
				true, "", a1.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA1_18);

		Question questionA1_26 = new Question(4, "\"Người tham gia giao thông đường bộ\" gồm những đối tượng nào?",
				true, "", a1.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA1_26);

		Question questionA1_34 = new Question(5,
				"Bạn đang lái xe phía trước có một xe cảnh sát giao thông không phát tín hiệu ưu tiên bạn có được phép vượt hay không?",
				true, "", a1.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA1_34);

		Question questionA1_42 = new Question(6,
				"Theo luật giao thông đường bộ, tín hiệu đèn giao thông gồm 3 màu nào dưới đây?", true, "a1_42.jpg",
				a1.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA1_42);

		Question questionA1_50 = new Question(7,
				"Trên đường một chiều có vạch kẻ phân làn đường xe thô sơ và xe cơ giới phải đi như thế nào là đúng quy tắc giao thông?",
				true, "", a1.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA1_50);

		Question questionA1_58 = new Question(8,
				"Người ngồi trên xe mô tô 2 bánh, xe gắn máy phải đội mũ bảo hiểm có cài quai đúng quy cách khi nào?",
				true, "", a1.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA1_58);

		Question questionA1_66 = new Question(9,
				"Trong các trường hợp dưới đây, để đảo bảo an toàn khi tham gia giao thông, người lái xe mô tô cần thực hiện như thế nào?",
				true, "", a1.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA1_66);

		Question questionA1_74 = new Question(10,
				"Tác dụng của mũ bảo hiểm đối với người ngồi trên xe mô tô hai bánh trong trường hợp xảy ra tai nạn giao thông là gì?",
				true, "", a1.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA1_74);

		Question questionA1_82 = new Question(11,
				"Người điều khiển phương tiện tham giao thông trong hầm đường bộ ngoài việc phải tuân thủ các quy tắc giao thông còn phải thực hiện những quy định nào dưới đây?",
				true, "", a1.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA1_82);

		Question questionA1_90 = new Question(12,
				"Khi điều khiển xe trên đường vòng người lái xe cần phải làm gì để đảm bảo an toàn?", true, "",
				a1.getId(), set2.getId(), kt.getId());
		questionRepo.save(questionA1_90);

		Question questionA1_98 = new Question(13,
				"Kỹ thuật cơ bản để giữ thăng bằng khi điều khiển xe mô tô đi trên đường gồ ghề như thế nào trong các trường hợp dưới đây?",
				true, "", a1.getId(), set2.getId(), kt.getId());
		questionRepo.save(questionA1_98);

		Question questionA1_106 = new Question(14, "Biển nào báo hiệu \"Giao nhau có tín hiệu đèn\"?", true,
				"a1_106.jpg", a1.getId(), set2.getId(), bb.getId());
		questionRepo.save(questionA1_106);

		Question questionA1_114 = new Question(15, "Biển nào báo hiệu \"Đường đôi\"?", true, "a1_114.jpg", a1.getId(),
				set2.getId(), bb.getId());
		questionRepo.save(questionA1_114);

		Question questionA1_122 = new Question(16, "Biển nào dưới đây xe gắn máy được phép đi vào?", true, "a1_122.jpg",
				a1.getId(), set2.getId(), bb.getId());
		questionRepo.save(questionA1_122);

		Question questionA1_130 = new Question(17, "Biển nào xe được phép quay đầu nhưng không được rẽ trái?", true,
				"a1_130.jpg", a1.getId(), set2.getId(), bb.getId());
		questionRepo.save(questionA1_130);

		Question questionA1_138 = new Question(18,
				"Chiều dài đoạn đường 500 m từ nơi đặt biển này, người lái xe có được phép bấm còi không?", true,
				"a1_138.jpg", a1.getId(), set2.getId(), bb.getId());
		questionRepo.save(questionA1_138);

		Question questionA1_146 = new Question(19,
				"Hiệu lực của biển \"Tốc độ tối đa cho phép\" hết tác dụng khi gặp biển nào dưới đây?", true,
				"a1_146.jpg", a1.getId(), set2.getId(), bb.getId());
		questionRepo.save(questionA1_146);

		Question questionA1_154 = new Question(20, "Biển nào báo hiệu\"Hướng đi thẳng phải theo\"?", true, "a1_154.jpg",
				a1.getId(), set2.getId(), bb.getId());
		questionRepo.save(questionA1_154);

		Question questionA1_162 = new Question(21,
				"Vạch kẻ đường nào dưới đây là vạch phân chia hai chiều xe chạy ( vạch tim đường)?", true, "a1_162.jpg",
				a1.getId(), set2.getId(), bb.getId());
		questionRepo.save(questionA1_162);

		Question questionA1_170 = new Question(22, "Theo tín hiệu đèn, xe nào được phép đi?", true, "a1_170.jpg",
				a1.getId(), set2.getId(), sh.getId());
		questionRepo.save(questionA1_170);

		Question questionA1_178 = new Question(23, "Thứ tự các xe đi như thế nào là đúng quy tắc giao thông?", true,
				"a1_178.jpg", a1.getId(), set2.getId(), sh.getId());
		questionRepo.save(questionA1_178);

		Question questionA1_186 = new Question(24, "Thứ tự các xe đi như thế nào là đúng quy tắc giao thông?", true,
				"a1_186.jpg", a1.getId(), set2.getId(), sh.getId());
		questionRepo.save(questionA1_186);

		Question questionA1_194 = new Question(25, "Trong hình dưới những xe nào vi phạm quy tắc giao thông?", true,
				"a1_194.jpg", a1.getId(), set2.getId(), sh.getId());
		questionRepo.save(questionA1_194);

		Question questionA1_3 = new Question(1,
				"Bạn đang lái xe phía trước có một xe cứu thương đang phát tín hiệu ưu tiên bạn có được phép vượt hay không?",
				true, "", a1.getId(), set3.getId(), kn.getId());
		questionRepo.save(questionA1_3);

		Question questionA1_11 = new Question(2,
				"Hành vi vận chuyển đồ vật cồng kềnh bằng xe mô tô, xe gắn máy khi tham gia giao thông có được phép hay không?",
				true, "", a1.getId(), set3.getId(), kn.getId());
		questionRepo.save(questionA1_11);

		Question questionA1_19 = new Question(3,
				"Trong các khái niệm dưới đây, \"dải phân cách\" được hiểu như thế nào là đúng?", true, "", a1.getId(),
				set3.getId(), kn.getId());
		questionRepo.save(questionA1_19);

		Question questionA1_27 = new Question(4, "Khái niệm \"đỗ xe\" được hiểu như thế nào là đúng?", true, "",
				a1.getId(), set3.getId(), kn.getId());
		questionRepo.save(questionA1_27);

		Question questionA1_35 = new Question(5,
				"Khi điều khiển xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy, những hành vi nào không được phép?",
				true, "", a1.getId(), set3.getId(), kn.getId());
		questionRepo.save(questionA1_35);

		Question questionA1_43 = new Question(6,
				"Biển báo hiệu có dạng hình tròn, viền đỏ, nền trắng, trên nền có hình vẽ hoặc chữ số, chữ viết màu đen là loại biển gì dưới đây?",
				true, "", a1.getId(), set3.getId(), kn.getId());
		questionRepo.save(questionA1_43);

		Question questionA1_51 = new Question(7,
				"Bạn đang lái xe trong khu vực đô thị từ 22 giờ đến 5 giờ sáng hôm sau và cần vượt một xe khác, bạn cần báo hiệu như thế nào để đảm bảo an toàn giao thông?",
				true, "", a1.getId(), set3.getId(), kn.getId());
		questionRepo.save(questionA1_51);

		Question questionA1_59 = new Question(8,
				"Người điều khiển xe mô tô hai bánh, xe gắn máy được phép chở tối đa 2 người trong những trường hợp nào?",
				true, "", a1.getId(), set3.getId(), kn.getId());
		questionRepo.save(questionA1_59);

		Question questionA1_67 = new Question(9,
				"Đường bộ trong khu vực đông dân cư gồm những đoạn đường nào dưới đây?", true, "", a1.getId(),
				set3.getId(), kn.getId());
		questionRepo.save(questionA1_67);

		Question questionA1_75 = new Question(10,
				"Tại nơi đường giao nhau, người lái xe đang đi trên đường không ưu tiên phải xử lý như thế nào là đúng quy tắc giao thông?",
				true, "", a1.getId(), set3.getId(), kn.getId());
		questionRepo.save(questionA1_75);

		Question questionA1_83 = new Question(11, "Khái niệm về văn hóa giao thông được hiểu như thế nào là đúng?",
				true, "", a1.getId(), set3.getId(), vh.getId());
		questionRepo.save(questionA1_83);

		Question questionA1_91 = new Question(12,
				"Để đạt được hiệu quả phanh cao nhất, người lái xe mô tô phải sử dụng các kỹ năng như thế nào dưới đây?",
				true, "", a1.getId(), set3.getId(), kt.getId());
		questionRepo.save(questionA1_91);

		Question questionA1_99 = new Question(13,
				"Khi điều khiển xe mô tô tay ga xuống đường dốc dài, độ dốc cao, người lái xe cần thực hiện các thao tác nào dưới đây để đảm bảo an toàn?",
				true, "", a1.getId(), set3.getId(), kt.getId());
		questionRepo.save(questionA1_99);

		Question questionA1_107 = new Question(14, "Biển nào báo hiệu \"Giao nhau với đường sắt có rào chắn\"?", true,
				"a1_107.jpg", a1.getId(), set3.getId(), bb.getId());
		questionRepo.save(questionA1_107);

		Question questionA1_115 = new Question(15, "Biển nào báo hiệu \"Giao nhau với đường hai chiều\"?", true,
				"a1_115.jpg", a1.getId(), set3.getId(), bb.getId());
		questionRepo.save(questionA1_115);

		Question questionA1_123 = new Question(16, "Biển nào báo hiệu cấm xe mô tô hai bánh đi vào?", true,
				"a1_123.jpg", a1.getId(), set3.getId(), bb.getId());
		questionRepo.save(questionA1_123);

		Question questionA1_131 = new Question(17, "Biển nào là biển \"Cấm đi ngược chiều\"?", true, "a1_131.jpg",
				a1.getId(), set3.getId(), bb.getId());
		questionRepo.save(questionA1_131);

		Question questionA1_139 = new Question(18, "Biển nào xe mô tô hai bánh không được đi vào?", true, "a1_139.jpg",
				a1.getId(), set3.getId(), bb.getId());
		questionRepo.save(questionA1_139);

		Question questionA1_147 = new Question(19,
				"Trong các biển dưới đây biển nào là biển \" Hết tốc độ tối thiểu\"?", true, "a1_147.jpg", a1.getId(),
				set3.getId(), bb.getId());
		questionRepo.save(questionA1_147);

		Question questionA1_155 = new Question(20, "Biển nào báo hiệu \"Đường một chiều\"?", true, "a1_155.jpg",
				a1.getId(), set3.getId(), bb.getId());
		questionRepo.save(questionA1_155);

		Question questionA1_163 = new Question(21, "Các vạch dưới đây có tác dụng gì?", true, "a1_163.jpg", a1.getId(),
				set3.getId(), bb.getId());
		questionRepo.save(questionA1_163);

		Question questionA1_171 = new Question(22, "Xe nào được quyền đi trước trong trường hợp này?", true,
				"a1_171.jpg", a1.getId(), set3.getId(), sh.getId());
		questionRepo.save(questionA1_171);

		Question questionA1_179 = new Question(23, "Xe nào được quyền đi trước trong trường hợp này?", true,
				"a1_179.jpg", a1.getId(), set3.getId(), sh.getId());
		questionRepo.save(questionA1_179);

		Question questionA1_187 = new Question(24, "Theo hướng mũi tên, những hướng nào xe gắn máy đi được?", true,
				"a1_187.jpg", a1.getId(), set3.getId(), sh.getId());
		questionRepo.save(questionA1_187);

		Question questionA1_195 = new Question(25, "Xe nào vi phạm quy tắc giao thông?", true, "a1_195.jpg", a1.getId(),
				set3.getId(), sh.getId());
		questionRepo.save(questionA1_195);

		Question questionA1_4 = new Question(1,
				"Ở phần đường dành cho người đi bộ qua đường, trên cầu, đầu cầu, đường cao tốc, đường hẹp, đường dốc, tại nơi đường bộ giao nhau cùng mức với đường sắt có được quay đầu xe hay không?",
				true, "", a1.getId(), set4.getId(), kn.getId());
		questionRepo.save(questionA1_4);

		Question questionA1_12 = new Question(2,
				"Hành vi sử dụng xe mô tô để kéo, đẩy xe mô tô khác bị hết xăng đến trạm mua xăng có được phép hay không?",
				true, "", a1.getId(), set4.getId(), kn.getId());
		questionRepo.save(questionA1_12);

		Question questionA1_20 = new Question(3, "\"Dải phân cách\" trên đường bộ gồm những loại nào?", true, "",
				a1.getId(), set4.getId(), kn.getId());
		questionRepo.save(questionA1_20);

		Question questionA1_28 = new Question(4,
				"Trong các khái niệm dưới đây, khái niệm \"dừng xe\" được hiểu như thế nào là đúng?", true, "",
				a1.getId(), set4.getId(), kn.getId());
		questionRepo.save(questionA1_28);

		Question questionA1_36 = new Question(5, "Người đủ 16 tuổi được điều khiển các loại xe nào dưới đây?", true, "",
				a1.getId(), set4.getId(), kn.getId());
		questionRepo.save(questionA1_36);

		Question questionA1_44 = new Question(6,
				"Biển báo hiệu có dạng hình tam giác đều, viền đỏ, nền màu vàng, trên có hình vẽ màu đen là loại biển gì dưới đây?",
				true, "a1_44.jpg", a1.getId(), set4.getId(), kn.getId());
		questionRepo.save(questionA1_44);

		Question questionA1_52 = new Question(7,
				"Khi điều khiển xe chạy trên đường biết có xe sau xin vượt nếu đủ điều kiện an toàn người lái xe phải làm gì?",
				true, "", a1.getId(), set4.getId(), kn.getId());
		questionRepo.save(questionA1_52);

		Question questionA1_60 = new Question(8,
				"Người điều khiển xe mô tô hai bánh, xe gắn máy không được thực hiện những hành vi nào dưới dây?", true,
				"", a1.getId(), set4.getId(), kn.getId());
		questionRepo.save(questionA1_60);

		Question questionA1_68 = new Question(9,
				"Tốc độ tối đa cho phép đối với xe máy chuyên dùng, xe gắn máy (kể cả xe máy điện) và các loại xe tương tự trên đường bộ (trừ đường cao tốc) không được vượt quá bao nhiêu km",
				true, "", a1.getId(), set4.getId(), kn.getId());
		questionRepo.save(questionA1_68);

		Question questionA1_76 = new Question(10,
				"Người lái xe phải xử lý như thế nào khi quan sát phía trước thấy người đi bộ đang sang đường tại nơi có vạch đường dành cho người đi bộ để đảm bảo an toàn?",
				true, "", a1.getId(), set4.getId(), kn.getId());
		questionRepo.save(questionA1_76);

		Question questionA1_84 = new Question(11,
				"Trong các hành vi dưới đây, người lái xe ô tô, mô tô có văn hóa giao thông phải ứng xử như thế nào?",
				true, "", a1.getId(), set4.getId(), vh.getId());
		questionRepo.save(questionA1_84);

		Question questionA1_92 = new Question(12,
				"Khi đang lái xe mô tô và ô tô, nếu có nhu cầu sử dụng điện thoại để nhắn tin hoặc gọi điện, người lái xe phải thực hiện như thế nào trong các tình huống dưới đây?",
				true, "", a1.getId(), set4.getId(), kt.getId());
		questionRepo.save(questionA1_92);

		Question questionA1_100 = new Question(13,
				"Các phương tiện tham gia giao thông đường bộ (kể cả những xe có quyền ưu tiên) đều phải dừng lại bên phải đường của mình và trước vạch \"dừng xe\" tại các điểm giao cắt giữa đường bộ và đường sắt khi có báo hiệu dừng nào dưới đây?",
				true, "", a1.getId(), set4.getId(), kt.getId());
		questionRepo.save(questionA1_100);

		Question questionA1_108 = new Question(14, "Biển nào báo hiệu nguy hiểm giao nhau với đường sắt?", true,
				"a1_108.jpg", a1.getId(), set4.getId(), bb.getId());
		questionRepo.save(questionA1_108);

		Question questionA1_116 = new Question(15, "Biển nào báo hiệu \"Đường hai chiều\"?", true, "a1_116.jpg",
				a1.getId(), set4.getId(), bb.getId());
		questionRepo.save(questionA1_116);

		Question questionA1_124 = new Question(16, "khi gặp biển nào thì xe mô tô hai bánh được đi vào?", true,
				"a1_124.jpg", a1.getId(), set4.getId(), bb.getId());
		questionRepo.save(questionA1_124);

		Question questionA1_132 = new Question(17, "Biển nào dưới đây các phương tiện không được phép đi vào?", true,
				"a1_132.jpg", a1.getId(), set4.getId(), bb.getId());
		questionRepo.save(questionA1_132);

		Question questionA1_140 = new Question(18, "Biển nào xe mô tô hai bánh được đi vào?", true, "a1_140.jpg",
				a1.getId(), set4.getId(), bb.getId());
		questionRepo.save(questionA1_140);

		Question questionA1_148 = new Question(19, "Biển nào dưới đây báo hiệu hết cấm vượt?", true, "a1_148.jpg",
				a1.getId(), set4.getId(), bb.getId());
		questionRepo.save(questionA1_148);

		Question questionA1_156 = new Question(20, "Biển nào chỉ dẫn cho người đi bộ sử dụng cầu vượt qua đường?", true,
				"a1_156.jpg", a1.getId(), set4.getId(), bb.getId());
		questionRepo.save(questionA1_156);

		Question questionA1_164 = new Question(21, "Khi gặp vạch kẻ đường nào các xe được phép đè vạch?", true,
				"a1_164.jpg", a1.getId(), set4.getId(), bb.getId());
		questionRepo.save(questionA1_164);

		Question questionA1_172 = new Question(22,
				"Các xe đi theo hướng mũi tên, xe nào chấp hành đúng quy tắc giao thông?", true, "a1_172.jpg",
				a1.getId(), set4.getId(), sh.getId());
		questionRepo.save(questionA1_172);

		Question questionA1_180 = new Question(23, "Các xe đi theo thứ tự nào là đúng quy tắc giao thông đường bộ?",
				true, "a1_180.jpg", a1.getId(), set4.getId(), sh.getId());
		questionRepo.save(questionA1_180);

		Question questionA1_188 = new Question(24, "Theo hướng mũi tên, những hướng nào xe mô tô được phép đi?", true,
				"a1_188.jpg", a1.getId(), set4.getId(), sh.getId());
		questionRepo.save(questionA1_188);

		Question questionA1_196 = new Question(25, "Các xe đi theo thứ nào là đúng quy tắc giao thông đường bộ?", true,
				"a1_196.jpg", a1.getId(), set4.getId(), sh.getId());
		questionRepo.save(questionA1_196);

		Question questionA1_5 = new Question(1,
				"Người điều khiển xe mô tô hai bánh, ba bánh, xe gắn máy có được phép sử dụng xe để kéo hoặc đẩy các phương tiện khác khi tham gia giao thông không?",
				true, "", a1.getId(), set5.getId(), kn.getId());
		questionRepo.save(questionA1_5);

		Question questionA1_13 = new Question(2,
				"Khi đang lên dốc người ngồi trên xe mô tô có được kéo theo người đang điều khiển xe đạp hay không?",
				true, "", a1.getId(), set5.getId(), kn.getId());
		questionRepo.save(questionA1_13);

		Question questionA1_21 = new Question(3,
				"Khái niệm \"phương tiện giao thông cơ giới đường bộ\" được hiểu thế nào là đúng?", true, "",
				a1.getId(), set5.getId(), kn.getId());
		questionRepo.save(questionA1_21);

		Question questionA1_29 = new Question(4, "Cuộc đua xe chỉ được thực hiện khi nào?", true, "", a1.getId(),
				set5.getId(), kn.getId());
		questionRepo.save(questionA1_29);

		Question questionA1_37 = new Question(5,
				"Người đủ bao nhiêu tuổi trở lên thì được điều khiển xe mô tô hai bánh, xe mô tô ba bánh có dung tích xi lanh từ 50 cm3 trở lên và các loại xe có kết cấu tương tự; xe ô tô tải, máy kéo có trọng tải dưới 3500kg; xe ô tô chở người đến 9 chỗ ngồi?",
				true, "", a1.getId(), set5.getId(), kn.getId());
		questionRepo.save(questionA1_37);

		Question questionA1_45 = new Question(6,
				"Biển báo hiệu hình tròn có nền xanh lam có hình vẽ màu trắng là loại gì dưới đây?", true, "a1_45.jpg",
				a1.getId(), set5.getId(), kn.getId());
		questionRepo.save(questionA1_45);

		Question questionA1_53 = new Question(7,
				"Khi muốn chuyển hướng, người lái xe phải thực hiện như thế nào để đảm bảo an toàn giao thông?", true,
				"", a1.getId(), set5.getId(), kn.getId());
		questionRepo.save(questionA1_53);

		Question questionA1_61 = new Question(8,
				"Người lái xe phải giảm tốc độ thấp hơn tốc độ tối đa cho phép (có thể dừng lại một cách an toàn) trong trường hợp nào dưới đây?",
				true, "", a1.getId(), set5.getId(), kn.getId());
		questionRepo.save(questionA1_61);

		Question questionA1_69 = new Question(9,
				"Trên đường bộ (trừ đường cao tốc) trong khu vực đông dân cư, đường đôi có dải phân cách giữa, xe mô tô hai bánh, ô tô chở người đến 30 chỗ tham gia giao thông với tốc độ tối đa cho phép là bao nhiêu?",
				true, "", a1.getId(), set5.getId(), kn.getId());
		questionRepo.save(questionA1_69);

		Question questionA1_77 = new Question(10,
				"Tại nơi đường giao nhau, khi đèn điều khiển giao thông có tín hiệu màu vàng, người điều khiển giao thông phải chấp hành như thế nào là đúng quy tắc giao thông?",
				true, "", a1.getId(), set5.getId(), kn.getId());
		questionRepo.save(questionA1_77);

		Question questionA1_85 = new Question(11,
				"Trong các hành vi dưới đây, người lái xe mô tô có văn hóa giao thông phải ứng xử như thế nào?", true,
				"", a1.getId(), set5.getId(), vh.getId());
		questionRepo.save(questionA1_85);

		Question questionA1_93 = new Question(12,
				"Gương chiếu hậu của xe mô tô hai bánh, có tác dụng gì trong các trường hợp dưới đây?", true, "",
				a1.getId(), set5.getId(), kt.getId());
		questionRepo.save(questionA1_93);

		Question questionA1_101 = new Question(13,
				"Biển nào báo hiệu \"Đường giao nhau\" của các tuyến đường cùng cấp?", true, "a1_101.jpg", a1.getId(),
				set5.getId(), bb.getId());
		questionRepo.save(questionA1_101);

		Question questionA1_109 = new Question(14,
				"Biển nào báo hiệu đường sắt giao nhau với đường bộ không có rào chắn?", true, "a1_109.jpg", a1.getId(),
				set5.getId(), bb.getId());
		questionRepo.save(questionA1_109);

		Question questionA1_117 = new Question(15, "Biển nào báo hiệu \"Giao nhau với đường hai chiều\"?", true,
				"a1_117.jpg", a1.getId(), set5.getId(), bb.getId());
		questionRepo.save(questionA1_117);

		Question questionA1_125 = new Question(16, "Biển nào cấm quay đầu xe?", true, "a1_125.jpg", a1.getId(),
				set5.getId(), bb.getId());
		questionRepo.save(questionA1_125);

		Question questionA1_133 = new Question(17, "Khi gặp biển nào xe ưu tiên theo luật định vẫn phải dừng lại?",
				true, "a1_133.jpg", a1.getId(), set5.getId(), bb.getId());
		questionRepo.save(questionA1_133);

		Question questionA1_141 = new Question(18, "Gặp biển nào người lái xe phải nhường đường cho người đi bộ?", true,
				"a1_141.jpg", a1.getId(), set5.getId(), bb.getId());
		questionRepo.save(questionA1_141);

		Question questionA1_149 = new Question(19, "Trong các biển dưới đây biển nào là biển \"Hết mọi lệnh cấm\"?",
				true, "a1_149.jpg", a1.getId(), set5.getId(), bb.getId());
		questionRepo.save(questionA1_149);

		Question questionA1_157 = new Question(20, "Biển nào chỉ dẫn cho người đi bộ sử dụng hầm chui qua đường?", true,
				"a1_157.jpg", a1.getId(), set5.getId(), bb.getId());
		questionRepo.save(questionA1_157);

		Question questionA1_165 = new Question(21, "Vạch dưới đây có ý nghĩa gì?", true, "a1_165.jpg", a1.getId(),
				set5.getId(), bb.getId());
		questionRepo.save(questionA1_165);

		Question questionA1_173 = new Question(22, "Trong hình dưới đây, xe nào chấp hành đúng quy tắc giao thông?",
				true, "a1_173.jpg", a1.getId(), set5.getId(), sh.getId());
		questionRepo.save(questionA1_173);

		Question questionA1_181 = new Question(23, "Trong trường hợp này xe nào đỗ vi phạm quy tắc giao thông?", true,
				"a1_181.jpg", a1.getId(), set5.getId(), sh.getId());
		questionRepo.save(questionA1_181);

		Question questionA1_189 = new Question(24, "Trường hợp này xe nào được quyền đi trước?", true, "a1_189.jpg",
				a1.getId(), set5.getId(), sh.getId());
		questionRepo.save(questionA1_189);

		Question questionA1_197 = new Question(25, "Bạn xử lý như thế nào trong trường hợp này?", true, "a1_197.jpg",
				a1.getId(), set5.getId(), sh.getId());
		questionRepo.save(questionA1_197);

		Question questionA1_6 = new Question(1,
				"Khi điều khiển xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy, những hành vi buông cả hai tay; sử dụng xe để kéo, đẩy xe khác, vật khác; sử dụng chân chống của xe quệt xuống đường khi xe đang chạy có được phép không?",
				true, "", a1.getId(), set6.getId(), kn.getId());
		questionRepo.save(questionA1_6);

		Question questionA1_14 = new Question(2,
				"Theo luật phòng chống tác hại của rượu, bia, đối tượng nào dưới đây bị cấm sử dụng rượu bia khi tham gia giao thông?",
				true, "", a1.getId(), set6.getId(), kn.getId());
		questionRepo.save(questionA1_14);

		Question questionA1_22 = new Question(3,
				"Khái niệm \"phương tiện giao thông thô sơ đường bộ\" được hiểu thế nào là đúng?", true, "", a1.getId(),
				set6.getId(), kn.getId());
		questionRepo.save(questionA1_22);

		Question questionA1_30 = new Question(4,
				"Sử dụng rượu, bia khi lái xe, nếu bị phát hiện thì bị xử lý như thế nào?", true, "", a1.getId(),
				set6.getId(), kn.getId());
		questionRepo.save(questionA1_30);

		Question questionA1_38 = new Question(5,
				"Người có giấy phép lái xe mô tô hạng A1 không được phép điều khiển loại xe nào dưới đây?", true, "",
				a1.getId(), set6.getId(), kn.getId());
		questionRepo.save(questionA1_38);

		Question questionA1_46 = new Question(6,
				"Biển báo hiệu hình chữ nhật hoặc hình vuông hoặc hình mũi tên nền xanh lam là loại biển gì dưới đây?",
				true, "a1_46.jpg", a1.getId(), set6.getId(), kn.getId());
		questionRepo.save(questionA1_46);

		Question questionA1_54 = new Question(7,
				"Khi tránh xe đi ngược chiều, các xe phải nhường đường như thế nào là đúng quy tắc giao thông?", true,
				"", a1.getId(), set6.getId(), kn.getId());
		questionRepo.save(questionA1_54);

		Question questionA1_62 = new Question(8,
				"Khi điều khiển xe cơ giới, người lái xe phải bật đèn tín hiệu báo rẽ trong các trường hợp nào sau đây?",
				true, "", a1.getId(), set6.getId(), kn.getId());
		questionRepo.save(questionA1_62);

		Question questionA1_70 = new Question(9,
				"Trên đường bộ (trừ đường cao tốc) trong khu vực đông dân cư, đường hai chiều không có dải phân cách giữa, xe mô tô hai bánh, ô tô chở người đến 30 chỗ tham gia giao thông với tốc độ tối đa cho phép là bao nhiêu?",
				true, "", a1.getId(), set6.getId(), kn.getId());
		questionRepo.save(questionA1_70);

		Question questionA1_78 = new Question(10,
				"Để báo hiệu cho xe phía trước biết xe mô tô của bạn muốn vượt, bạn phải có tín hiệu như thế nào dưới đây?",
				true, "", a1.getId(), set6.getId(), kn.getId());
		questionRepo.save(questionA1_78);

		Question questionA1_86 = new Question(11,
				"Trên đường đang xảy ra ùn tắc những hành vi nào sau đây là thiếu văn hóa khi tham gia giao thông?",
				true, "", a1.getId(), set6.getId(), vh.getId());
		questionRepo.save(questionA1_86);

		Question questionA1_94 = new Question(12,
				"Khi điều khiển xe mô tô quay đầu người lái xe cần thực hiện như thế nào để đảm bảo an toàn?", true, "",
				a1.getId(), set6.getId(), kt.getId());
		questionRepo.save(questionA1_94);

		Question questionA1_102 = new Question(13, "Biển nào báo hiệu \"Giao nhau với đường không ưu tiên\"?", true,
				"a1_102.jpg", a1.getId(), set6.getId(), bb.getId());
		questionRepo.save(questionA1_102);

		Question questionA1_110 = new Question(14,
				"Biển nào báo hiệu sắp đến chỗ giao nhau giữa đường bộ và đường sắt?", true, "a1_110.jpg", a1.getId(),
				set6.getId(), bb.getId());
		questionRepo.save(questionA1_110);

		Question questionA1_118 = new Question(15, "Biển nào báo hiệu \"Chú ý chướng ngại vật\"?", true, "a1_118.jpg",
				a1.getId(), set6.getId(), bb.getId());
		questionRepo.save(questionA1_118);

		Question questionA1_126 = new Question(16, "Biển nào cấm xe rẽ trái?", true, "a1_126.jpg", a1.getId(),
				set6.getId(), bb.getId());
		questionRepo.save(questionA1_126);

		Question questionA1_134 = new Question(17,
				"Biển nào cấm tất cả các loại xe cơ giới và thô sơ đi lại trên đường, trừ xe ưu tiên theo luật định (nếu đường vẫn cho xe chạy được)?",
				true, "a1_134.jpg", a1.getId(), set6.getId(), bb.getId());
		questionRepo.save(questionA1_134);

		Question questionA1_142 = new Question(18,
				"Biển nào chỉ đường dành cho người đi bộ, các loại xe không được đi vào khi gặp biển này?", true,
				"a1_142.jpg", a1.getId(), set6.getId(), bb.getId());
		questionRepo.save(questionA1_142);

		Question questionA1_150 = new Question(19, "Biển nào báo hiệu \"Nơi đỗ xe cho người tàn tật\"?", true,
				"a1_150.jpg", a1.getId(), set6.getId(), bb.getId());
		questionRepo.save(questionA1_150);

		Question questionA1_158 = new Question(20,
				"Gặp biển báo này, người tham gia giao thông phải xử lý như thế nào?", true, "a1_158.jpg", a1.getId(),
				set6.getId(), bb.getId());
		questionRepo.save(questionA1_158);

		Question questionA1_166 = new Question(21, "Các xe đi như thế nào là đúng quy tắc giao thông?", true,
				"a1_166.jpg", a1.getId(), set6.getId(), sh.getId());
		questionRepo.save(questionA1_166);

		Question questionA1_174 = new Question(22, "Các xe đi theo hướng mũi tên, xe nào vi phạm quy tắc giao thông?",
				true, "a1_174.jpg", a1.getId(), set6.getId(), sh.getId());
		questionRepo.save(questionA1_174);

		Question questionA1_182 = new Question(23, "Xe nào đỗ vi phạm quy tắc giao thông?", true, "a1_182.jpg",
				a1.getId(), set6.getId(), sh.getId());
		questionRepo.save(questionA1_182);

		Question questionA1_190 = new Question(24, "Bạn có được phép vượt xe mô tô phía trước không?", true,
				"a1_190.jpg", a1.getId(), set6.getId(), sh.getId());
		questionRepo.save(questionA1_190);

		Question questionA1_198 = new Question(25, "Xe nào dừng đúng theo quy tắc giao thông?", true, "a1_198.jpg",
				a1.getId(), set6.getId(), sh.getId());
		questionRepo.save(questionA1_198);

		Question questionA1_7 = new Question(1,
				"Người ngồi trên xe mô tô hai bánh, ba bánh, xe gắn máy khi tham gia giao thông có được mang, vác vật cồng kềnh hay không?",
				true, "", a1.getId(), set7.getId(), kn.getId());
		questionRepo.save(questionA1_7);

		Question questionA1_15 = new Question(2,
				"Phần của đường bộ đươc sử dụng cho các phương tiện giao thông qua lại là gì?", true, "", a1.getId(),
				set7.getId(), kn.getId());
		questionRepo.save(questionA1_15);

		Question questionA1_23 = new Question(3, "\"Phương tiện tham gia giao thông đường bộ\" gồm những loại nào?",
				true, "", a1.getId(), set7.getId(), kn.getId());
		questionRepo.save(questionA1_23);

		Question questionA1_31 = new Question(4,
				"Người lái xe sử dụng đèn như thế nào khi lái xe trong khu đô thị và đông dân cư vào ban đêm?", true,
				"", a1.getId(), set7.getId(), kn.getId());
		questionRepo.save(questionA1_31);

		Question questionA1_39 = new Question(5,
				"Người có giấy phép lái xe mô tô hạng A1 được phép điều khiển các loại xe nào dưới đây?", true, "",
				a1.getId(), set7.getId(), kn.getId());
		questionRepo.save(questionA1_39);

		Question questionA1_47 = new Question(6,
				"Tại nơi có biển báo hiệu cố định lại có báo hiệu tạm thời thì người tham gia giao thông phải chấp hành hiệu lệnh của báo hiệu nào?",
				true, "", a1.getId(), set7.getId(), kn.getId());
		questionRepo.save(questionA1_47);

		Question questionA1_55 = new Question(7,
				"Bạn đang lái xe trên đường hẹp, xuống dốc và gặp một xe đang đi lên dốc, bạn cần làm gì?", true, "",
				a1.getId(), set7.getId(), kn.getId());
		questionRepo.save(questionA1_55);

		Question questionA1_63 = new Question(8,
				"Trên đoạn đường hai chiều không có giải phân cách giữa, người lái xe không được vượt xe khác trong các trường hợp nào dưới đây?",
				true, "", a1.getId(), set7.getId(), kn.getId());
		questionRepo.save(questionA1_63);

		Question questionA1_71 = new Question(9,
				"Trên đường bộ (trừ đường cao tốc) trong khu vực đông dân cư, đường hai chiều hoặc đường một chiều có một làn xe cơ giới, loại xe nào dưới đây được tham gia giao thông với tốc độ tối đa cho phép là 50 km",
				true, "", a1.getId(), set7.getId(), kn.getId());
		questionRepo.save(questionA1_71);

		Question questionA1_79 = new Question(10,
				"Người điều khiển xe mô tô phải giảm tốc độ và hết sức thận trọng khi qua những đoạn đường nào dưới đây?",
				true, "", a1.getId(), set7.getId(), kn.getId());
		questionRepo.save(questionA1_79);

		Question questionA1_87 = new Question(11,
				"Khi xảy ra tai nạn giao thông, có người bị thương nghiêm trọng, người lái xe và người có mặt tại hiện trường vụ tai nạn phải thực hiện các công việc gì dưới đây?",
				true, "", a1.getId(), set7.getId(), vh.getId());
		questionRepo.save(questionA1_87);

		Question questionA1_95 = new Question(12,
				"Tay ga trên xe mô tô hai bánh có tác dụng gì trong các trường hợp dưới đây?", true, "", a1.getId(),
				set7.getId(), kt.getId());
		questionRepo.save(questionA1_95);

		Question questionA1_103 = new Question(13, "Biển nào báo hiệu \"Giao nhau với đường ưu tiên\"?", true,
				"a1_103.jpg", a1.getId(), set7.getId(), bb.getId());
		questionRepo.save(questionA1_103);

		Question questionA1_111 = new Question(14, "Biển nào báo hiệu \"Đường bị thu hẹp\"?", true, "a1_111.jpg",
				a1.getId(), set7.getId(), bb.getId());
		questionRepo.save(questionA1_111);

		Question questionA1_119 = new Question(15,
				"Gặp biển nào người tham gia giao thông phải đi chậm và thận trọng đề phòng khả năng xuất hiện và di chuyển bất ngờ của trẻ em trên mặt đường?",
				true, "a1_119.jpg", a1.getId(), set7.getId(), bb.getId());
		questionRepo.save(questionA1_119);

		Question questionA1_127 = new Question(16, "Khi gặp biển nào xe được rẽ trái?", true, "a1_127.jpg", a1.getId(),
				set7.getId(), bb.getId());
		questionRepo.save(questionA1_127);

		Question questionA1_135 = new Question(17, "Gặp biển nào xe xích lô được phép đi vào?", true, "a1_135.jpg",
				a1.getId(), set7.getId(), bb.getId());
		questionRepo.save(questionA1_135);

		Question questionA1_143 = new Question(18, "Biển nào báo hiệu \"Đường dành cho xe thô sơ\"?", true,
				"a1_143.jpg", a1.getId(), set7.getId(), bb.getId());
		questionRepo.save(questionA1_143);

		Question questionA1_151 = new Question(19, "Biển nào cho phép xe rẽ trái?", true, "a1_151.jpg", a1.getId(),
				set7.getId(), bb.getId());
		questionRepo.save(questionA1_151);

		Question questionA1_159 = new Question(20, "Biển số 1 có ý nghĩa gì?", true, "a1_159.jpg", a1.getId(),
				set7.getId(), bb.getId());
		questionRepo.save(questionA1_159);

		Question questionA1_167 = new Question(21, "Theo hướng mũi tên, xe nào được phép đi?", true, "a1_167.jpg",
				a1.getId(), set7.getId(), sh.getId());
		questionRepo.save(questionA1_167);

		Question questionA1_175 = new Question(22, "Các xe đi theo hướng mũi tên, xe nào vi phạm quy tắc giao thông?",
				true, "a1_175.jpg", a1.getId(), set7.getId(), sh.getId());
		questionRepo.save(questionA1_175);

		Question questionA1_183 = new Question(23, "Xe nào đỗ vi phạm quy tắc giao thông?", true, "a1_183.jpg",
				a1.getId(), set7.getId(), sh.getId());
		questionRepo.save(questionA1_183);

		Question questionA1_191 = new Question(24,
				"Theo tín hiệu đèn của xe cơ giới, xe nào vi phạm quy tắc giao thông?", true, "a1_191.jpg", a1.getId(),
				set7.getId(), sh.getId());
		questionRepo.save(questionA1_191);

		Question questionA1_199 = new Question(25,
				"Xe của bạn đang di chuyển gần đến khu vực giao cắt với đường sắt, khi rào chắn đang dịch chuyển, bạn điều khiển xe như thế nào là đúng quy tắc giao thông?",
				true, "a1_199.jpg", a1.getId(), set7.getId(), sh.getId());
		questionRepo.save(questionA1_199);

		Question questionA1_8 = new Question(1,
				"Người ngồi trên xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy khi tham gia giao thông có được bám, kéo hoặc đẩy các phương thiện khác không?",
				true, "", a1.getId(), set8.getId(), kn.getId());
		questionRepo.save(questionA1_8);

		Question questionA1_16 = new Question(2, "\"Làn đường\" là gì?", true, "", a1.getId(), set8.getId(),
				kn.getId());
		questionRepo.save(questionA1_16);

		Question questionA1_24 = new Question(3,
				"\"Người điều khiển phương tiện tham gia giao thông đường bộ\" gồm những đối tượng nào dưới đây?", true,
				"", a1.getId(), set8.getId(), kn.getId());
		questionRepo.save(questionA1_24);

		Question questionA1_32 = new Question(4,
				"Khi lái xe trong khu đô thị và đông dân cư trừ các khu vực có biển cấm sử dụng còi, người lái xe được sử dụng còi như thế nào trong các trường hợp dưới đây?",
				true, "", a1.getId(), set8.getId(), kn.getId());
		questionRepo.save(questionA1_32);

		Question questionA1_40 = new Question(5,
				"Khi gặp hiệu lệnh như dưới đây của cảnh sát giao thông thì người tham gia giao thông phải đi như thế nào là đúng quy tắc giao thông?",
				true, "a1_40.jpg", a1.getId(), set8.getId(), kn.getId());
		questionRepo.save(questionA1_40);

		Question questionA1_48 = new Question(6,
				"Khi sử dụng giấy phép lái xe đã khai báo mất để điều khiển phương tiện cơ giới đường bộ, ngoài việc bị thu hồi giấy phép lái xe, chịu trách nhiệm trước pháp luật, người lái xe không được cấp giấy phép lái xe trong thời gian bao nhiêu năm?",
				true, "", a1.getId(), set8.getId(), kn.getId());
		questionRepo.save(questionA1_48);

		Question questionA1_56 = new Question(7,
				"Tại nơi đường giao nhau, người lái xe đang đi trên đường không ưu tiên phải nhường đường như thế nào là đúng quy tắc giao thông?",
				true, "", a1.getId(), set8.getId(), kn.getId());
		questionRepo.save(questionA1_56);

		Question questionA1_64 = new Question(8,
				"Tại ngã ba hoặc ngã tư không có đảm bảo an toàn, người lái xe phải nhường đường như thế nào là đúng trong các trường hợp dưới đây?",
				true, "", a1.getId(), set8.getId(), kn.getId());
		questionRepo.save(questionA1_64);

		Question questionA1_72 = new Question(9, "Khi điều khiển xe chạy với tốc độ dưới 60km", true, "", a1.getId(),
				set8.getId(), kn.getId());
		questionRepo.save(questionA1_72);

		Question questionA1_80 = new Question(10,
				"Khi gặp xe buýt đang dừng đón, trả khách, người điều khiển xe mô tô phải xử lý như thế nào dưới đây để đảm bảo an toàn giao thông?",
				true, "", a1.getId(), set8.getId(), kn.getId());
		questionRepo.save(questionA1_80);

		Question questionA1_88 = new Question(11,
				"Khi tránh nhau trên đường hẹp, người lái xe cần phải chú ý những điểm nào để đảm bảo an toàn giao thông?",
				true, "", a1.getId(), set8.getId(), kt.getId());
		questionRepo.save(questionA1_88);

		Question questionA1_96 = new Question(12,
				"Những thói quen nào dưới đây khi điều khiển xe mô tô tay ga tham gia giao thông dễ gây tai nạn nguy hiểm?",
				true, "", a1.getId(), set8.getId(), kt.getId());
		questionRepo.save(questionA1_96);

		Question questionA1_104 = new Question(13,
				"Biển nào báo hiệu, chỉ dẫn xe đi trên đường này được quyền ưu tiên qua nơi giao nhau?", true,
				"a1_104.jpg", a1.getId(), set8.getId(), bb.getId());
		questionRepo.save(questionA1_104);

		Question questionA1_112 = new Question(14,
				"Khi gặp biển nào, người lái xe phải giảm tốc độ, chú ý xe đi ngược chiều, xe đi ở phía đường bị hẹp phải nhường đường cho xe đi ngược chiều?",
				true, "a1_112.jpg", a1.getId(), set8.getId(), bb.getId());
		questionRepo.save(questionA1_112);

		Question questionA1_120 = new Question(15, "Biển nào chỉ dẫn nới bắt đầu đoạn đường dành cho người đi bộ?",
				true, "a1_120.jpg", a1.getId(), set8.getId(), bb.getId());
		questionRepo.save(questionA1_120);

		Question questionA1_128 = new Question(16, "Biển nào cấm các phương tiện giao thông đường bộ rẽ phải?", true,
				"a1_128.jpg", a1.getId(), set8.getId(), bb.getId());
		questionRepo.save(questionA1_128);

		Question questionA1_136 = new Question(17, "Gặp biển nào xe lam, xe xích lô máy được phép đi vào?", true,
				"a1_136.jpg", a1.getId(), set8.getId(), bb.getId());
		questionRepo.save(questionA1_136);

		Question questionA1_144 = new Question(18,
				"Biển nào (đặt trước ngã ba, ngã tư) cho phép xe được rẽ sang hướng khác?", true, "a1_144.jpg",
				a1.getId(), set8.getId(), bb.getId());
		questionRepo.save(questionA1_144);

		Question questionA1_152 = new Question(19, "Biển nào xe quay đầu không bị cấm?", true, "a1_152.jpg", a1.getId(),
				set8.getId(), bb.getId());
		questionRepo.save(questionA1_152);

		Question questionA1_160 = new Question(20,
				"Vạch kẻ đường nào dưới đây là vạch phân chia các làn xe cùng chiều?", true, "a1_160.jpg", a1.getId(),
				set8.getId(), bb.getId());
		questionRepo.save(questionA1_160);

		Question questionA1_168 = new Question(21,
				"Trong trường hợp này, thứ tự các xe đi như thế nào là đúng quy tắc giao thông?", true, "a1_168.jpg",
				a1.getId(), set8.getId(), sh.getId());
		questionRepo.save(questionA1_168);

		Question questionA1_176 = new Question(22, "Các xe đi theo hướng mũi tên xe nào vi phạm quy tắc giao thông?",
				true, "a1_176.jpg", a1.getId(), set8.getId(), sh.getId());
		questionRepo.save(questionA1_176);

		Question questionA1_184 = new Question(23, "Thứ tự các xe đi như thế nào là đúng quy tắc giao thông?", true,
				"a1_184.jpg", a1.getId(), set8.getId(), sh.getId());
		questionRepo.save(questionA1_184);

		Question questionA1_192 = new Question(24,
				"Xe tải kéo mô tô ba bánh như hình này có đúng quy tắc giao thông không?", true, "a1_192.jpg",
				a1.getId(), set8.getId(), sh.getId());
		questionRepo.save(questionA1_192);

		Question questionA1_200 = new Question(25,
				"Trong tình huống dưới đây, xe đầu kéo kéo rơ móoc (xe container) đang rẽ phải, xe con màu xanh và xe máy phía sau xe container đi như thế nào để đảm bảo an toàn?",
				true, "a1_200.jpg", a1.getId(), set8.getId(), sh.getId());
		questionRepo.save(questionA1_200);

		// Câu hỏi bằng A2
		Question questionA2_1 = new Question(1,
				"Phần của đường bộ được sử dụng cho các phương tiện giao thông qua lại là gì?", true, "", a2.getId(),
				set1.getId(), kn.getId());
		questionRepo.save(questionA2_1);

		Question questionA2_19 = new Question(2, "Cuộc đua xe chỉ được thực hiện khi nào?", true, "", a2.getId(),
				set1.getId(), kn.getId());
		questionRepo.save(questionA2_19);

		Question questionA2_37 = new Question(3,
				"Ở phần đường dành cho người đi bộ qua đường, trên cầu, đầu cầu, đường cao tốc, đường hẹp, đường dốc, tại nơi đường bộ giao nhau cùng mức với đường sắt có được quay đầu xe hay không?",
				true, "", a2.getId(), set1.getId(), kn.getId());
		questionRepo.save(questionA2_37);

		Question questionA2_55 = new Question(4,
				"Người có giấy phép lái xe mô tô hạng A1 không được phép điều khiển loại xe nào dưới đây?", true, "",
				a2.getId(), set1.getId(), kn.getId());
		questionRepo.save(questionA2_55);

		Question questionA2_73 = new Question(5,
				"Khi điều khiển xe chạy trên đường biết có xe sau xin vượt nêu đủ điều kiện an toàn người lái xe phải làm gì?",
				true, "", a2.getId(), set1.getId(), kn.getId());
		questionRepo.save(questionA2_73);

		Question questionA2_91 = new Question(6,
				"Người điều khiển xe mô tô hai bánh, xe gắn máy có được đi xe dàn hàng ngang; đi xe vào phần đường dành cho người đi bộ và phương tiện khác; sử dụng ô, điện thoại di động, thiết bị âm thanh (trừ thiết bị trợ thính) hay không?",
				true, "", a2.getId(), set1.getId(), kn.getId());
		questionRepo.save(questionA2_91);

		Question questionA2_109 = new Question(7,
				"Trên đường bộ ngoài khu vực đông dân cư, đường đôi có dải phân cách giữa (trừ đường cao tốc), loại xe nào dưới đây tham gia giao thông với tốc độ tối đa cho phép là 60 km/h?",
				true, "", a2.getId(), set1.getId(), kn.getId());
		questionRepo.save(questionA2_109);

		Question questionA2_127 = new Question(8,
				"Khi xe ô tô, mô tô đến gần vị trí giao nhau giữa đường bộ và đường sắt không có rào chắn, khi đèn tín hiệu màu đỏ đã bật sáng hoặc khi có tiếng chuông báo hiệu, người lái xe xử lý như thế nào?",
				true, "", a2.getId(), set1.getId(), kn.getId());
		questionRepo.save(questionA2_127);

		Question questionA2_145 = new Question(9,
				"Trên làn đường dành cho ô tô có vũng nước lớn, có nhiều người đi xe mô tô trên làn đường bên cạnh, người lái xe ô tô xử lý như thế nào là có văn hóa giao thông?",
				true, "", a2.getId(), set1.getId(), vh.getId());
		questionRepo.save(questionA2_145);

		Question questionA2_163 = new Question(10,
				"Khi tránh nhau trên đường hẹp, người lái xe cần phải chú ý những điểm nào để đảm bảo an toàn giao thông?",
				true, "", a2.getId(), set1.getId(), kt.getId());
		questionRepo.save(questionA2_163);

		Question questionA2_181 = new Question(11, "Thế nào là động cơ 4 kỳ?", true, "", a2.getId(), set1.getId(),
				kt.getId());
		questionRepo.save(questionA2_181);

		Question questionA2_199 = new Question(12, "Biển nào cấm xe rẽ trái?", true, "a2_199.jpg", a2.getId(),
				set1.getId(), bb.getId());
		questionRepo.save(questionA2_199);

		Question questionA2_217 = new Question(13, "Gặp biển nào xe xích lô được phép đi vào?", true, "a2_217.jpg",
				a2.getId(), set1.getId(), bb.getId());
		questionRepo.save(questionA2_217);

		Question questionA2_235 = new Question(14, "Biển nào xe mô tô hai bánh không được đi vào?", true, "a2_235.jpg",
				a2.getId(), set1.getId(), bb.getId());
		questionRepo.save(questionA2_235);

		Question questionA2_253 = new Question(15,
				"Biển nào chỉ đường dành cho người đi bộ, các loại xe không được đi vào khi gặp biển này?", true,
				"a2_253.jpg", a2.getId(), set1.getId(), bb.getId());
		questionRepo.save(questionA2_253);

		Question questionA2_271 = new Question(16, "Biển nào báo hiệu “Giao nhau với đường ưu tiên”?", true,
				"a2_271.jpg", a2.getId(), set1.getId(), bb.getId());
		questionRepo.save(questionA2_271);

		Question questionA2_289 = new Question(17, "Biển nào sau đây là biển “Kè, vực sâu bên đường phía bên trái”?",
				true, "a2_289.jpg", a2.getId(), set1.getId(), bb.getId());
		questionRepo.save(questionA2_289);

		Question questionA2_307 = new Question(18, "Các biển báo này có ý nghĩa gì?", true, "a2_307.jpg", a2.getId(),
				set1.getId(), bb.getId());
		questionRepo.save(questionA2_307);

		Question questionA2_325 = new Question(19,
				"Biển nào cho phép được quay đầu xe đi theo hướng ngược lại khi đặt biển trước ngã ba, ngã tư?", true,
				"a2_325.jpg", a2.getId(), set1.getId(), bb.getId());
		questionRepo.save(questionA2_325);

		Question questionA2_343 = new Question(20, "Biển số 1 có ý nghĩa gì?", true, "a2_343.jpg", a2.getId(),
				set1.getId(), bb.getId());
		questionRepo.save(questionA2_343);

		Question questionA2_361 = new Question(21,
				"Vạch kẻ đường nào dưới đây là vạch phân chia hai chiều xe chạy (vạch tim đường)?", true, "a2_361.jpg",
				a2.getId(), set1.getId(), bb.getId());
		questionRepo.save(questionA2_361);

		Question questionA2_379 = new Question(22,
				"Theo tín hiệu đèn, xe tải đi theo hướng nào là đúng quy tắc giao thông?", true, "a2_379.jpg",
				a2.getId(), set1.getId(), sh.getId());
		questionRepo.save(questionA2_379);

		Question questionA2_397 = new Question(23, "Các xe đi như thế nào là đúng quy tắc giao thông?", true,
				"a2_397.jpg", a2.getId(), set1.getId(), sh.getId());
		questionRepo.save(questionA2_397);

		Question questionA2_415 = new Question(24, "Xe nào đi trước là đúng quy tắc giao thông?", true, "a2_415.jpg",
				a2.getId(), set1.getId(), sh.getId());
		questionRepo.save(questionA2_415);

		Question questionA2_433 = new Question(25, "Bạn xử lý như thế nào trong trường hợp này?", true, "a2_433.jpg",
				a2.getId(), set1.getId(), sh.getId());
		questionRepo.save(questionA2_433);

		Question questionA2_2 = new Question(1, "“Làn đường là gì?", true, "", a2.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA2_2);

		Question questionA2_20 = new Question(2,
				"Người điều khiển phương tiện giao thông đường bộ mà trong cơ thể có chất ma tuý có bị nghiêm cấm hay không?",
				true, "", a2.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA2_20);

		Question questionA2_38 = new Question(3,
				"Bạn đang lái xe, phía trước có một xe cảnh sát giao thông không phát tín hiệu ưu tiên bạn có được phép vượt hay không?",
				true, "", a2.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA2_38);

		Question questionA2_56 = new Question(4, "Người có giấy phép lái xe mô tô hạ", true, "", a2.getId(),
				set2.getId(), kn.getId());
		questionRepo.save(questionA2_56);

		Question questionA2_74 = new Question(5,
				"Trong khu dân cư, ở nơi nào cho phép người lái xe, người điều khiển xe máy chuyên dùng được quay đầu xe?",
				true, "", a2.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA2_74);

		Question questionA2_92 = new Question(6,
				"Người lái xe phải giảm tốc độ thấp hơn tốc độ tối đa cho phép (có thể dùng lại một cách an toàn) trong trường hợp nào dưới đây?",
				true, "", a2.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA2_92);

		Question questionA2_110 = new Question(7,
				"Trên đường bộ ngoài khu vực đông dân cư, đường hai chiều không có dải phân cách giữa; đường một chiều có một làn xe cơ giới (trừ đường cao tốc), loại xe nào dưới đây tham gia giao thông với tốc độ tối đa cho phép là 80 km/h?",
				true, "", a2.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA2_110);

		Question questionA2_128 = new Question(8,
				"Khi điều khiển xe trên đường vòng, khuất tầm nhìn người lái xe cần phải làm gì để đảm bảo an toàn?",
				true, "", a2.getId(), set2.getId(), kn.getId());
		questionRepo.save(questionA2_128);

		Question questionA2_146 = new Question(9,
				"Người lái xe cố tình không phân biệt làn đường, vạch phân làn, phóng nhanh, vượt ẩu, vượt đèn đỏ, đi vào đường cấm, đường một chiều được coi là hành vi nào trong các hành vi dưới đây?",
				true, "", a2.getId(), set2.getId(), vh.getId());
		questionRepo.save(questionA2_146);

		Question questionA2_164 = new Question(10,
				"Khi điều khiển xe trên đường vòng người lái xe cần phải làm gì để đảm bảo an toàn?", true, "",
				a2.getId(), set2.getId(), kt.getId());
		questionRepo.save(questionA2_164);

		Question questionA2_182 = new Question(11, "Đèn phanh trên xe ô tô có tác dụng gì?", true, "", a2.getId(),
				set2.getId(), kt.getId());
		questionRepo.save(questionA2_182);

		Question questionA2_200 = new Question(12, "Khi gặp biển nào xe được rẽ trái?", true, "a2_200.jpg", a2.getId(),
				set2.getId(), bb.getId());
		questionRepo.save(questionA2_200);

		Question questionA2_218 = new Question(13, "Gặp biển nào xe lam, xe xích lô máy được phép đi vào?", true,
				"a2_218.jpg", a2.getId(), set2.getId(), bb.getId());
		questionRepo.save(questionA2_218);

		Question questionA2_236 = new Question(14, "Ba biển này có hiệu lực như thế nào?", true, "a2_236.jpg",
				a2.getId(), set2.getId(), bb.getId());
		questionRepo.save(questionA2_236);

		Question questionA2_254 = new Question(15, "Biển nào báo hiệu “Đường dành cho xe thô sơ”?", true, "a2_254.jpg",
				a2.getId(), set2.getId(), bb.getId());
		questionRepo.save(questionA2_254);

		Question questionA2_272 = new Question(16, "Biển nào báo hiệu “Đường giao nhau” của các tuyến đường cùng cấp?",
				true, "a2_272.jpg", a2.getId(), set2.getId(), bb.getId());
		questionRepo.save(questionA2_272);

		Question questionA2_290 = new Question(17, "Biển nào sau đây là biển “Kè, vực sâu bên đường phía bên phải”?",
				true, "a2_290.jpg", a2.getId(), set2.getId(), bb.getId());
		questionRepo.save(questionA2_290);

		Question questionA2_308 = new Question(18, "Biển báo này có ý nghĩa gì?", true, "a2_308.jpg", a2.getId(),
				set2.getId(), bb.getId());
		questionRepo.save(questionA2_308);

		Question questionA2_326 = new Question(19, "Biển nào không cho phép rẽ phải?", true, "a2_326.jpg", a2.getId(),
				set2.getId(), bb.getId());
		questionRepo.save(questionA2_326);

		Question questionA2_344 = new Question(20, "Biển nào báo hiệu “Tuyến đường cầu vượt cắt qua”?", true,
				"a2_344.jpg", a2.getId(), set2.getId(), bb.getId());
		questionRepo.save(questionA2_344);

		Question questionA2_362 = new Question(21, "Các vạch dưới đây có tác dụng gì?", true, "a2_362.jpg", a2.getId(),
				set2.getId(), bb.getId());
		questionRepo.save(questionA2_362);

		Question questionA2_380 = new Question(22, "Các xe đi theo hướng mũi tên, xe nào vi phạm quy tắc giao thông?",
				true, "a2_380.jpg", a2.getId(), set2.getId(), sh.getId());
		questionRepo.save(questionA2_380);

		Question questionA2_398 = new Question(23, "Theo hướng mũi tên, xe nào được phép đi?", true, "a2_398.jpg",
				a2.getId(), set2.getId(), sh.getId());
		questionRepo.save(questionA2_398);

		Question questionA2_416 = new Question(24,
				"Người lái xe có thể quay đầu xe như thế nào là đúng quy tắc giao thông?", true, "a2_416.jpg",
				a2.getId(), set2.getId(), sh.getId());
		questionRepo.save(questionA2_416);

		Question questionA2_434 = new Question(25, "Bạn xử lý như thế nào trong trường hợp này?", true, "a2_434.jpg",
				a2.getId(), set2.getId(), sh.getId());
		questionRepo.save(questionA2_434);

		Question questionA2_3 = new Question(1, "Khái niệm “khổ giới hạn của đường bộ” được hiểu như thế nào là đúng?",
				true, "", a2.getId(), set3.getId(), kn.getId());
		questionRepo.save(questionA2_3);

		Question questionA2_21 = new Question(2,
				"Việc lái xe mô tô, ô tô, máy kéo ngay sau khi uống rượu, bia có được phép hay không?", true, "",
				a2.getId(), set3.getId(), kn.getId());
		questionRepo.save(questionA2_21);

		Question questionA2_39 = new Question(3,
				"Bạn đang lái xe, phía trước có một xe cứu thương đang phát tín hiệu ưu tiên bạn có được phép vượt hay không?",
				true, "", a2.getId(), set3.getId(), kn.getId());
		questionRepo.save(questionA2_39);

		Question questionA2_57 = new Question(4,
				"Người có giấy phép lái xe mô tô hạng A2 được phép điều khiển loại xe nào dưới đây?", true, "",
				a2.getId(), set3.getId(), kn.getId());
		questionRepo.save(questionA2_57);

		Question questionA2_75 = new Question(5,
				"Người lái xe phải làm gì khi quay đầu xe trên cầu, đường ngâm hay khu vực đường bộ giao nhau cùng mức với đường sắt?",
				true, "", a2.getId(), set3.getId(), kn.getId());
		questionRepo.save(questionA2_75);

		Question questionA2_93 = new Question(6,
				"Khi gặp một đoàn xe, một đoàn xe tăng hay gặp một đoàn người có tổ chức theo đội ngũ, người lái xe phải xử lý như thế nào?",
				true, "", a2.getId(), set3.getId(), kn.getId());
		questionRepo.save(questionA2_93);

		Question questionA2_111 = new Question(7,
				"Trên đường bộ ngoài khu vực đông dân cư, đường hai chiều không có dải phân cách giữa đường một chiều có 01 làn xe cơ giới (trừ đường cao tốc), loại xe nào dưới đây tham gia giao thông với tốc độ tối đa cho phép là 70 km/h?",
				true, "", a2.getId(), set3.getId(), kn.getId());
		questionRepo.save(questionA2_111);

		Question questionA2_129 = new Question(8,
				"Người lái xe phải xử lý như thế nào khi quan sát phía trước thấy người đi bộ đang sang đường tại nơi có vạch đường dành cho người đi bộ để đảm bảo an toàn?",
				true, "", a2.getId(), set3.getId(), kn.getId());
		questionRepo.save(questionA2_129);

		Question questionA2_147 = new Question(9,
				"Khi sơ cứu người bị tai nạn giao thông đường bộ, có vết thương chảy máu ngoài, màu đỏ tươi phun thành tia và phun mạnh khi mạch đập, bạn phải làm gì dưới đây?",
				true, "", a2.getId(), set3.getId(), vh.getId());
		questionRepo.save(questionA2_147);

		Question questionA2_165 = new Question(10,
				"Khi điều khiển xe qua đường sắt, người lái xe cần phải thực hiện các thao tác nào dưới đây để đảm bảo an toàn?",
				true, "", a2.getId(), set3.getId(), kt.getId());
		questionRepo.save(questionA2_165);

		Question questionA2_183 = new Question(11,
				"Trên xe ô tô có trang bị thiết bị như hình vẽ có tác dụng gì dưới đây?", true, "a2_183.jpg",
				a2.getId(), set3.getId(), kt.getId());
		questionRepo.save(questionA2_183);

		Question questionA2_201 = new Question(12, "Biển nào cấm ô tô rẽ trái?", true, "a2_201.jpg", a2.getId(),
				set3.getId(), bb.getId());
		questionRepo.save(questionA2_201);

		Question questionA2_219 = new Question(13, "Biển báo này có ý nghĩa gì?", true, "a2_219.jpg", a2.getId(),
				set3.getId(), bb.getId());
		questionRepo.save(questionA2_219);

		Question questionA2_237 = new Question(14,
				"Biển nào báo hiệu chiều dài đoạn đường phải giữ cự ly tôi thiếu giữa hai xe?", true, "a2_237.jpg",
				a2.getId(), set3.getId(), bb.getId());
		questionRepo.save(questionA2_237);

		Question questionA2_255 = new Question(15, "Biển nào báo hiệu sắp đến chỗ giao nhau nguy hiểm?", true,
				"a2_255.jpg", a2.getId(), set3.getId(), bb.getId());
		questionRepo.save(questionA2_255);

		Question questionA2_273 = new Question(16, "Biển nào báo hiệu “Giao nhau với đường không ưu tiên”?", true,
				"a2_273.jpg", a2.getId(), set3.getId(), bb.getId());
		questionRepo.save(questionA2_273);

		Question questionA2_291 = new Question(17, "Biển nào sau đây là biển “Đường trơn”?", true, "a2_291.jpg",
				a2.getId(), set3.getId(), bb.getId());
		questionRepo.save(questionA2_291);

		Question questionA2_309 = new Question(18, "Gặp biển báo này người lái xe phải xử lý thế nào?", true,
				"a2_309.jpg", a2.getId(), set3.getId(), bb.getId());
		questionRepo.save(questionA2_309);

		Question questionA2_327 = new Question(19,
				"Khi đến chỗ giao nhau, gặp biển nào thì người lái xe không được cho xe đi thẳng, phải rẽ sang hướng khác?",
				true, "a2_327.jpg", a2.getId(), set3.getId(), bb.getId());
		questionRepo.save(questionA2_327);

		Question questionA2_345 = new Question(20, "Biển báo dưới đây có ý nghĩa gì?", true, "a2_345.jpg", a2.getId(),
				set3.getId(), bb.getId());
		questionRepo.save(questionA2_345);

		Question questionA2_363 = new Question(21, "Các vạch dưới đây có tác dụng gì?", true, "a2_363.jpg", a2.getId(),
				set3.getId(), bb.getId());
		questionRepo.save(questionA2_363);

		Question questionA2_381 = new Question(22, "Thứ tự các xe đi như thế nào là đúng quy tắc giao thông?", true,
				"a2_381.jpg", a2.getId(), set3.getId(), sh.getId());
		questionRepo.save(questionA2_381);

		Question questionA2_399 = new Question(23, "Xe con vượt xe tải như trường hợp này có đúng không?", true,
				"a2_399.jpg", a2.getId(), set3.getId(), sh.getId());
		questionRepo.save(questionA2_399);

		Question questionA2_417 = new Question(24, "Thứ tự các xe đi như thế nào là đúng quy tắc giao thông?", true,
				"a2_417.jpg", a2.getId(), set3.getId(), sh.getId());
		questionRepo.save(questionA2_417);

		Question questionA2_435 = new Question(25, "Bạn xử lý như thế nào trong trường hợp này?", true, "a2_435.jpg",
				a2.getId(), set3.getId(), sh.getId());
		questionRepo.save(questionA2_435);

		Question questionA2_4 = new Question(1,
				"Trong các khái niệm dưới đây, “dải phân cách” được hiểu như thế nào là đúng?", true, "", a2.getId(),
				set4.getId(), kn.getId());
		questionRepo.save(questionA2_4);

		Question questionA2_22 = new Question(2,
				"Người điều khiển xe môtô, ô tô, máy kéo trên đường mà trong máu hoặc hơi thở có nồng độ cồn có bị nghiêm cấm không?",
				true, "", a2.getId(), set4.getId(), kn.getId());
		questionRepo.save(questionA2_22);

		Question questionA2_40 = new Question(3,
				"Người lái xe không được quay đầu xe trong các trường hợp nào dưới đây?", true, "", a2.getId(),
				set4.getId(), kn.getId());
		questionRepo.save(questionA2_40);

		Question questionA2_58 = new Question(4,
				"Người có giấy phép lái xe mô tô hạng A3 được phép điều khiển loại xe nào dưới đây?", true, "",
				a2.getId(), set4.getId(), kn.getId());
		questionRepo.save(questionA2_58);

		Question questionA2_76 = new Question(5,
				"Khi muốn chuyển hướng, người lái xe phải thực hiện như thế nào để đảm bảo an toàn giao thông?", true,
				"", a2.getId(), set4.getId(), kn.getId());
		questionRepo.save(questionA2_76);

		Question questionA2_94 = new Question(6,
				"Tại ngã ba hoặc ngã tư không có đảo an toàn, người lái xe phải nhường đường như thế nào là đúng trong các trường hợp dưới đây?",
				true, "", a2.getId(), set4.getId(), kn.getId());
		questionRepo.save(questionA2_94);

		Question questionA2_112 = new Question(7,
				"Trên đường bộ ngoài khu vực đông dân cư, đường hai chiều không có dải phân cách giữa đường một chiều có 01 làn xe cơ giới (trừ đường cao tốc), loại xe nào dưới đây tham gia giao thông với tốc độ tối đa cho phép là 60 km/h?",
				true, "", a2.getId(), set4.getId(), kn.getId());
		questionRepo.save(questionA2_112);

		Question questionA2_130 = new Question(8,
				"Người điều khiển phương tiện tham gia giao thông đường bộ phải giảm tốc độ để có thể dùng lại một cách an toàn trong các trường hợp nào dưới đây?",
				true, "", a2.getId(), set4.getId(), kn.getId());
		questionRepo.save(questionA2_130);

		Question questionA2_148 = new Question(9,
				"Người lái xe có văn hóa khi tham gia giao thông phải đáp ứng các điều kiện nào dưới đây?", true, "",
				a2.getId(), set4.getId(), vh.getId());
		questionRepo.save(questionA2_148);

		Question questionA2_166 = new Question(10, "Các biện pháp tiết kiệm nhiên liệu khi chạy xe?", true, "",
				a2.getId(), set4.getId(), kt.getId());
		questionRepo.save(questionA2_166);

		Question questionA2_184 = new Question(11, "Trên xe ô tô có trang bị thiết bị như hình vẽ, dùng để làm gì?",
				true, "a2_184.jpg", a2.getId(), set4.getId(), kt.getId());
		questionRepo.save(questionA2_184);

		Question questionA2_202 = new Question(12, "Biển nào cấm các phương tiện giao thông đường bộ rẽ phải?", true,
				"a2_202.jpg", a2.getId(), set4.getId(), bb.getId());
		questionRepo.save(questionA2_202);

		Question questionA2_220 = new Question(13, "Biển báo này có ý nghĩa như thế nào?", true, "a2_220.jpg",
				a2.getId(), set4.getId(), bb.getId());
		questionRepo.save(questionA2_220);

		Question questionA2_238 = new Question(14,
				"Biển nào báo hiệu khoảng cách thực tế từ nơi đặt biển đến nơi cần cự ly tối thiểu giữa hai xe?", true,
				"a2_238.jpg", a2.getId(), set4.getId(), bb.getId());
		questionRepo.save(questionA2_238);

		Question questionA2_256 = new Question(15, "Biển nào báo hiệu “Giao nhau với đường sắt có rào chắn”?", true,
				"a2_256.jpg", a2.getId(), set4.getId(), bb.getId());
		questionRepo.save(questionA2_256);

		Question questionA2_274 = new Question(16, "Biển nào báo hiệu “Đường hai chiều”?", true, "a2_274.jpg",
				a2.getId(), set4.getId(), bb.getId());
		questionRepo.save(questionA2_274);

		Question questionA2_292 = new Question(17, "Biển nào sau đây là biển “Lề đường nguy hiểm”?", true, "a2_292.jpg",
				a2.getId(), set4.getId(), bb.getId());
		questionRepo.save(questionA2_292);

		Question questionA2_310 = new Question(18, "Biển báo này có ý nghĩa gì?", true, "a2_310.jpg", a2.getId(),
				set4.getId(), bb.getId());
		questionRepo.save(questionA2_310);

		Question questionA2_328 = new Question(19, "Biển nào cho phép quay đầu xe?", true, "a2_328.jpg", a2.getId(),
				set4.getId(), bb.getId());
		questionRepo.save(questionA2_328);

		Question questionA2_346 = new Question(20,
				"Tại đoạn đường có biển “Làn đường dành riêng cho từng loại xe” dưới đây, các phương tiện có được phép chuyển sang làn khác để đi theo hành trình mong muốn khi đến gần nơi đường bộ giao nhau hay không?",
				true, "a2_346.jpg", a2.getId(), set4.getId(), bb.getId());
		questionRepo.save(questionA2_346);

		Question questionA2_364 = new Question(21, "Khi gặp vạch kẻ đường nào các xe được phép đè vạch?", true,
				"a2_364.jpg", a2.getId(), set4.getId(), bb.getId());
		questionRepo.save(questionA2_364);

		Question questionA2_382 = new Question(22, "Trong trường hợp này xe nào đỗ vi phạm quy tắc giao thông?", true,
				"a2_382.jpg", a2.getId(), set4.getId(), sh.getId());
		questionRepo.save(questionA2_382);

		Question questionA2_400 = new Question(23, "Xe nào vượt đúng quy tắc giao thông?", true, "a2_400.jpg",
				a2.getId(), set4.getId(), sh.getId());
		questionRepo.save(questionA2_400);

		Question questionA2_418 = new Question(24,
				"Theo tín hiệu đèn, xe nào phải dừng lại là đúng quy tắc giao thông?", true, "a2_418.jpg", a2.getId(),
				set4.getId(), sh.getId());
		questionRepo.save(questionA2_418);

		Question questionA2_436 = new Question(25, "Xe nào phải nhường đường trong trường hợp này?", true, "a2_436.jpg",
				a2.getId(), set4.getId(), sh.getId());
		questionRepo.save(questionA2_436);

		Question questionA2_5 = new Question(1, "Dải phân cách trên đường bộ gồm những loại nào?", true, "", a2.getId(),
				set5.getId(), kn.getId());
		questionRepo.save(questionA2_5);

		Question questionA2_23 = new Question(2,
				"Sử dụng rượu, bia khi lái xe, nếu bị phát hiện thì bị xử lý như thế nào?", true, "", a2.getId(),
				set5.getId(), kn.getId());
		questionRepo.save(questionA2_23);

		Question questionA2_41 = new Question(3,
				"Bạn đang lái xe trong khu dân cư, có đông xe qua lại, nếu muốn quay đầu bạn cần làm gì để tránh ùn tắc và đảm bảo an toàn giao thông?",
				true, "", a2.getId(), set5.getId(), kn.getId());
		questionRepo.save(questionA2_41);

		Question questionA2_59 = new Question(4,
				"Biển báo hiệu có dạng hình tròn, viền đỏ, nền trắng, trên nền có hình vẽ hoặc chữ số, chữ viết màu đen loại biển gì dưới đây?",
				true, "a2_59.jpg", a2.getId(), set5.getId(), kn.getId());
		questionRepo.save(questionA2_59);

		Question questionA2_77 = new Question(5, "Khi lùi xe người lái xe phải làm gì để bảo đảm an toàn?", true, "",
				a2.getId(), set5.getId(), kn.getId());
		questionRepo.save(questionA2_77);

		Question questionA2_95 = new Question(6,
				"Khi điều khiển xe cơ giới, người lái xe phải bật đèn tín hiệu báo rẽ trong trường hợp nào sau đây?",
				true, "", a2.getId(), set5.getId(), kn.getId());
		questionRepo.save(questionA2_95);

		Question questionA2_113 = new Question(7,
				"Trên đường bộ ngoài khu vực đông dân cư, đường hai chiều không có dải phân cách giữa đường một chiều có 01 làn xe cơ giới (trừ đường cao tốc), loại xe nào dưới đây tham gia giao thông với tốc độ tối đa cho phép là 50 km/h?",
				true, "", a2.getId(), set5.getId(), kn.getId());
		questionRepo.save(questionA2_113);

		Question questionA2_131 = new Question(8,
				"Tại những đoạn đường không bố trí biển báo hạn chế tốc độ, không bố trí biển báo khoảng cách an toàn tối thiểu giữa hai xe, người điều khiển phương tiện tham gia giao thông phải thực hiện quy định nào dưới đây để đảm bảo an toàn giao thông?",
				true, "", a2.getId(), set5.getId(), kn.getId());
		questionRepo.save(questionA2_131);

		Question questionA2_149 = new Question(9,
				"Trong các hành vi dưới đây, người lái xe mô tô có văn hóa giao thông phải ứng xử như thế nào?", true,
				"", a2.getId(), set5.getId(), vh.getId());
		questionRepo.save(questionA2_149);

		Question questionA2_167 = new Question(10,
				"Khi tầm nhìn bị hạn chế bởi sương mù hoặc mưa to, người lái xe phải thực hiện các thao tác nào?", true,
				"", a2.getId(), set5.getId(), kt.getId());
		questionRepo.save(questionA2_167);

		Question questionA2_185 = new Question(11, "Trên xe ô tô có trang bị thiết bị như hình vẽ, dùng để làm gì?",
				true, "a2_185.jpg", a2.getId(), set5.getId(), kt.getId());
		questionRepo.save(questionA2_185);

		Question questionA2_203 = new Question(12, "Biển nào cấm các phương tiện giao thông đường bộ rẽ trái?", true,
				"a2_203.jpg", a2.getId(), set5.getId(), bb.getId());
		questionRepo.save(questionA2_203);

		Question questionA2_221 = new Question(13,
				"Khi gặp các biển này xe ưu tiên theo luật định (có tải trọng hay chiều cao toàn bộ vượt quá chỉ số ghi trên biển) có được phép đi qua hay không?",
				true, "a2_221.jpg", a2.getId(), set5.getId(), bb.getId());
		questionRepo.save(questionA2_221);

		Question questionA2_239 = new Question(14, "Biển này có ý nghĩa gì?", true, "a2_239.jpg", a2.getId(),
				set5.getId(), bb.getId());
		questionRepo.save(questionA2_239);

		Question questionA2_257 = new Question(15, "Biển nào báo hiệu “Giao nhau có tín hiệu đèn”?", true, "a2_257.jpg",
				a2.getId(), set5.getId(), bb.getId());
		questionRepo.save(questionA2_257);

		Question questionA2_275 = new Question(16,
				"Biển nào báo hiệu phải giảm tốc độ, nhường đường cho xe cơ giới đi ngược chiều qua đường hẹp?", true,
				"a2_275.jpg", a2.getId(), set5.getId(), bb.getId());
		questionRepo.save(questionA2_275);

		Question questionA2_293 = new Question(17,
				"Biển nào sau đây báo trước gần tới đoạn đường đang tiến hành thi công?", true, "a2_293.jpg",
				a2.getId(), set5.getId(), bb.getId());
		questionRepo.save(questionA2_293);

		Question questionA2_311 = new Question(18,
				"Biển nào báo hiệu các phương tiện phải đi đúng làn đường quy định và tuân thủ tốc độ tối đa cho phép?",
				true, "a2_311.jpg", a2.getId(), set5.getId(), bb.getId());
		questionRepo.save(questionA2_311);

		Question questionA2_329 = new Question(19, "Biển nào chỉ dẫn tên đường trên các tuyến đường đối ngoại?", true,
				"a2_329.jpg", a2.getId(), set5.getId(), bb.getId());
		questionRepo.save(questionA2_329);

		Question questionA2_347 = new Question(20,
				"Tại đoạn đường có cắm biển dưới đây, xe tải và xe khách có được phép đi vào không?", true,
				"a2_347.jpg", a2.getId(), set5.getId(), bb.getId());
		questionRepo.save(questionA2_347);

		Question questionA2_365 = new Question(21, "Vạch dưới đây có tác dụng gì?", true, "a2_365.jpg", a2.getId(),
				set5.getId(), bb.getId());
		questionRepo.save(questionA2_365);

		Question questionA2_383 = new Question(22, "Xe nào được quyền đi trước?", true, "a2_383.jpg", a2.getId(),
				set5.getId(), sh.getId());
		questionRepo.save(questionA2_383);

		Question questionA2_401 = new Question(23, "Đi theo hướng mũi tên, xe nào vi phạm quy tắc giao thông?", true,
				"a2_401.jpg", a2.getId(), set5.getId(), sh.getId());
		questionRepo.save(questionA2_401);

		Question questionA2_419 = new Question(24, "Xe nào phải nhường đường là đúng quy tắc giao thông?", true,
				"a2_419.jpg", a2.getId(), set5.getId(), sh.getId());
		questionRepo.save(questionA2_419);

		Question questionA2_437 = new Question(25,
				"Bạn xử lý như thế nào khi lái xe ô tô vượt qua đoàn người đi xe đạp có tổ chức?", true, "a2_437.jpg",
				a2.getId(), set5.getId(), sh.getId());
		questionRepo.save(questionA2_437);

		Question questionA2_6 = new Question(1, "Người lái xe được hiểu như thế nào trong các khái niệm dưới đây?",
				true, "", a2.getId(), set6.getId(), kn.getId());
		questionRepo.save(questionA2_6);

		Question questionA2_24 = new Question(2,
				"Theo Luật phòng chống tác hại của rượu, bia, đối tượng nào dưới đây bị cấm sử dụng rượu, bia khi tham gia giao thông?",
				true, "", a2.getId(), set6.getId(), kn.getId());
		questionRepo.save(questionA2_24);

		Question questionA2_42 = new Question(3, "Người lái xe không được lùi xe ở những khu vực nào dưới đây?", true,
				"", a2.getId(), set6.getId(), kn.getId());
		questionRepo.save(questionA2_42);

		Question questionA2_60 = new Question(4,
				"Biển báo hiệu có dạng tam giác đều, viền đỏ, nền màu vàng, trên có hình vẽ màu đen là loại biển gì dưới đây?",
				true, "a2_60.jpg", a2.getId(), set6.getId(), kn.getId());
		questionRepo.save(questionA2_60);

		Question questionA2_78 = new Question(5,
				"Khi tránh xe đi ngược chiều, các xe phải nhường đường như thế nào là đúng quy tắc giao thông?", true,
				"", a2.getId(), set6.getId(), kn.getId());
		questionRepo.save(questionA2_78);

		Question questionA2_96 = new Question(6,
				"Trên đoạn đường hai chiều không có giải phân cách giữa, người lái xe không được vượt xe khác trong các trường hợp nào dưới đây?",
				true, "", a2.getId(), set6.getId(), kn.getId());
		questionRepo.save(questionA2_96);

		Question questionA2_114 = new Question(7,
				"Khi tham gia giao thông trên đoạn đường không có biển báo “Cự ly tối thiểu giữa hai xe”, với điều kiện mặt đường khô ráo, xe cơ giới đang chạy với tốc độ từ trên 60 km/h đến 80 km/h, người lái xe phải giữ khoảng cách an toàn với xe đang chạy liền trước tối thiểu là bao nhiêu?",
				true, "", a2.getId(), set6.getId(), kn.getId());
		questionRepo.save(questionA2_114);

		Question questionA2_132 = new Question(8,
				"Theo Luật Giao thông đường bộ, tín hiệu đèn giao thông gồm 3 màu nào dưới đây?", true, "", a2.getId(),
				set6.getId(), kn.getId());
		questionRepo.save(questionA2_132);

		Question questionA2_150 = new Question(9,
				"Trong các hành vi dưới đây, người lái xe mô tô có văn hóa giao thông phải ứng xử như thế nào?", true,
				"", a2.getId(), set6.getId(), vh.getId());
		questionRepo.save(questionA2_150);

		Question questionA2_168 = new Question(10,
				"Khi đèn pha của xe đi ngược chiều gây chói mắt, làm giảm khả năng quan sát trên đường, người lái xe xử lý như thế nào để đảm bảo an toàn?",
				true, "", a2.getId(), set6.getId(), kt.getId());
		questionRepo.save(questionA2_168);

		Question questionA2_186 = new Question(11,
				"Biển nào cấm các loại xe cơ giới đi vào, trừ xe gắn máy, mô tô hai bánh và các loại xe ưu tiên theo luật định?",
				true, "a2_186.jpg", a2.getId(), set6.getId(), bb.getId());
		questionRepo.save(questionA2_186);

		Question questionA2_204 = new Question(12, "Biển nào dưới đây cấm ô tô quay đầu?", true, "a2_204.jpg",
				a2.getId(), set6.getId(), bb.getId());
		questionRepo.save(questionA2_204);

		Question questionA2_222 = new Question(13, "Biển nào hạn chế chiều cao của xe và hàng?", true, "a2_222.jpg",
				a2.getId(), set6.getId(), bb.getId());
		questionRepo.save(questionA2_222);

		Question questionA2_240 = new Question(14, "Theo hướng bên phải có được phép đỗ xe, dừng xe không?", true,
				"a2_240.jpg", a2.getId(), set6.getId(), bb.getId());
		questionRepo.save(questionA2_240);

		Question questionA2_258 = new Question(15, "Biển nào báo hiệu nguy hiểm giao nhau với đường sắt?", true,
				"a2_258.jpg", a2.getId(), set6.getId(), bb.getId());
		questionRepo.save(questionA2_258);

		Question questionA2_276 = new Question(16, "Biển nào chỉ dẫn “Được ưu tiên qua đường hẹp”?", true, "a2_276.jpg",
				a2.getId(), set6.getId(), bb.getId());
		questionRepo.save(questionA2_276);

		Question questionA2_294 = new Question(17,
				"Biển nào sau đây cảnh báo nguy hiểm đoạn đường thường xảy ra tai nạn?", true, "a2_294.jpg", a2.getId(),
				set6.getId(), bb.getId());
		questionRepo.save(questionA2_294);

		Question questionA2_312 = new Question(18,
				"Biển nào báo hiệu các phương tiện phải tuân thủ tốc độ tối đa cho phép trên từng làn đường?", true,
				"a2_312.jpg", a2.getId(), set6.getId(), bb.getId());
		questionRepo.save(questionA2_312);

		Question questionA2_330 = new Question(19, "Biển số 1 có ý nghĩa gì?", true, "a2_330.jpg", a2.getId(),
				set6.getId(), bb.getId());
		questionRepo.save(questionA2_330);

		Question questionA2_348 = new Question(20,
				"Tại đoạn đường có cắm biển dưới đây, xe tải và xe khách có được phép đi vào không?", true,
				"a2_348.jpg", a2.getId(), set6.getId(), bb.getId());
		questionRepo.save(questionA2_348);

		Question questionA2_366 = new Question(21, "Vạch dưới đây có ý nghĩa gì?", true, "a2_366.jpg", a2.getId(),
				set6.getId(), bb.getId());
		questionRepo.save(questionA2_366);

		Question questionA2_384 = new Question(22, "Theo hướng mũi tên, những hướng nào xe gắn máy đi được?", true,
				"a2_384.jpg", a2.getId(), set6.getId(), sh.getId());
		questionRepo.save(questionA2_384);

		Question questionA2_402 = new Question(23, "Thứ tự các xe đi như thế nào là đúng quy tắc giao thông?", true,
				"a2_402.jpg", a2.getId(), set6.getId(), sh.getId());
		questionRepo.save(questionA2_402);

		Question questionA2_420 = new Question(24, "Bạn có được phép vượt xe mô tô phía trước không?", true,
				"a2_420.jpg", a2.getId(), set6.getId(), sh.getId());
		questionRepo.save(questionA2_420);

		Question questionA2_438 = new Question(25, "Bạn xử lý như thế nào trong trường hợp này?", true, "a2_438.jpg",
				a2.getId(), set6.getId(), sh.getId());
		questionRepo.save(questionA2_438);

		Question questionA2_7 = new Question(1,
				"Đường mà trên đó phương tiện tham gia giao thông được các phương tiện giao thông đến từ hướng khác nhường đường khi qua nơi đường giao nhau, được cắm biển báo hiệu đường ưu tiên là loại đường gì?",
				true, "", a2.getId(), set7.getId(), kn.getId());
		questionRepo.save(questionA2_7);

		Question questionA2_25 = new Question(2,
				"Hành vi giao xe cơ giới, xe máy chuyên dùng cho người không đủ điều kiện để điều khiển xe tham gia giao thông có được phép hay không?",
				true, "", a2.getId(), set7.getId(), kn.getId());
		questionRepo.save(questionA2_25);

		Question questionA2_43 = new Question(3,
				"Người điều khiển phương tiện giao thông trên đường phố có được dùng xe, đỗ xe trên miệng cống thoát nước, miệng hâm của đường điện thoại, điện cao thế, chỗ dành riêng cho xe chữa cháy lấy nước hay không?",
				true, "", a2.getId(), set7.getId(), kn.getId());
		questionRepo.save(questionA2_43);

		Question questionA2_61 = new Question(4,
				"Biển báo hiệu hình tròn có nền xanh lam có hình vẽ màu trắng là loại biển gì dưới đây?", true,
				"a2_61.jpg", a2.getId(), set7.getId(), kn.getId());
		questionRepo.save(questionA2_61);

		Question questionA2_79 = new Question(5,
				"Bạn đang lái xe trên đường hẹp, xuống dốc và gặp một xe đang đi lên dốc, bạn cần làm gì?", true, "",
				a2.getId(), set7.getId(), kn.getId());
		questionRepo.save(questionA2_79);

		Question questionA2_97 = new Question(6,
				"Khi lái xe trên đường vắng mà cảm thấy buồn ngủ, người lái xe nên chọn cách xử lý như thế nào cho phù hợp?",
				true, "", a2.getId(), set7.getId(), kn.getId());
		questionRepo.save(questionA2_97);

		Question questionA2_115 = new Question(7,
				"Khi tham gia giao thông trên đoạn đường không có biển báo “Cự ly tối thiểu giữa hai xe”, với điều kiện mặt đường khô ráo, xe cơ giới đang chạy với tốc độ từ trên 80 km/h đến 100 km/h, người lái xe phải giữ khoảng cách an toàn với xe đang chạy liền trước tối thiểu là bao nhiêu?",
				true, "", a2.getId(), set7.getId(), kn.getId());
		questionRepo.save(questionA2_115);

		Question questionA2_133 = new Question(8,
				"Tại nơi đường giao nhau, khi đèn điều khiển giao thông có tín hiệu màu vàng, người điều khiển giao thông phải chấp hành như thế nào là đúng quy tắc giao thông?",
				true, "", a2.getId(), set7.getId(), kn.getId());
		questionRepo.save(questionA2_133);

		Question questionA2_151 = new Question(9,
				"Trong các hành vi dưới đây, người lái xe ô tô, mô tô có văn hóa giao thông phải ứng xử như thế nào?",
				true, "", a2.getId(), set7.getId(), vh.getId());
		questionRepo.save(questionA2_151);

		Question questionA2_169 = new Question(10,
				"Để đạt được hiệu quả phanh cao nhất, người lái xe mô tô phải sử dụng các kỹ năng như thế nào dưới đây?",
				true, "", a2.getId(), set7.getId(), kt.getId());
		questionRepo.save(questionA2_169);

		Question questionA2_187 = new Question(11, "Biển nào cấm ô tô tải?", true, "a2_187.jpg", a2.getId(),
				set7.getId(), bb.getId());
		questionRepo.save(questionA2_187);

		Question questionA2_205 = new Question(12, "Biển nào dưới đây cấm ô tô quay đầu và rẽ phải?", true,
				"a2_205.jpg", a2.getId(), set7.getId(), bb.getId());
		questionRepo.save(questionA2_205);

		Question questionA2_223 = new Question(13, "Biển số 2 có ý nghĩa như thế nào?", true, "a2_223.jpg", a2.getId(),
				set7.getId(), bb.getId());
		questionRepo.save(questionA2_223);

		Question questionA2_241 = new Question(14,
				"Gặp biển này, xe ô tô sơ mi rơ moóc có chiều dài toàn bộ kể cả xe, moóc và hàng lớn hơn trị số ghi trên biển có được phép đi vào không?",
				true, "a2_241.jpg", a2.getId(), set7.getId(), bb.getId());
		questionRepo.save(questionA2_241);

		Question questionA2_259 = new Question(15,
				"Biển nào báo hiệu đường sắt giao nhau với đường bộ không có rào chắn?", true, "a2_259.jpg", a2.getId(),
				set7.getId(), bb.getId());
		questionRepo.save(questionA2_259);

		Question questionA2_277 = new Question(16, "Biển nào báo hiệu “Đường đôi”?", true, "a2_277.jpg", a2.getId(),
				set7.getId(), bb.getId());
		questionRepo.save(questionA2_277);

		Question questionA2_295 = new Question(17,
				"Biển nào dưới đây báo hiệu đoạn đường hay xảy ra ùn tắc giao thông?", true, "a2_295.jpg", a2.getId(),
				set7.getId(), bb.getId());
		questionRepo.save(questionA2_295);

		Question questionA2_313 = new Question(18, "Biển nào báo hiệu “Đường dành cho xe ô tô”?", true, "a2_313.jpg",
				a2.getId(), set7.getId(), bb.getId());
		questionRepo.save(questionA2_313);

		Question questionA2_331 = new Question(19, "Biển số 3 có ý nghĩa gì?", true, "a2_331.jpg", a2.getId(),
				set7.getId(), bb.getId());
		questionRepo.save(questionA2_331);

		Question questionA2_349 = new Question(20, "Biển nào báo hiệu kết thúc đường cao tốc?", true, "a2_349.jpg",
				a2.getId(), set7.getId(), bb.getId());
		questionRepo.save(questionA2_349);

		Question questionA2_367 = new Question(21, "Vạch dưới đây có ý nghĩa gì?", true, "a2_367.jpg", a2.getId(),
				set7.getId(), bb.getId());
		questionRepo.save(questionA2_367);

		Question questionA2_385 = new Question(22, "Xe nào đỗ vi phạm quy tắc giao thông?", true, "a2_385.jpg",
				a2.getId(), set7.getId(), sh.getId());
		questionRepo.save(questionA2_385);

		Question questionA2_403 = new Question(23, "Thứ tự các xe đi như thế nào là đúng quy tắc giao thông?", true,
				"a2_403.jpg", a2.getId(), set7.getId(), sh.getId());
		questionRepo.save(questionA2_403);

		Question questionA2_421 = new Question(24,
				"Theo tín hiệu đèn của xe cơ giới, xe nào vi phạm quy tắc giao thông?", true, "a2_421.jpg", a2.getId(),
				set7.getId(), sh.getId());
		questionRepo.save(questionA2_421);

		Question questionA2_439 = new Question(25, "Các xe đi theo thứ tự nào là đúng quy tắc giao thông đường bộ?",
				true, "a2_439.jpg", a2.getId(), set7.getId(), sh.getId());
		questionRepo.save(questionA2_439);

		Question questionA2_8 = new Question(1,
				"Khái niệm “phương tiện giao thông cơ giới đường bộ” được hiểu thế nào là đúng?", true, "", a2.getId(),
				set8.getId(), kn.getId());
		questionRepo.save(questionA2_8);

		Question questionA2_26 = new Question(2,
				"Hành vi điều khiển xe cơ giới chạy quá tốc độ quy định, giành đường, vượt ẩu có bị nghiêm cấm hay không?",
				true, "", a2.getId(), set8.getId(), kn.getId());
		questionRepo.save(questionA2_26);

		Question questionA2_44 = new Question(3,
				"Khi xe đã kéo 1 xe hoặc xe đã kéo 1 rơ moóc, bạn có được phép kéo thêm xe (kể cả xe thô sơ) hoặc rơ moóc thứ hai hay không?",
				true, "", a2.getId(), set8.getId(), kn.getId());
		questionRepo.save(questionA2_44);

		Question questionA2_62 = new Question(4,
				"Biển báo hiệu hình chữ nhật hoặc hình vuông hoặc hình mũi tên nền xanh lam là loại biển gì dưới đây?",
				true, "a2_62.jpg", a2.getId(), set8.getId(), kn.getId());
		questionRepo.save(questionA2_62);

		Question questionA2_80 = new Question(5,
				"Tại nơi đường giao nhau, người lái xe đang đi trên đường không ưu tiên phải nhường đường như thế nào là đúng quy tắc giao thông?",
				true, "", a2.getId(), set8.getId(), kn.getId());
		questionRepo.save(questionA2_80);

		Question questionA2_98 = new Question(6, "Người lái xe mô tô xử lý như thế nào khi cho xe mô tô phía sau vượt?",
				true, "", a2.getId(), set8.getId(), kn.getId());
		questionRepo.save(questionA2_98);

		Question questionA2_116 = new Question(7,
				"Khi tham gia giao thông trên đoạn đường không có biển báo “Cự ly tối thiểu giữa hai xe”, với điều kiện mặt đường khô ráo, xe cơ giới đang chạy với tốc độ từ trên 100 km/h đến 120 km/h, người lái xe phải giữ khoảng cách an toàn với xe đang chạy liền trước tối thiểu là bao nhiêu?",
				true, "", a2.getId(), set8.getId(), kn.getId());
		questionRepo.save(questionA2_116);

		Question questionA2_134 = new Question(8,
				"Để báo hiệu cho xe phía trước biết xe mô tô của bạn muốn vượt, bạn phải có tín hiệu như thế nào dưới đây?",
				true, "", a2.getId(), set8.getId(), kn.getId());
		questionRepo.save(questionA2_134);

		Question questionA2_152 = new Question(9,
				"Người có văn hóa giao thông khi điều khiển xe cơ giới tham gia giao thông đường bộ phải đảm bảo các điều kiện gì dưới đây?",
				true, "", a2.getId(), set8.getId(), vh.getId());
		questionRepo.save(questionA2_152);

		Question questionA2_170 = new Question(10,
				"Khi lái xe ô tô trên mặt đường có nhiều “ổ gà”, người lái xe phải thực hiện thao tác như thế nào để đảm bảo an toàn?",
				true, "", a2.getId(), set8.getId(), kt.getId());
		questionRepo.save(questionA2_170);

		Question questionA2_188 = new Question(11, "Biển nào cấm máy kéo?", true, "a2_188.jpg", a2.getId(),
				set8.getId(), bb.getId());
		questionRepo.save(questionA2_188);

		Question questionA2_206 = new Question(12, "Biển nào cấm xe taxi mà không cấm các phương tiện khác?", true,
				"a2_206.jpg", a2.getId(), set8.getId(), bb.getId());
		questionRepo.save(questionA2_206);

		Question questionA2_224 = new Question(13, "Biển số 3 có ý nghĩa như thế nào?", true, "a2_224.jpg", a2.getId(),
				set8.getId(), bb.getId());
		questionRepo.save(questionA2_224);

		Question questionA2_242 = new Question(14,
				"Xe ô tô chở hàng vượt quá phía trước và sau thùng xe, mỗi phía quá 10% chiều dài toàn bộ thân xe, tổng chiều dài xe (cả hàng) từ trước đến sau nhỏ hơn trị số ghi trên biển thì có được phép đi vào không?",
				true, "a2_242.jpg", a2.getId(), set8.getId(), bb.getId());
		questionRepo.save(questionA2_242);

		Question questionA2_260 = new Question(15,
				"Biển nào báo hiệu sắp đến chỗ giao nhau giữa đường bộ và đường sắt?", true, "a2_260.jpg", a2.getId(),
				set8.getId(), bb.getId());
		questionRepo.save(questionA2_260);

		Question questionA2_278 = new Question(16, "Biển nào báo hiệu “Đường đôi”?", true, "a2_278.jpg", a2.getId(),
				set8.getId(), bb.getId());
		questionRepo.save(questionA2_278);

		Question questionA2_296 = new Question(17,
				"Gặp biển nào người tham gia giao thông phải đi chậm và thận trọng đề phòng khả năng xuất hiện và di chuyển bất ngờ của trẻ em trên mặt đường?",
				true, "a2_296.jpg", a2.getId(), set8.getId(), bb.getId());
		questionRepo.save(questionA2_296);

		Question questionA2_314 = new Question(18, "Biển nào báo hiệu “Hết đoạn đường dành cho xe ô tô”?", true,
				"a2_314.jpg", a2.getId(), set8.getId(), bb.getId());
		questionRepo.save(questionA2_314);

		Question questionA2_332 = new Question(19,
				"Biển nào báo hiệu “Đường phía trước có làn đường dành cho ô tô khách”?", true, "a2_332.jpg",
				a2.getId(), set8.getId(), bb.getId());
		questionRepo.save(questionA2_332);

		Question questionA2_350 = new Question(20, "Biển này có ý nghĩa gì?", true, "a2_350.jpg", a2.getId(),
				set8.getId(), bb.getId());
		questionRepo.save(questionA2_350);

		Question questionA2_368 = new Question(21, "Thứ tự đi như thế nào là đúng quy tắc giao thông?", true,
				"a2_368.jpg", a2.getId(), set8.getId(), sh.getId());
		questionRepo.save(questionA2_368);

		Question questionA2_386 = new Question(22, "Xe nào đỗ vi phạm quy tắc giao thông?", true, "a2_386.jpg",
				a2.getId(), set8.getId(), sh.getId());
		questionRepo.save(questionA2_386);

		Question questionA2_404 = new Question(23, "Những hướng nào ô tô tải được phép đi?", true, "a2_404.jpg",
				a2.getId(), set8.getId(), sh.getId());
		questionRepo.save(questionA2_404);

		Question questionA2_422 = new Question(24, "Các xe đi theo hướng mũi tên, xe nào vi phạm quy tắc giao thông?",
				true, "a2_422.jpg", a2.getId(), set8.getId(), sh.getId());
		questionRepo.save(questionA2_422);

		Question questionA2_440 = new Question(25, "Các xe đi theo thứ tự nào là đúng quy tắc giao thông đường bộ?",
				true, "a2_440.jpg", a2.getId(), set8.getId(), sh.getId());
		questionRepo.save(questionA2_440);

		Question questionA2_9 = new Question(1,
				"Khái niệm “phương tiện giao thông thô sơ đường bộ” được hiểu thế nào là đúng?", true, "", a2.getId(),
				set9.getId(), kn.getId());
		questionRepo.save(questionA2_9);

		Question questionA2_27 = new Question(2,
				"Khi lái xe trên đường, người lái xe cần quan sát và bảo đảm tốc độ phương tiện như thế nào?", true, "",
				a2.getId(), set9.getId(), kn.getId());
		questionRepo.save(questionA2_27);

		Question questionA2_45 = new Question(3,
				"Người điều khiển xe mô tô hai bánh, ba bánh, xe gắn máy có được phép sử dụng xe để kéo hoặc đẩy các phương tiện khác khi tham gia giao thông không?",
				true, "", a2.getId(), set9.getId(), kn.getId());
		questionRepo.save(questionA2_45);

		Question questionA2_63 = new Question(4,
				"Việc sát hạch cấp giấy phép lái xe ô tô phải thực hiện ở đâu và như thế nào?", true, "", a2.getId(),
				set9.getId(), kn.getId());
		questionRepo.save(questionA2_63);

		Question questionA2_81 = new Question(5,
				"Tại nơi đường giao nhau không có báo hiệu đi theo vòng xuyến, người điều khiển phương tiện phải nhường đường như thế nào là đúng quy tắc giao thông?",
				true, "", a2.getId(), set9.getId(), kn.getId());
		questionRepo.save(questionA2_81);

		Question questionA2_99 = new Question(6,
				"Trong các trường hợp dưới đây, để đảm bảo an toàn khi tham gia giao thông, người lái xe mô tô cần thực hiện như thế nào?",
				true, "", a2.getId(), set9.getId(), kn.getId());
		questionRepo.save(questionA2_99);

		Question questionA2_117 = new Question(7,
				"Khi điều khiển xe chạy với tốc độ dưới 60 km/h, để đảm bảo khoảng cách an toàn giữa hai xe, người lái xe phải điều khiển xe như thế nào?",
				true, "", a2.getId(), set9.getId(), kn.getId());
		questionRepo.save(questionA2_117);

		Question questionA2_135 = new Question(8,
				"Khi người lái xe ô tô dừng, đỗ sát theo lề đường, hè phố phía bên phải theo chiều đi của mình, bánh xe gần nhất không được cách xa lề đường, hè phố quá bao nhiêu mét trong các trường hợp dưới đây và không gây cản trở, nguy hiểm cho giao thông?",
				true, "", a2.getId(), set9.getId(), kn.getId());
		questionRepo.save(questionA2_135);

		Question questionA2_153 = new Question(9,
				"Khi xảy ra tai nạn giao thông, người lái xe và người có mặt tại hiện trường vụ tai nạn phải thực hiện các công việc gì dưới đây?",
				true, "", a2.getId(), set9.getId(), vh.getId());
		questionRepo.save(questionA2_153);

		Question questionA2_171 = new Question(10,
				"Khi đi từ đường nhánh ra đường chính, người lái xe phải xử lý như nào là đúng quy tắc giao thông?",
				true, "", a2.getId(), set9.getId(), kt.getId());
		questionRepo.save(questionA2_171);

		Question questionA2_189 = new Question(11, "Biển nào báo hiệu cấm xe mô tô ba bánh đi vào?", true, "a2_189.jpg",
				a2.getId(), set9.getId(), bb.getId());
		questionRepo.save(questionA2_189);

		Question questionA2_207 = new Question(12, "Biển nào cho phép xe rẽ trái?", true, "a2_207.jpg", a2.getId(),
				set9.getId(), bb.getId());
		questionRepo.save(questionA2_207);

		Question questionA2_225 = new Question(13, "Biển nào cấm máy kéo kéo theo rơ moóc?", true, "a2_225.jpg",
				a2.getId(), set9.getId(), bb.getId());
		questionRepo.save(questionA2_225);

		Question questionA2_243 = new Question(14, "Biển này có ý nghĩa gì?", true, "a2_243.jpg", a2.getId(),
				set9.getId(), bb.getId());
		questionRepo.save(questionA2_243);

		Question questionA2_261 = new Question(15, "Biển nào báo hiệu “Của chui”?", true, "a2_261.jpg", a2.getId(),
				set9.getId(), bb.getId());
		questionRepo.save(questionA2_261);

		Question questionA2_279 = new Question(16, "Biển nào báo hiệu “Kết thúc đường đôi”?", true, "a2_279.jpg",
				a2.getId(), set9.getId(), bb.getId());
		questionRepo.save(questionA2_279);

		Question questionA2_297 = new Question(17, "Biển nào chỉ dẫn nơi bắt đầu đoạn đường dành cho người đi bộ?",
				true, "a2_297.jpg", a2.getId(), set9.getId(), bb.getId());
		questionRepo.save(questionA2_297);

		Question questionA2_315 = new Question(18,
				"Khi gặp biển nào thì các phương tiện không được đi vào, trừ ô tô và mô tô?", true, "a2_315.jpg",
				a2.getId(), set9.getId(), bb.getId());
		questionRepo.save(questionA2_315);

		Question questionA2_333 = new Question(19, "Biển nào báo hiệu “Rẽ ra đường có làn đường dành cho ô tô khách”?",
				true, "a2_333.jpg", a2.getId(), set9.getId(), bb.getId());
		questionRepo.save(questionA2_333);

		Question questionA2_351 = new Question(20, "Biển này có ý nghĩa gì?", true, "a2_351.jpg", a2.getId(),
				set9.getId(), bb.getId());
		questionRepo.save(questionA2_351);

		Question questionA2_369 = new Question(21, "Thứ tự các xe đi như thế nào là đúng quy tắc giao thông?", true,
				"a2_369.jpg", a2.getId(), set9.getId(), sh.getId());
		questionRepo.save(questionA2_369);

		Question questionA2_387 = new Question(22,
				"Xe tải kéo mô tô ba bánh như hình này có đúng quy tắc giao thông không?", true, "a2_387.jpg",
				a2.getId(), set9.getId(), sh.getId());
		questionRepo.save(questionA2_387);

		Question questionA2_405 = new Question(23, "Theo hướng mũi tên, thứ tự các xe đi như thế nào là đúng?", true,
				"a2_405.jpg", a2.getId(), set9.getId(), sh.getId());
		questionRepo.save(questionA2_405);

		Question questionA2_423 = new Question(24, "Các xe đi theo hướng mũi tên, xe nào vi phạm quy tắc giao thông?",
				true, "a2_423.jpg", a2.getId(), set9.getId(), sh.getId());
		questionRepo.save(questionA2_423);

		Question questionA2_441 = new Question(25, "Xe nào phải nhường đường trong trường hợp này?", true, "a2_441.jpg",
				a2.getId(), set9.getId(), sh.getId());
		questionRepo.save(questionA2_441);

		Question questionA2_10 = new Question(1, "“Phương tiện tham gia giao thông đường bộ” gồm những loại nào?", true,
				"", a2.getId(), set10.getId(), kn.getId());
		questionRepo.save(questionA2_10);

		Question questionA2_28 = new Question(2,
				"Phương tiện giao thông đường bộ di chuyển với tốc độ thấp hơn phải đi như thế nào?", true, "",
				a2.getId(), set10.getId(), kn.getId());
		questionRepo.save(questionA2_28);

		Question questionA2_46 = new Question(3,
				"Khi điều khiển xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy, những hành vi buông cả hai tay; sử dụng xe để kéo, đẩy xe khác, vật khác, sử dụng chân chống của xe quệt xuống đường khi xe đang chạy có được phép hay không?",
				true, "", a2.getId(), set10.getId(), kn.getId());
		questionRepo.save(questionA2_46);

		Question questionA2_64 = new Question(4,
				"Khi điều khiển xe chạy trên đường, người lái xe phải mang theo các loại giấy tờ gì?", true, "",
				a2.getId(), set10.getId(), kn.getId());
		questionRepo.save(questionA2_64);

		Question questionA2_82 = new Question(5,
				"Trên đoạn đường bộ giao nhau cùng lúc với đường sắt, cầu đường bộ đi chung với đường sắt thì loại phương tiện nào được quyền ưu tiên đi trước?",
				true, "", a2.getId(), set10.getId(), kn.getId());
		questionRepo.save(questionA2_82);

		Question questionA2_100 = new Question(6,
				"Đường bộ trong khu vực đông dân cư gồm những đoạn đường nào dưới đây?", true, "", a2.getId(),
				set10.getId(), kn.getId());
		questionRepo.save(questionA2_100);

		Question questionA2_118 = new Question(7,
				"Người lái xe phải giảm tốc độ thấp hơn tốc độ tối đa cho phép đến mức cần thiết, chú ý quan sát và chuẩn bị sẵn sàng những tình huống có thể xảy ra để phòng ngừa tai nạn trong các trường hợp nào dưới đây?",
				true, "", a2.getId(), set10.getId(), kn.getId());
		questionRepo.save(questionA2_118);

		Question questionA2_136 = new Question(8,
				"Khi dừng, đỗ xe trên đường phố hẹp, người lái xe ô tô phải dừng, đỗ xe ở vị trí cách xe ô tô đang đổ bên kia đường khoảng cách tối thiểu là bao nhiêu mét trong các trường hợp dưới đây để đảm bảo an toàn giao thông?",
				true, "", a2.getId(), set10.getId(), kn.getId());
		questionRepo.save(questionA2_136);

		Question questionA2_154 = new Question(9,
				"Khi xảy ra tai nạn giao thông, có người bị thương nghiêm trọng, người lái xe và người có mặt tại hiện trường vụ tai nạn phải thực hiện các công việc gì dưới đây?",
				true, "", a2.getId(), set10.getId(), vh.getId());
		questionRepo.save(questionA2_154);

		Question questionA2_172 = new Question(10,
				"Khi đang lái xe mô tô và ô tô, nếu có nhu cầu sử dụng điện thoại để nhắn tin hoặc gọi điện, người lái xe phải thực hiện như thế nào trong các tình huống nêu dưới đây?",
				true, "", a2.getId(), set10.getId(), kt.getId());
		questionRepo.save(questionA2_172);

		Question questionA2_190 = new Question(11, "Biển nào dưới đây xe gắn máy được phép đi vào?", true, "a2_190.jpg",
				a2.getId(), set10.getId(), bb.getId());
		questionRepo.save(questionA2_190);

		Question questionA2_208 = new Question(12, "Biển nào xe quay đầu không bị cấm?", true, "a2_208.jpg", a2.getId(),
				set10.getId(), bb.getId());
		questionRepo.save(questionA2_208);

		Question questionA2_226 = new Question(13, "Khi gặp biển số 1, xe ô tô tải có được đi vào không?", true,
				"a2_226.jpg", a2.getId(), set10.getId(), bb.getId());
		questionRepo.save(questionA2_226);

		Question questionA2_244 = new Question(14, "Biển này có ý nghĩa gì?", true, "a2_244.jpg", a2.getId(),
				set10.getId(), bb.getId());
		questionRepo.save(questionA2_244);

		Question questionA2_262 = new Question(15, "Hai biển này có ý nghĩa gì?", true, "a2_262.jpg", a2.getId(),
				set10.getId(), bb.getId());
		questionRepo.save(questionA2_262);

		Question questionA2_280 = new Question(16, "Biển nào báo hiệu “Giao nhau với đường hai chiều”?", true,
				"a2_280.jpg", a2.getId(), set10.getId(), bb.getId());
		questionRepo.save(questionA2_280);

		Question questionA2_298 = new Question(17,
				"Biển nào dưới đây báo hiệu gần đến đoạn đường thường có trẻ em đi ngang qua?", true, "a2_298.jpg",
				a2.getId(), set10.getId(), bb.getId());
		questionRepo.save(questionA2_298);

		Question questionA2_316 = new Question(18, "Biển này có ý nghĩa gì?", true, "a2_316.jpg", a2.getId(),
				set10.getId(), bb.getId());
		questionRepo.save(questionA2_316);

		Question questionA2_334 = new Question(19,
				"Biển nào đặt trên đường chính trước khi đến nơi đường giao nhau để rẽ vào đường cụt?", true,
				"a2_334.jpg", a2.getId(), set10.getId(), bb.getId());
		questionRepo.save(questionA2_334);

		Question questionA2_352 = new Question(20, "Ý nghĩa các biểu tượng ghi trên biển chỉ dẫn là gì?", true,
				"a2_352.jpg", a2.getId(), set10.getId(), bb.getId());
		questionRepo.save(questionA2_352);

		Question questionA2_370 = new Question(21, "Trường hợp này xe nào được quyền đi trước?", true, "a2_370.jpg",
				a2.getId(), set10.getId(), sh.getId());
		questionRepo.save(questionA2_370);

		Question questionA2_388 = new Question(22, "Theo hướng mũi tên, hướng nào xe không được phép đi?", true,
				"a2_388.jpg", a2.getId(), set10.getId(), sh.getId());
		questionRepo.save(questionA2_388);

		Question questionA2_406 = new Question(23, "Trong hình dưới đây, xe nào chấp hành đúng quy tắc giao thông?",
				true, "a2_406.jpg", a2.getId(), set10.getId(), sh.getId());
		questionRepo.save(questionA2_406);

		Question questionA2_424 = new Question(24, "Các xe đi theo hướng mũi tên, xe nào vi phạm quy tắc giao thông?",
				true, "a2_424.jpg", a2.getId(), set10.getId(), sh.getId());
		questionRepo.save(questionA2_424);

		Question questionA2_442 = new Question(25, "Bạn xử lý như thế nào trong trường hợp này?", true, "a2_442.jpg",
				a2.getId(), set10.getId(), sh.getId());
		questionRepo.save(questionA2_442);

		Question questionA2_11 = new Question(1, "“Người tham gia giao thông đường bộ” gồm những đối tượng nào?", true,
				"", a2.getId(), set11.getId(), kn.getId());
		questionRepo.save(questionA2_11);

		Question questionA2_29 = new Question(2,
				"Trên đường có nhiều làn đường, khi điều khiển phương tiện ở tốc độ chậm bạn phải đi ở làn đường nào?",
				true, "", a2.getId(), set11.getId(), kn.getId());
		questionRepo.save(questionA2_29);

		Question questionA2_47 = new Question(3,
				"Khi điều khiển xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy, những hành vi nào không được phép?",
				true, "", a2.getId(), set11.getId(), kn.getId());
		questionRepo.save(questionA2_47);

		Question questionA2_65 = new Question(4,
				"Khi sử dụng giấy phép lái xe đã khai báo mất để điều khiển phương tiện cơ giới đường bộ, ngoài việc bị thu hồi giấy phép lái xe, chịu trách nhiệm trước pháp luật, người lái xe không được cấp giấy phép lái xe trong thời gian bao nhiêu năm?",
				true, "", a2.getId(), set11.getId(), kn.getId());
		questionRepo.save(questionA2_65);

		Question questionA2_83 = new Question(5,
				"Tại nơi đường bộ giao nhau cùng lúc với đường sắt chỉ có đèn tín hiệu hoặc chuông báo hiệu, khi đèn tín hiệu màu đỏ đã bật sáng hoặc có tiếng chuông báo hiệu, người tham gia giao thông phải dừng lại ngay và giữ khoảng cách tối thiểu bao nhiêu mét tính từ ray gần nhất?",
				true, "", a2.getId(), set11.getId(), kn.getId());
		questionRepo.save(questionA2_83);

		Question questionA2_101 = new Question(6,
				"Tốc độ tối đa cho phép đối với xe máy chuyên dùng, xe gắn máy (kể cả xe máy điện) và các loại xe tương tự trên đường bộ (trừ đường cao tốc) không được vượt quá bao nhiêu km/h?",
				true, "", a2.getId(), set11.getId(), kn.getId());
		questionRepo.save(questionA2_101);

		Question questionA2_119 = new Question(7,
				"Người lái xe phải giảm tốc độ, đi sát về bên phải của phần đường xe chạy trong các trường hợp nào dưới đây?",
				true, "", a2.getId(), set11.getId(), kn.getId());
		questionRepo.save(questionA2_119);

		Question questionA2_137 = new Question(8,
				"Trên đường bộ, người lái xe ô tô có được phép dùng xe, đỗ xe song song với một xe khác đang dừng, đỗ hay không?",
				true, "", a2.getId(), set11.getId(), kn.getId());
		questionRepo.save(questionA2_137);

		Question questionA2_155 = new Question(9,
				"Khi sơ cứu ban đầu cho người bị tai nạn giao thông đường bộ không còn hô hấp, người lái xe và người có mặt tại hiện trường vụ tai nạn phải thực hiện các công việc gì dưới đây?",
				true, "", a2.getId(), set11.getId(), vh.getId());
		questionRepo.save(questionA2_155);

		Question questionA2_173 = new Question(10,
				"Những thói quen nào dưới đây khi điều khiển xe mô tô tay ga tham gia giao thông dễ gây tai nạn nguy hiểm?",
				true, "", a2.getId(), set11.getId(), kt.getId());
		questionRepo.save(questionA2_173);

		Question questionA2_191 = new Question(11, "Biển nào báo hiệu cấm xe mô tô hai bánh đi vào?", true,
				"a2_191.jpg", a2.getId(), set11.getId(), bb.getId());
		questionRepo.save(questionA2_191);

		Question questionA2_209 = new Question(12, "Biển nào xe được phép quay đầu nhưng không được rẽ trái?", true,
				"a2_209.jpg", a2.getId(), set11.getId(), bb.getId());
		questionRepo.save(questionA2_209);

		Question questionA2_227 = new Question(13, "Biển nào không có hiệu lực đối với ô tô tải không kéo moóc?", true,
				"a2_227.jpg", a2.getId(), set11.getId(), bb.getId());
		questionRepo.save(questionA2_227);

		Question questionA2_245 = new Question(14, "Biển nào là biển “Tốc độ tối đa cho phép về ban đêm”?", true,
				"a2_245.jpg", a2.getId(), set11.getId(), bb.getId());
		questionRepo.save(questionA2_245);

		Question questionA2_263 = new Question(15, "Biển nào báo hiệu “Nơi đường sắt giao vuông góc với đường bộ”?",
				true, "a2_263.jpg", a2.getId(), set11.getId(), bb.getId());
		questionRepo.save(questionA2_263);

		Question questionA2_281 = new Question(16, "Biển nào báo hiệu “Đường hai chiều”?", true, "a2_281.jpg",
				a2.getId(), set11.getId(), bb.getId());
		questionRepo.save(questionA2_281);

		Question questionA2_299 = new Question(17,
				"Gặp biển nào dưới đây người tham gia giao thông cần phải điều chỉnh tốc độ xe chạy cho thích hợp, đề phòng gió ngang thổi mạnh gây lật xe?",
				true, "a2_299.jpg", a2.getId(), set11.getId(), bb.getId());
		questionRepo.save(questionA2_299);

		Question questionA2_317 = new Question(18,
				"Biển nào (đặt trước ngã ba, ngã tư) cho phép xe được rẽ sang hướng khác?", true, "a2_317.jpg",
				a2.getId(), set11.getId(), bb.getId());
		questionRepo.save(questionA2_317);

		Question questionA2_335 = new Question(19, "Biển nào chỉ dẫn cho người đi bộ sử dụng cầu vượt qua đường?", true,
				"a2_335.jpg", a2.getId(), set11.getId(), bb.getId());
		questionRepo.save(questionA2_335);

		Question questionA2_353 = new Question(20, "Biển này có ý nghĩa gì?", true, "a2_353.jpg", a2.getId(),
				set11.getId(), bb.getId());
		questionRepo.save(questionA2_353);

		Question questionA2_371 = new Question(21, "Thứ tự các xe đi như thế nào là đúng quy tắc giao thông?", true,
				"a2_371.jpg", a2.getId(), set11.getId(), sh.getId());
		questionRepo.save(questionA2_371);

		Question questionA2_389 = new Question(22, "Theo hướng mũi tên, những hướng nào ô tô không được phép đi?", true,
				"a2_389.jpg", a2.getId(), set11.getId(), sh.getId());
		questionRepo.save(questionA2_389);

		Question questionA2_407 = new Question(23, "Theo hướng mũi tên, những hướng nào xe mô tô được phép đi?", true,
				"a2_407.jpg", a2.getId(), set11.getId(), sh.getId());
		questionRepo.save(questionA2_407);

		Question questionA2_425 = new Question(24, "Các xe đi theo hướng mũi tên, xe nào vi phạm quy tắc giao thông?",
				true, "a2_425.jpg", a2.getId(), set11.getId(), sh.getId());
		questionRepo.save(questionA2_425);

		Question questionA2_443 = new Question(25, "Xe nào được đi trước trong trường hợp này?", true, "a2_443.jpg",
				a2.getId(), set11.getId(), sh.getId());
		questionRepo.save(questionA2_443);

		Question questionA2_12 = new Question(1,
				"“Người điều khiển phương tiện tham gia giao thông đường bộ” gồm những đối tượng nào dưới đây?", true,
				"", a2.getId(), set12.getId(), kn.getId());
		questionRepo.save(questionA2_12);

		Question questionA2_30 = new Question(2,
				"Hành vi vượt xe tại các vị trí có tầm nhìn hạn chế, đường vòng, đầu dốc có bị nghiêm cấm hay không?",
				true, "", a2.getId(), set12.getId(), kn.getId());
		questionRepo.save(questionA2_30);

		Question questionA2_48 = new Question(3,
				"Người ngồi trên xe mô tô hai bánh, ba bánh, xe gắn máy khi tham gia giao thông có được mang, vác vật cồng kềnh hay không?",
				true, "", a2.getId(), set12.getId(), kn.getId());
		questionRepo.save(questionA2_48);

		Question questionA2_66 = new Question(4,
				"Khi gặp hiệu lệnh như dưới đây của cảnh sát giao thông thì người tham gia giao thông phải đi như thế nào là đúng quy tắc giao thông?",
				true, "a2_66.jpg", a2.getId(), set12.getId(), kn.getId());
		questionRepo.save(questionA2_66);

		Question questionA2_84 = new Question(5,
				"Những trường hợp nào ghi ở dưới đây không được đi vào đường cao tốc trừ người, phương tiện, thiết bị phục vụ cho việc quản lý, bảo trì đường cao tốc?",
				true, "", a2.getId(), set12.getId(), kn.getId());
		questionRepo.save(questionA2_84);

		Question questionA2_102 = new Question(6,
				"Trên đường bộ (trừ đường cao tốc) trong khu vực đông dân cư, đường đôi có dải phân cách giữa, xe mô tô hai bánh, ô tô chở người đến 30 chỗ tham gia giao thông với tốc độ tối đa cho phép là bao nhiêu?",
				true, "", a2.getId(), set12.getId(), kn.getId());
		questionRepo.save(questionA2_102);

		Question questionA2_120 = new Question(7,
				"Tại các điểm giao cắt giữa đường bộ và đường sắt quyền ưu tiên thuộc về loại phương tiện nào dưới đây?",
				true, "", a2.getId(), set12.getId(), kn.getId());
		questionRepo.save(questionA2_120);

		Question questionA2_138 = new Question(8,
				"Người điều khiển xe mô tô phải giảm tốc độ và hết sức thận trọng khi qua những đoạn đường nào dưới đây?",
				true, "", a2.getId(), set12.getId(), kn.getId());
		questionRepo.save(questionA2_138);

		Question questionA2_156 = new Question(9,
				"Hành vi bỏ trốn sau khi gây tai nạn để trốn tránh trách nhiệm hoặc khi có điều kiện mà cố ý không cứu giúp người bị tai nạn giao thông có bị nghiêm cấm hay không?",
				true, "", a2.getId(), set12.getId(), vh.getId());
		questionRepo.save(questionA2_156);

		Question questionA2_174 = new Question(10,
				"Khi điều khiển xe mô tô quay đầu người lái xe cần thực hiện như thế nào để đảm bảo an toàn?", true, "",
				a2.getId(), set12.getId(), kt.getId());
		questionRepo.save(questionA2_174);

		Question questionA2_192 = new Question(11, "Khi gặp biển nào thì xe mô tô hai bánh được đi vào?", true,
				"a2_192.jpg", a2.getId(), set12.getId(), bb.getId());
		questionRepo.save(questionA2_192);

		Question questionA2_210 = new Question(12, "Biển nào là biển “Cấm đi ngược chiều”?", true, "a2_210.jpg",
				a2.getId(), set12.getId(), bb.getId());
		questionRepo.save(questionA2_210);

		Question questionA2_228 = new Question(13, "Biển nào cấm máy kéo?", true, "a2_228.jpg", a2.getId(),
				set12.getId(), bb.getId());
		questionRepo.save(questionA2_228);

		Question questionA2_246 = new Question(14,
				"Biển báo nào báo hiệu bắt đầu đoạn đường vào phạm vi khu dân cư, các phương tiện tham gia giao thông phải tuân theo các quy định đi đường được áp dụng ở khu đông dân cư?",
				true, "a2_246.jpg", a2.getId(), set12.getId(), bb.getId());
		questionRepo.save(questionA2_246);

		Question questionA2_264 = new Question(15, "Các biển này có ý nghĩa gì?", true, "a2_264.jpg", a2.getId(),
				set12.getId(), bb.getId());
		questionRepo.save(questionA2_264);

		Question questionA2_282 = new Question(16, "Biển nào báo hiệu “Giao nhau với đường hai chiều”?", true,
				"a2_282.jpg", a2.getId(), set12.getId(), bb.getId());
		questionRepo.save(questionA2_282);

		Question questionA2_300 = new Question(17, "Biển nào sau đây là biển “Dốc xuống nguy hiểm”?", true,
				"a2_300.jpg", a2.getId(), set12.getId(), bb.getId());
		questionRepo.save(questionA2_300);

		Question questionA2_318 = new Question(18, "Biển nào báo hiệu “Hướng đi thẳng phải theo” ?", true, "a2_318.jpg",
				a2.getId(), set12.getId(), bb.getId());
		questionRepo.save(questionA2_318);

		Question questionA2_336 = new Question(19, "Biển nào chỉ dẫn cho người đi bộ sử dụng hầm chui qua đường?", true,
				"a2_336.jpg", a2.getId(), set12.getId(), bb.getId());
		questionRepo.save(questionA2_336);

		Question questionA2_354 = new Question(20,
				"Trên đường cao tốc, gặp biển nào thì người lái xe đi theo hướng bên trái để tránh chướng ngại vật?",
				true, "a2_354.jpg", a2.getId(), set12.getId(), bb.getId());
		questionRepo.save(questionA2_354);

		Question questionA2_372 = new Question(21, "Xe nào được quyền đi trước trong trường hợp này?", true,
				"a2_372.jpg", a2.getId(), set12.getId(), sh.getId());
		questionRepo.save(questionA2_372);

		Question questionA2_390 = new Question(22, "Xe nào vượt đúng quy tắc giao thông?", true, "a2_390.jpg",
				a2.getId(), set12.getId(), sh.getId());
		questionRepo.save(questionA2_390);

		Question questionA2_408 = new Question(23,
				"Trong trường hợp này, thứ tự xe đi như thế nào là đúng quy tắc giao thông?", true, "a2_408.jpg",
				a2.getId(), set12.getId(), sh.getId());
		questionRepo.save(questionA2_408);

		Question questionA2_426 = new Question(24,
				"Các xe đi theo hướng mũi tên, xe nào chấp hành đúng quy tắc giao thông?", true, "a2_426.jpg",
				a2.getId(), set12.getId(), sh.getId());
		questionRepo.save(questionA2_426);

		Question questionA2_444 = new Question(25, "Các xe đi theo thứ tự nào là đúng quy tắc giao thông đường bộ?",
				true, "a2_444.jpg", a2.getId(), set12.getId(), sh.getId());
		questionRepo.save(questionA2_444);

		Question questionA2_13 = new Question(1,
				"Khái niệm “người điều khiển giao thông” được hiểu như thế nào là đúng?", true, "", a2.getId(),
				set13.getId(), kn.getId());
		questionRepo.save(questionA2_13);

		Question questionA2_31 = new Question(2,
				"Khi lái xe trong khu đô thị và đồng dân cư trừ các khu vực có biển cấm sử dụng còi, người lái xe được sử dụng còi như thế nào trong các trường hợp dưới đây?",
				true, "", a2.getId(), set13.getId(), kn.getId());
		questionRepo.save(questionA2_31);

		Question questionA2_49 = new Question(3,
				"Người ngồi trên xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy khi tham gia giao thông có được bám, kéo hoặc đẩy các phương tiện khác không?",
				true, "", a2.getId(), set13.getId(), kn.getId());
		questionRepo.save(questionA2_49);

		Question questionA2_67 = new Question(4,
				"Khi gặp hiệu lệnh như dưới đây của cảnh sát giao thông thì người tham gia giao thông phải đi như thế nào là đúng quy tắc giao thông?",
				true, "a2_67.jpg", a2.getId(), set13.getId(), kn.getId());
		questionRepo.save(questionA2_67);

		Question questionA2_85 = new Question(5,
				"Người điều khiển phương tiện tham gia giao thông trong hầm đường bộ ngoài việc phải tuân thủ các quy tắc giao thông còn phải thực hiện những quy định nào dưới đây?",
				true, "", a2.getId(), set13.getId(), kn.getId());
		questionRepo.save(questionA2_85);

		Question questionA2_103 = new Question(6,
				"Trên đường bộ (trừ đường cao tốc) trong khu vực đông dân cư, đường hai chiều không có dải phân cách giữa, xe mô tô hai bánh, ô tô chở người đến 30 chỗ tham gia giao thông với tốc độ tối đa cho phép là bao nhiêu?",
				true, "", a2.getId(), set13.getId(), kn.getId());
		questionRepo.save(questionA2_103);

		Question questionA2_121 = new Question(7,
				"Các phương tiện tham gia giao thông đường bộ (kể cả những xe có quyền ưu tiên) đều phải dừng lại bên phải đường của mình và trước vạch “dừng xe” tại các điểm giao cắt giữa đường bộ và đường sắt khi có báo hiệu dùng nào dưới đây?",
				true, "", a2.getId(), set13.getId(), kn.getId());
		questionRepo.save(questionA2_121);

		Question questionA2_139 = new Question(8,
				"Khi gặp xe buýt đang dùng đón, trả khách, người điều khiển xe mô tô phải xử lý như thế nào dưới đây để đảm bảo an toàn giao thông?",
				true, "", a2.getId(), set13.getId(), kn.getId());
		questionRepo.save(questionA2_139);

		Question questionA2_157 = new Question(9,
				"Khi xảy ra tai nạn giao thông, những hành vi nào dưới đây bị nghiêm cấm?", true, "", a2.getId(),
				set13.getId(), vh.getId());
		questionRepo.save(questionA2_157);

		Question questionA2_175 = new Question(10,
				"Tay ga trên xe mô tô hai bánh có tác dụng gì trong các trường hợp dưới đây?", true, "", a2.getId(),
				set13.getId(), kt.getId());
		questionRepo.save(questionA2_175);

		Question questionA2_193 = new Question(11, "Biển nào cho phép ô tô con được vượt?", true, "a2_193.jpg",
				a2.getId(), set13.getId(), bb.getId());
		questionRepo.save(questionA2_193);

		Question questionA2_211 = new Question(12, "Biển nào dưới đây các phương tiện không được phép đi vào?", true,
				"a2_211.jpg", a2.getId(), set13.getId(), bb.getId());
		questionRepo.save(questionA2_211);

		Question questionA2_229 = new Question(13,
				"Khi gặp biển này, xe mô tô ba bánh có được phép rẽ trái hoặc rẽ phải hay không?", true, "a2_229.jpg",
				a2.getId(), set13.getId(), bb.getId());
		questionRepo.save(questionA2_229);

		Question questionA2_247 = new Question(14,
				"Biển nào báo hiệu hạn chế tốc độ của phương tiện không vượt quá trị số ghi trên biển?", true,
				"a2_247.jpg", a2.getId(), set13.getId(), bb.getId());
		questionRepo.save(questionA2_247);

		Question questionA2_265 = new Question(15, "Biển nào báo hiệu “Hết đoạn đường ưu tiên”?", true, "a2_265.jpg",
				a2.getId(), set13.getId(), bb.getId());
		questionRepo.save(questionA2_265);

		Question questionA2_283 = new Question(16, "Biển nào báo hiệu “Chú ý chướng ngại vật”?", true, "a2_283.jpg",
				a2.getId(), set13.getId(), bb.getId());
		questionRepo.save(questionA2_283);

		Question questionA2_301 = new Question(17, "Biển nào sau đây là biển “Dốc lên nguy hiểm”?", true, "a2_301.jpg",
				a2.getId(), set13.getId(), bb.getId());
		questionRepo.save(questionA2_301);

		Question questionA2_319 = new Question(18, "Biển nào báo hiệu “Đường một chiều”?", true, "a2_319.jpg",
				a2.getId(), set13.getId(), bb.getId());
		questionRepo.save(questionA2_319);

		Question questionA2_337 = new Question(19, "Biển nào báo hiệu “Nơi đỗ xe dành cho người khuyết tật”?", true,
				"a2_337.jpg", a2.getId(), set13.getId(), bb.getId());
		questionRepo.save(questionA2_337);

		Question questionA2_355 = new Question(20,
				"Trên đường cao tốc, gặp biển nào thì người lái xe đi theo hướng bên phải để tránh chướng ngại vật?",
				true, "a2_355.jpg", a2.getId(), set13.getId(), bb.getId());
		questionRepo.save(questionA2_355);

		Question questionA2_373 = new Question(21, "Xe nào phải nhường đường đi cuối cùng qua nơi giao nhau này?", true,
				"a2_373.jpg", a2.getId(), set13.getId(), sh.getId());
		questionRepo.save(questionA2_373);

		Question questionA2_391 = new Question(22,
				"Theo hướng mũi tên, gặp biển hướng đi phải theo đặt trước ngã tư, những hướng nào xe được phép đi?",
				true, "a2_391.jpg", a2.getId(), set13.getId(), sh.getId());
		questionRepo.save(questionA2_391);

		Question questionA2_409 = new Question(23,
				"Xe con quay đầu đi ngược lại như hình vẽ dưới có vị phạm quy tắc giao thông không?", true,
				"a2_409.jpg", a2.getId(), set13.getId(), sh.getId());
		questionRepo.save(questionA2_409);

		Question questionA2_427 = new Question(24, "Trong hình dưới, những xe nào vi phạm quy tắc giao thông?", true,
				"a2_427.jpg", a2.getId(), set13.getId(), sh.getId());
		questionRepo.save(questionA2_427);

		Question questionA2_445 = new Question(25, "Xe nào được đi trước trong trường hợp này?", true, "a2_445.jpg",
				a2.getId(), set13.getId(), sh.getId());
		questionRepo.save(questionA2_445);

		Question questionA2_14 = new Question(1,
				"Trong các khái niệm dưới đây khái niệm “dùng xe” được hiều như thế nào là đúng?", true, "", a2.getId(),
				set14.getId(), kn.getId());
		questionRepo.save(questionA2_14);

		Question questionA2_32 = new Question(2,
				"Người lái xe sử dụng đèn như thế nào khi lái xe trong khu đô thị và đông dân cư vào ban đêm?", true,
				"", a2.getId(), set14.getId(), kn.getId());
		questionRepo.save(questionA2_32);

		Question questionA2_50 = new Question(3,
				"Người ngồi trên xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy khi tham gia giao thông có được sử dụng ô khi trời mưa hay không ?",
				true, "", a2.getId(), set14.getId(), kn.getId());
		questionRepo.save(questionA2_50);

		Question questionA2_68 = new Question(4,
				"Trên đường giao thông, khi hiệu lệnh của người điều khiển giao thông trái với hiệu lệnh của đèn hoặc biển báo hiệu thì người tham gia giao thông phải chấp hành theo hiệu lệnh nào?",
				true, "", a2.getId(), set14.getId(), kn.getId());
		questionRepo.save(questionA2_68);

		Question questionA2_86 = new Question(5,
				"Xe quá tải trọng, quá khổ giới hạn tham gia giao thông cần tuân thủ quy định nào ghi ở dưới đây?",
				true, "", a2.getId(), set14.getId(), kn.getId());
		questionRepo.save(questionA2_86);

		Question questionA2_104 = new Question(6,
				"Trên đường bộ (trừ đường cao tốc) trong khu vực đông dân cư, đường hai chiều hoặc đường một chiều có một làn xe cơ giới, loại xe nào dưới đây được tham gia giao thông với tốc độ tối đa cho phép là 50 km/h?",
				true, "", a2.getId(), set14.getId(), kn.getId());
		questionRepo.save(questionA2_104);

		Question questionA2_122 = new Question(7,
				"Trên đoạn đường bộ giao nhau đồng mức với đường sắt, người không có nhiệm vụ có được tự ý mở chắn đường ngang khi chắn đã đóng hay không?",
				true, "", a2.getId(), set14.getId(), kn.getId());
		questionRepo.save(questionA2_122);

		Question questionA2_140 = new Question(8,
				"Người hành nghề lái xe khi thực hiện tốt việc rèn luyện, nâng cao trách nhiệm, đạo đức nghề nghiệp sẽ thu được kết quả như thế nào dưới đây?",
				true, "", a2.getId(), set14.getId(), kn.getId());
		questionRepo.save(questionA2_140);

		Question questionA2_158 = new Question(9,
				"Khi xảy ra tai nạn giao thông, những hành vi nào dưới đây bị nghiêm cấm?", true, "", a2.getId(),
				set14.getId(), vh.getId());
		questionRepo.save(questionA2_158);

		Question questionA2_176 = new Question(10,
				"Gương chiếu hậu của xe mô tô hai bánh, có tác dụng gì trong các trường hợp dưới đây?", true, "",
				a2.getId(), set14.getId(), kt.getId());
		questionRepo.save(questionA2_176);

		Question questionA2_194 = new Question(11, "Biển nào không cho phép ô tô con vượt?", true, "a2_194.jpg",
				a2.getId(), set14.getId(), bb.getId());
		questionRepo.save(questionA2_194);

		Question questionA2_212 = new Question(12, "Gặp biển nào người lái xe không được đỗ xe vào ngày chắn?", true,
				"a2_212.jpg", a2.getId(), set14.getId(), bb.getId());
		questionRepo.save(questionA2_212);

		Question questionA2_230 = new Question(13, "Biển này có hiệu lực đối với xe mô tô hai, ba bánh không?", true,
				"a2_230.jpg", a2.getId(), set14.getId(), bb.getId());
		questionRepo.save(questionA2_230);

		Question questionA2_248 = new Question(14, "Trong các biển báo dưới đây biển nào báo hiệu “Hết đường cao tốc?",
				true, "a2_248.jpg", a2.getId(), set14.getId(), bb.getId());
		questionRepo.save(questionA2_248);

		Question questionA2_266 = new Question(15,
				"Biển nào báo hiệu, chỉ dẫn xe đi trên đường này được quyền ưu tiên qua nơi giao nhau?", true,
				"a2_266.jpg", a2.getId(), set14.getId(), bb.getId());
		questionRepo.save(questionA2_266);

		Question questionA2_284 = new Question(16,
				"Biển nào chỉ dẫn cho người tham gia giao thông biết vị trí và khoảng cách có làn đường cứu nạn hay làn thoát xe khẩn cấp?",
				true, "a2_284.jpg", a2.getId(), set14.getId(), bb.getId());
		questionRepo.save(questionA2_284);

		Question questionA2_302 = new Question(17, "Biển báo này có ý nghĩa gì?", true, "a2_302.jpg", a2.getId(),
				set14.getId(), bb.getId());
		questionRepo.save(questionA2_302);

		Question questionA2_320 = new Question(18,
				"Trong các biển dưới đây biển nào là biển “Hết tốc độ tối đa cho phép”?", true, "a2_320.jpg",
				a2.getId(), set14.getId(), bb.getId());
		questionRepo.save(questionA2_320);

		Question questionA2_338 = new Question(19, "Gặp biển báo này, người lái xe phải đỗ xe như thế nào?", true,
				"a2_338.jpg", a2.getId(), set14.getId(), bb.getId());
		questionRepo.save(questionA2_338);

		Question questionA2_356 = new Question(20,
				"Trên đường cao tốc, gặp biển nào thì người lái xe đi được cả hai hướng (bên trái hoặc bên phải) để tránh chướng ngại vật?",
				true, "a2_356.jpg", a2.getId(), set14.getId(), bb.getId());
		questionRepo.save(questionA2_356);

		Question questionA2_374 = new Question(21, "Xe nào phải nhường đường là đúng quy tắc giao thông?", true,
				"a2_374.jpg", a2.getId(), set14.getId(), sh.getId());
		questionRepo.save(questionA2_374);

		Question questionA2_392 = new Question(22, "Xe nào được quyền đi trước trong trường hợp này?", true,
				"a2_392.jpg", a2.getId(), set14.getId(), sh.getId());
		questionRepo.save(questionA2_392);

		Question questionA2_410 = new Question(23, "Trong hình dưới, những xe nào vi phạm quy tắc giao thông?", true,
				"a2_410.jpg", a2.getId(), set14.getId(), sh.getId());
		questionRepo.save(questionA2_410);

		Question questionA2_428 = new Question(24, "Các xe đi theo thứ tự nào là đúng quy tắc giao thông đường bộ?",
				true, "a2_428.jpg", a2.getId(), set14.getId(), sh.getId());
		questionRepo.save(questionA2_428);

		Question questionA2_446 = new Question(25, "Xe nào dùng đúng theo quy tắc giao thông?", true, "a2_446.jpg",
				a2.getId(), set14.getId(), sh.getId());
		questionRepo.save(questionA2_446);

		Question questionA2_15 = new Question(1, "Khái niệm “đỗ xe” được hiểu như thế nào là đúng?", true, "",
				a2.getId(), set15.getId(), kn.getId());
		questionRepo.save(questionA2_15);

		Question questionA2_33 = new Question(2,
				"Hành vi lắp đặt, sử dụng còi, đèn không đúng thiết kế của nhà sản xuất đối với từng loại xe cơ giới có được phép hay không?",
				true, "", a2.getId(), set15.getId(), kn.getId());
		questionRepo.save(questionA2_33);

		Question questionA2_51 = new Question(3,
				"Người có giấy phép lái xe mô tô hạng A1 không được phép điều khiển loại xe nào dưới đây?", true, "",
				a2.getId(), set15.getId(), kn.getId());
		questionRepo.save(questionA2_51);

		Question questionA2_69 = new Question(4,
				"Tại nơi có biển báo hiệu cố định lại có báo hiệu tạm thời thì người tham gia giao thông phải chấp hành hiệu lệnh của báo hiệu nào?",
				true, "", a2.getId(), set15.getId(), kn.getId());
		questionRepo.save(questionA2_69);

		Question questionA2_87 = new Question(5,
				"Việc nối giữa xe kéo với xe được kéo trong trường hợp hệ thống hãm của xe được kéo không còn hiệu lực thì phải dùng cách nào?",
				true, "", a2.getId(), set15.getId(), kn.getId());
		questionRepo.save(questionA2_87);

		Question questionA2_105 = new Question(6,
				"Trên đường bộ (trừ đường cao tốc) trong khu vực đông dân cư, đường hai chiều hoặc đường một chiều có một làn xe cơ giới, loại xe nào dưới đây được tham gia giao thông với tốc độ tối đa cho phép là 50 km/h?",
				true, "", a2.getId(), set15.getId(), kn.getId());
		questionRepo.save(questionA2_105);

		Question questionA2_123 = new Question(7,
				"Tác dụng của mũ bảo hiểm đối với người ngồi trên xe mô tô hai bánh trong trường hợp xảy ra tai nạn giao thông là gì?",
				true, "", a2.getId(), set15.getId(), kn.getId());
		questionRepo.save(questionA2_123);

		Question questionA2_141 = new Question(8,
				"Người lái xe vận tải hàng hóa cần thực hiện những nhiệm vụ gì ghi ở dưới đây?", true, "", a2.getId(),
				set15.getId(), kn.getId());
		questionRepo.save(questionA2_141);

		Question questionA2_159 = new Question(9,
				"Trong đoạn đường hai chiều tại khu đông dân cư đang ùn tắc, người điều khiển xe mô tô hai bánh có văn hóa giao thông sẽ lựa chọn xử lý tình huống nào dưới đây?",
				true, "", a2.getId(), set15.getId(), vh.getId());
		questionRepo.save(questionA2_159);

		Question questionA2_177 = new Question(10,
				"Để đảm bảo an toàn khi tham gia giao thông, người lái xe lái xe mô tô hai bánh cần điều khiển tay ga như thế nào trong các trường hợp dưới đây?",
				true, "", a2.getId(), set15.getId(), kt.getId());
		questionRepo.save(questionA2_177);

		Question questionA2_195 = new Question(11, "Biển nào cấm ô tô tải vượt?", true, "a2_195.jpg", a2.getId(),
				set15.getId(), bb.getId());
		questionRepo.save(questionA2_195);

		Question questionA2_213 = new Question(12, "Gặp biển nào người lái xe không được đỗ xe vào ngày le?", true,
				"a2_213.jpg", a2.getId(), set15.getId(), bb.getId());
		questionRepo.save(questionA2_213);

		Question questionA2_231 = new Question(13, "Biển này có ý nghĩa gì?", true, "a2_231.jpg", a2.getId(),
				set15.getId(), bb.getId());
		questionRepo.save(questionA2_231);

		Question questionA2_249 = new Question(14, "Số 50 ghi trên biển báo dưới đây có ý nghĩa gì?", true,
				"a2_249.jpg", a2.getId(), set15.getId(), bb.getId());
		questionRepo.save(questionA2_249);

		Question questionA2_267 = new Question(15, "Biển nào báo hiệu “Giao nhau với đường không ưu tiên”?", true,
				"a2_267.jpg", a2.getId(), set15.getId(), bb.getId());
		questionRepo.save(questionA2_267);

		Question questionA2_285 = new Question(16,
				"Biển nào chỉ dẫn cho người tham gia giao thông biết vị trí và khoảng cách có làn đường cứu nạn hay làn thoát xe khẩn cấp?",
				true, "a2_285.jpg", a2.getId(), set15.getId(), bb.getId());
		questionRepo.save(questionA2_285);

		Question questionA2_303 = new Question(17, "Biển báo này có ý nghĩa gì?", true, "a2_303.jpg", a2.getId(),
				set15.getId(), bb.getId());
		questionRepo.save(questionA2_303);

		Question questionA2_321 = new Question(18,
				"Hiệu lực của biển “Tốc độ tối đa cho phép” hết tác dụng khi gặp biển nào dưới đây?", true,
				"a2_321.jpg", a2.getId(), set15.getId(), bb.getId());
		questionRepo.save(questionA2_321);

		Question questionA2_339 = new Question(19,
				"Gặp biển báo này, người tham gia giao thông phải xử lý như thế nào?", true, "a2_339.jpg", a2.getId(),
				set15.getId(), bb.getId());
		questionRepo.save(questionA2_339);

		Question questionA2_357 = new Question(20,
				"Trên đường cao tốc, gặp biển nào người lái xe phải chú ý đổi hướng đi khi sắp vào đường cong nguy hiểm?",
				true, "a2_357.jpg", a2.getId(), set15.getId(), bb.getId());
		questionRepo.save(questionA2_357);

		Question questionA2_375 = new Question(21, "Xe nào được quyền đi trước trong trường hợp này?", true,
				"a2_375.jpg", a2.getId(), set15.getId(), sh.getId());
		questionRepo.save(questionA2_375);

		Question questionA2_393 = new Question(22, "Thứ tự các xe đi như thế nào là đúng quy tắc giao thông?", true,
				"a2_393.jpg", a2.getId(), set15.getId(), sh.getId());
		questionRepo.save(questionA2_393);

		Question questionA2_411 = new Question(23,
				"Để điều khiển cho xe đi thẳng, người lái xe phải làm gì là đúng quy tắc giao thông?", true,
				"a2_411.jpg", a2.getId(), set15.getId(), sh.getId());
		questionRepo.save(questionA2_411);

		Question questionA2_429 = new Question(24, "Các xe đi theo thứ tự nào là đúng quy tắc giao thông đường bộ?",
				true, "a2_429.jpg", a2.getId(), set15.getId(), sh.getId());
		questionRepo.save(questionA2_429);

		Question questionA2_447 = new Question(25, "Các xe đi theo thứ tự nào là đúng quy tắc giao thông đường bộ?",
				true, "a2_447.jpg", a2.getId(), set15.getId(), sh.getId());
		questionRepo.save(questionA2_447);

		Question questionA2_16 = new Question(1, "Khái niệm “đường cao tốc” được hiểu như thế nào là đúng?", true, "",
				a2.getId(), set16.getId(), kn.getId());
		questionRepo.save(questionA2_16);

		Question questionA2_34 = new Question(2,
				"Trong trường hợp đặc biệt, để được lắp đặt, sử dụng còi, đèn không đúng với thiết kế của nhà sản xuất đối với từng loại xe cơ giới bạn phải đảm bảo yêu cầu nào dưới đây?",
				true, "", a2.getId(), set16.getId(), kn.getId());
		questionRepo.save(questionA2_34);

		Question questionA2_52 = new Question(3,
				"Hành vi sử dụng xe mô tô để kéo, đẩy xe mô tô khác bị hết xăng đến trạm mua xăng có được phép hay không?",
				true, "", a2.getId(), set16.getId(), kn.getId());
		questionRepo.save(questionA2_52);

		Question questionA2_70 = new Question(4,
				"Trên đường có nhiều làn đường cho xe đi cùng chiều được phân biệt bằng vạch kẻ phân làn đường, người điều khiển phương tiện phải cho xe đi như thế nào?",
				true, "", a2.getId(), set16.getId(), kn.getId());
		questionRepo.save(questionA2_70);

		Question questionA2_88 = new Question(5,
				"Người ngồi trên xe mô tô 2 bánh, xe gắn máy phải đội mũ bảo hiểm có cài quai đúng quy cách khi nào?",
				true, "", a2.getId(), set16.getId(), kn.getId());
		questionRepo.save(questionA2_88);

		Question questionA2_106 = new Question(6,
				"Trên đường bộ (trừ đường cao tốc) ngoài khu vực đông dân cư, đường đôi có dải phân cách giữa, loại xe nào dưới đây tham gia giao thông với tốc độ tối đa cho phép là 90 km/h?",
				true, "", a2.getId(), set16.getId(), kn.getId());
		questionRepo.save(questionA2_106);

		Question questionA2_124 = new Question(7,
				"Khi điều khiển phương tiện tham giao giao thông, những hành vi nào dưới đây bị nghiêm cấm?", true, "",
				a2.getId(), set16.getId(), kn.getId());
		questionRepo.save(questionA2_124);

		Question questionA2_142 = new Question(8,
				"Người lái xe kinh doanh vận tải cần thực hiện những công việc gì ghi ở dưới đây để thường xuyên rèn luyện nâng cao đạo đức nghề nghiệp?",
				true, "", a2.getId(), set16.getId(), kn.getId());
		questionRepo.save(questionA2_142);

		Question questionA2_160 = new Question(9,
				"Trên đường đang xảy ra ùn tắc những hành vi nào sau đây là thiếu văn hóa khi tham gia giao thông?",
				true, "", a2.getId(), set16.getId(), vh.getId());
		questionRepo.save(questionA2_160);

		Question questionA2_178 = new Question(10,
				"Kỹ thuật cơ bản để giữ thăng bằng khi điều khiển xe mô tô đi trên đường gồ ghề như thế nào trong các trường hợp dưới đây?",
				true, "", a2.getId(), set16.getId(), kt.getId());
		questionRepo.save(questionA2_178);

		Question questionA2_196 = new Question(11, "Biển nào cấm xe tải vượt?", true, "a2_196.jpg", a2.getId(),
				set16.getId(), bb.getId());
		questionRepo.save(questionA2_196);

		Question questionA2_214 = new Question(12, "Khi gặp biển nào xe ưu tiên theo luật định vẫn phải dùng lại?",
				true, "a2_214.jpg", a2.getId(), set16.getId(), bb.getId());
		questionRepo.save(questionA2_214);

		Question questionA2_232 = new Question(13, "Biển phụ đặt dưới biển cấm bóp còi có ý nghĩa gì?", true,
				"a2_232.jpg", a2.getId(), set16.getId(), bb.getId());
		questionRepo.save(questionA2_232);

		Question questionA2_250 = new Question(14, "Trong các biển dưới đây biển nào chỉ dẫn bắt đầu đường cao tốc?",
				true, "a2_250.jpg", a2.getId(), set16.getId(), bb.getId());
		questionRepo.save(questionA2_250);

		Question questionA2_268 = new Question(15, "Biển nào báo hiệu “Giao nhau với đường ưu tiên”?", true,
				"a2_268.jpg", a2.getId(), set16.getId(), bb.getId());
		questionRepo.save(questionA2_268);

		Question questionA2_286 = new Question(16, "Biển nào dưới đây là biển “Cầu hẹp”?", true, "a2_286.jpg",
				a2.getId(), set16.getId(), bb.getId());
		questionRepo.save(questionA2_286);

		Question questionA2_304 = new Question(17, "Biển báo này có ý nghĩa gì?", true, "a2_304.jpg", a2.getId(),
				set16.getId(), bb.getId());
		questionRepo.save(questionA2_304);

		Question questionA2_322 = new Question(18, "Trong các biển dưới đây biển nào là biển “Hết tốc độ tối thiểu” ?",
				true, "a2_322.jpg", a2.getId(), set16.getId(), bb.getId());
		questionRepo.save(questionA2_322);

		Question questionA2_340 = new Question(19,
				"Gặp biển báo dưới đây, người lái xe có bắt buộc phải chạy vòng theo đảo an toàn theo hướng mũi tên khi muốn chuyển hướng hay không?",
				true, "a2_340.jpg", a2.getId(), set16.getId(), bb.getId());
		questionRepo.save(questionA2_340);

		Question questionA2_358 = new Question(20,
				"Vạch mũi tên chỉ hướng trên mặt đường nào dưới đây cho phép xe chỉ được đi thẳng và rẽ phải?", true,
				"a2_358.jpg", a2.getId(), set16.getId(), bb.getId());
		questionRepo.save(questionA2_358);

		Question questionA2_376 = new Question(21, "Theo tín hiệu đèn, xe nào được phép đi?", true, "a2_376.jpg",
				a2.getId(), set16.getId(), sh.getId());
		questionRepo.save(questionA2_376);

		Question questionA2_394 = new Question(22, "Xe nào được quyền đi trước trong trường hợp này?", true,
				"a2_394.jpg", a2.getId(), set16.getId(), sh.getId());
		questionRepo.save(questionA2_394);

		Question questionA2_412 = new Question(23,
				"Người lái xe điều khiển xe chạy theo hướng nào là đúng quy tắc giao thông?", true, "a2_412.jpg",
				a2.getId(), set16.getId(), sh.getId());
		questionRepo.save(questionA2_412);

		Question questionA2_430 = new Question(24, "Xe nào phải dừng lại trong trường hợp này?", true, "a2_430.jpg",
				a2.getId(), set16.getId(), sh.getId());
		questionRepo.save(questionA2_430);

		Question questionA2_448 = new Question(25, "Để điều khiển xe rẽ trái, bạn chọn hướng đi nào dưới đây?", true,
				"a2_448.jpg", a2.getId(), set16.getId(), sh.getId());
		questionRepo.save(questionA2_448);

		Question questionA2_17 = new Question(1, "Hành vi nào dưới đây bị nghiêm cấm?", true, "", a2.getId(),
				set17.getId(), kn.getId());
		questionRepo.save(questionA2_17);

		Question questionA2_35 = new Question(2,
				"Việc sản xuất, mua bán, sử dụng biển số xe cơ giới, xe máy chuyên dùng được quy định như thế nào trong Luật Giao thông đường bộ?",
				true, "", a2.getId(), set17.getId(), kn.getId());
		questionRepo.save(questionA2_35);

		Question questionA2_53 = new Question(3,
				"Hành vi vận chuyển đồ vật cồng kềnh bằng xe mô tô, xe gắn máy khi tham gia giao thông có được phép hay không?",
				true, "", a2.getId(), set17.getId(), kn.getId());
		questionRepo.save(questionA2_53);

		Question questionA2_71 = new Question(4,
				"Trên đường một chiều có vạch kẻ phân làn đường, xe thô sơ và xe cơ giới phải đi như thế nào là đúng quy tắc giao thông?",
				true, "", a2.getId(), set17.getId(), kn.getId());
		questionRepo.save(questionA2_71);

		Question questionA2_89 = new Question(5,
				"Người điều khiển xe mô tô hai bánh, xe gắn máy được phép chở tối đa 2 người trong những trường hợp nào?",
				true, "", a2.getId(), set17.getId(), kn.getId());
		questionRepo.save(questionA2_89);

		Question questionA2_107 = new Question(6,
				"Trên đường bộ ngoài khu vực đông dân cư, đường đôi có dải phân cách giữa (trừ đường cao tốc), loại xe nào dưới đây tham gia giao thông với tốc độ tối đa cho phép là 80 km/h?",
				true, "", a2.getId(), set17.getId(), kn.getId());
		questionRepo.save(questionA2_107);

		Question questionA2_125 = new Question(7,
				"Người lái xe phải nhanh chóng giảm tốc độ, tránh hoặc dùng lại sát lề đường bên phải để nhường đường cho các xe nào nếu dưới đây?",
				true, "", a2.getId(), set17.getId(), kn.getId());
		questionRepo.save(questionA2_125);

		Question questionA2_143 = new Question(8,
				"Người lái xe và nhân viên phục vụ trên xe ô tô vận tải hành khách phải có những trách nhiệm gì theo quy định dưới đây?",
				true, "", a2.getId(), set17.getId(), kn.getId());
		questionRepo.save(questionA2_143);

		Question questionA2_161 = new Question(9,
				"Khi điều khiển xe mô tô tay ga xuống đường dốc dài, độ dốc cao, người lái xe cần thực hiện các thao tác nào dưới đây để đảm bảo an toàn?",
				true, "", a2.getId(), set17.getId(), kt.getId());
		questionRepo.save(questionA2_161);

		Question questionA2_179 = new Question(10,
				"Chủ phương tiện cơ giới đường bộ có được tự ý thay đổi màu son, nhãn hiệu hoặc các đặc tính kỹ thuật của phương tiện so với chứng nhận đăng ký xe hay không?",
				true, "", a2.getId(), set17.getId(), kt.getId());
		questionRepo.save(questionA2_179);

		Question questionA2_197 = new Question(11, "Biển nào xe ô tô con được phép vượt?", true, "a2_197.jpg",
				a2.getId(), set17.getId(), bb.getId());
		questionRepo.save(questionA2_197);

		Question questionA2_215 = new Question(12,
				"Biển nào cấm tất cả các loại xe cơ giới và thô sơ đi lại trên đường, trừ xe ưu tiên theo luật định (nếu đường vẫn cho xe chạy được)?",
				true, "a2_215.jpg", a2.getId(), set17.getId(), bb.getId());
		questionRepo.save(questionA2_215);

		Question questionA2_233 = new Question(13,
				"Chiều dài đoạn đường 500 m từ nơi đặt biển này, người lái xe có được phép bấm còi không?", true,
				"a2_233.jpg", a2.getId(), set17.getId(), bb.getId());
		questionRepo.save(questionA2_233);

		Question questionA2_251 = new Question(14, "Biển báo dưới đây có ý nghĩa gì?", true, "a2_251.jpg", a2.getId(),
				set17.getId(), bb.getId());
		questionRepo.save(questionA2_251);

		Question questionA2_269 = new Question(15, "Biển nào báo hiệu “Đường bị thu hẹp”?", true, "a2_269.jpg",
				a2.getId(), set17.getId(), bb.getId());
		questionRepo.save(questionA2_269);

		Question questionA2_287 = new Question(16, "Biển nào dưới đây là biển “Cầu quay – cầu cất”?", true,
				"a2_287.jpg", a2.getId(), set17.getId(), bb.getId());
		questionRepo.save(questionA2_287);

		Question questionA2_305 = new Question(17, "Biển báo này có ý nghĩa gì?", true, "a2_305.jpg", a2.getId(),
				set17.getId(), bb.getId());
		questionRepo.save(questionA2_305);

		Question questionA2_323 = new Question(18, "Biển nào dưới đây báo hiệu hết cấm vượt?", true, "a2_323.jpg",
				a2.getId(), set17.getId(), bb.getId());
		questionRepo.save(questionA2_323);

		Question questionA2_341 = new Question(19,
				"Biển nào dưới đây chỉ dẫn địa giới hành chính giữa các thành phố, tỉnh, huyện?", true, "a2_341.jpg",
				a2.getId(), set17.getId(), bb.getId());
		questionRepo.save(questionA2_341);

		Question questionA2_359 = new Question(20,
				"Vạch kẻ đường nào dưới đây là vạch phân chia các làn xe cùng chiều?", true, "a2_359.jpg", a2.getId(),
				set17.getId(), bb.getId());
		questionRepo.save(questionA2_359);

		Question questionA2_377 = new Question(21,
				"Theo tín hiệu đèn, xe nào được quyền đi là đúng quy tắc giao thông?", true, "a2_377.jpg", a2.getId(),
				set17.getId(), sh.getId());
		questionRepo.save(questionA2_377);

		Question questionA2_395 = new Question(22,
				"Ô tô con đi theo chiều mũi tên có vi phạm quy tắc giao thông không?", true, "a2_395.jpg", a2.getId(),
				set17.getId(), sh.getId());
		questionRepo.save(questionA2_395);

		Question questionA2_413 = new Question(23,
				"Người lái xe điều khiển xe rẽ trái như thế nào là đúng quy tắc giao thông?", true, "a2_413.jpg",
				a2.getId(), set17.getId(), sh.getId());
		questionRepo.save(questionA2_413);

		Question questionA2_431 = new Question(24, "Xe của bạn được đi theo hướng nào trong trường hợp này?", true,
				"a2_431.jpg", a2.getId(), set17.getId(), sh.getId());
		questionRepo.save(questionA2_431);

		Question questionA2_449 = new Question(25,
				"Xe của bạn đang di chuyển gần đến khu vực giao cắt với đường sắt, khi rào chắn đang dịch chuyển, bạn điều khiển xe như thế nào là đúng quy tắc giao thông?",
				true, "a2_449.jpg", a2.getId(), set17.getId(), sh.getId());
		questionRepo.save(questionA2_449);

		Question questionA2_18 = new Question(1,
				"Hành vi đua xe cơ giới, xe máy chuyên dùng không bảo đảm tiêu chuẩn an toàn kỹ thuật và bảo vệ môi trường vào tham gia giao thông đường bộ có bị nghiêm cấm hay không?",
				true, "", a2.getId(), set18.getId(), kn.getId());
		questionRepo.save(questionA2_18);

		Question questionA2_36 = new Question(2,
				"Người lái xe không được vượt xe khác khi gặp trường hợp nào ghi ở dưới đây?", true, "", a2.getId(),
				set18.getId(), kn.getId());
		questionRepo.save(questionA2_36);

		Question questionA2_54 = new Question(3, "Người đủ 16 tuổi được điều khiển các loại xe nào dưới đây?", true, "",
				a2.getId(), set18.getId(), kn.getId());
		questionRepo.save(questionA2_54);

		Question questionA2_72 = new Question(4,
				"Bạn đang lái xe trong khu vực đô thị từ 22 giờ đến 5 giờ sáng hôm sau và cần vượt một xe khác, bạn cần báo hiệu như thế nào để đảm bảo an toàn giao thông?",
				true, "", a2.getId(), set18.getId(), kn.getId());
		questionRepo.save(questionA2_72);

		Question questionA2_90 = new Question(5,
				"Người điều khiển xe mô tô hai bánh, xe gắn máy không được thực hiện những hành vi nào dưới đây?", true,
				"", a2.getId(), set18.getId(), kn.getId());
		questionRepo.save(questionA2_90);

		Question questionA2_108 = new Question(6,
				"Trên đường bộ ngoài khu vực đông dân cư, đường đôi có dải phân cách giữa (trừ đường cao tốc), loại xe nào dưới đây tham gia giao thông với tốc độ tối đa cho phép là 70 km/h?",
				true, "", a2.getId(), set18.getId(), kn.getId());
		questionRepo.save(questionA2_108);

		Question questionA2_126 = new Question(7,
				"Tại nơi đường giao nhau, người lái xe đang đi trên đường không ưu tiên phải xử lý như thế nào là đúng quy tắc giao thông?",
				true, "", a2.getId(), set18.getId(), kn.getId());
		questionRepo.save(questionA2_126);

		Question questionA2_144 = new Question(8, "Khái niệm về văn hóa giao thông được hiểu như thế nào là đúng?",
				true, "", a2.getId(), set18.getId(), vh.getId());
		questionRepo.save(questionA2_144);

		Question questionA2_162 = new Question(9,
				"Khi quay đầu xe, người lái xe cần phải quan sát và thực hiện các thao tác nào để đảm bảo an toàn giao thông?",
				true, "", a2.getId(), set18.getId(), kt.getId());
		questionRepo.save(questionA2_162);

		Question questionA2_180 = new Question(10,
				"Xe mô tô và xe ô tô tham gia giao thông trên đường bộ phải bắt buộc có đủ bộ phận giảm thanh không?",
				true, "", a2.getId(), set18.getId(), kt.getId());
		questionRepo.save(questionA2_180);

		Question questionA2_198 = new Question(11, "Biển nào cấm quay đầu xe?", true, "a2_198.jpg", a2.getId(),
				set18.getId(), bb.getId());
		questionRepo.save(questionA2_198);

		Question questionA2_216 = new Question(12, "Biển nào là biển “Cấm xe chở hàng nguy hiểm”?", true, "a2_216.jpg",
				a2.getId(), set18.getId(), bb.getId());
		questionRepo.save(questionA2_216);

		Question questionA2_234 = new Question(13, "Biển nào xe mô tô hai bánh được đi vào?", true, "a2_234.jpg",
				a2.getId(), set18.getId(), bb.getId());
		questionRepo.save(questionA2_234);

		Question questionA2_252 = new Question(14, "Gặp biển nào người lái xe phải nhường đường cho người đi bộ?", true,
				"a2_252.jpg", a2.getId(), set18.getId(), bb.getId());
		questionRepo.save(questionA2_252);

		Question questionA2_270 = new Question(15,
				"Khi gặp biển nào, người lái xe phải giảm tốc độ, chú ý xe đi ngược chiều, xe đi ở phía đường bị hẹp phải nhường đường cho xe đi ngược chiều?",
				true, "a2_270.jpg", a2.getId(), set18.getId(), bb.getId());
		questionRepo.save(questionA2_270);

		Question questionA2_288 = new Question(16, "Biển nào dưới đây là biển “Kè, vực sâu phía trước?", true,
				"a2_288.jpg", a2.getId(), set18.getId(), bb.getId());
		questionRepo.save(questionA2_288);

		Question questionA2_306 = new Question(17, "Biển báo dưới đây có ý nghĩa gì?", true, "a2_306.jpg", a2.getId(),
				set18.getId(), bb.getId());
		questionRepo.save(questionA2_306);

		Question questionA2_324 = new Question(18, "Trong các biển dưới đây biển nào là biển “Hết mọi lệnh cấm”?", true,
				"a2_324.jpg", a2.getId(), set18.getId(), bb.getId());
		questionRepo.save(questionA2_324);

		Question questionA2_342 = new Question(19, "Biển nào báo hiệu “Cầu vượt liên thông”?", true, "a2_342.jpg",
				a2.getId(), set18.getId(), bb.getId());
		questionRepo.save(questionA2_342);

		Question questionA2_360 = new Question(20,
				"Vạch kẻ đường nào dưới đây là vạch phân chia hai chiều xe chạy (vạch tim đường), xe không được lấn làn, không được đè lên vạch?",
				true, "a2_360.jpg", a2.getId(), set18.getId(), bb.getId());
		questionRepo.save(questionA2_360);

		Question questionA2_378 = new Question(21, "Trong trường hợp này xe nào được quyền đi trước?", true,
				"a2_378.jpg", a2.getId(), set18.getId(), sh.getId());
		questionRepo.save(questionA2_378);

		Question questionA2_396 = new Question(22, "Xe nào vi phạm quy tắc giao thông?", true, "a2_396.jpg", a2.getId(),
				set18.getId(), sh.getId());
		questionRepo.save(questionA2_396);

		Question questionA2_414 = new Question(23, "Trong hình dưới, những xe nào vi phạm quy tắc giao thông?", true,
				"a2_414.jpg", a2.getId(), set18.getId(), sh.getId());
		questionRepo.save(questionA2_414);

		Question questionA2_432 = new Question(24, "Xe của bạn được đi theo hướng nào trong trường hợp này?", true,
				"a2_432.jpg", a2.getId(), set18.getId(), sh.getId());
		questionRepo.save(questionA2_432);

		Question questionA2_450 = new Question(25,
				"Trong tình huống dưới đây, xe đầu kéo kéo rơ moóc (xe container) đang rẽ phải, xe con màu xanh và xe máy phía sau xe container đi như thế nào để đảm bảo an toàn?",
				true, "a2_450.jpg", a2.getId(), set18.getId(), sh.getId());
		questionRepo.save(questionA2_450);

	}

	private void LoadAnswer() {
		List<Question> questionList = questionRepo.findAll();
		ArrayList<String> idList = new ArrayList<String>();

		for (Question q : questionList) {
			idList.add(q.getId());
		}

		answerRepo.deleteAll();

		// Câu trả lời câu hỏi bằng A1
		String[] a1_answers_1_set1 = { "Bị nghiêm cấm", "Không bị nghiêm cấm",
				"Không bị nghiêm cấm, nếu có chất ma túy ở mức nhẹ, có thể điều khiển phương tiện tham gia giao thông." };
		Answer a1_answer_1_set1 = new Answer(a1_answers_1_set1, 0, idList.get(0));
		answerRepo.save(a1_answer_1_set1);

		String[] a1_answers_2_set1 = { "Được sử dụng", "Chỉ người ngồi sau được sử dụng", "Không được sử dụng",
				"Được sử dụng nếu không có áo mưa." };
		Answer a1_answer_2_set1 = new Answer(a1_answers_2_set1, 2, idList.get(1));
		answerRepo.save(a1_answer_2_set1);

		String[] a1_answers_3_set1 = { "Đường không ưu tiên", "Đường tỉnh lộ", "Đường quốc lộ", "Đường ưu tiên." };
		Answer a1_answer_3_set1 = new Answer(a1_answers_3_set1, 3, idList.get(2));
		answerRepo.save(a1_answer_3_set1);

		String[] a1_answers_4_set1 = {
				"Là người điều khiển phương tiện tham gia giao thông tại nơi thi công, nơi ùn tắc giao thông, ở bến phà, tại cầu đường bộ đi chung với đường sắt.",
				"Là cảnh sát giao thông, người được giao nhiệm vụ hướng dẫn giao thông tại nơi thi công, nơi ùn tắc giao thông, ở bến phà, tại cầu đường bộ đi chung với đường sắt",
				"Là người tham gia giao thông tại nơi thi công, nơi ùn tắt giao thông, ở bến phà, tại cầu đường bộ đi chung với đường sắt." };
		Answer a1_answer_4_set1 = new Answer(a1_answers_4_set1, 1, idList.get(3));
		answerRepo.save(a1_answer_4_set1);

		String[] a1_answers_5_set1 = { "Phải đảm bảo phụ tùng do đúng nhà sản xuất đó cung cấp",
				"Phải được chấp thuận của cơ quan có thẩm quyền",
				"Phải là xe đăng ký và hoạt động tại các khu vực có địa hình phức tạp." };
		Answer a1_answer_5_set1 = new Answer(a1_answers_5_set1, 1, idList.get(4));
		answerRepo.save(a1_answer_5_set1);

		String[] a1_answers_6_set1 = {
				"Người tham gia giao thông ở hướng đối diện cảnh sát giao thông được đi, các hướng khác cần phải dừng lại",
				"Người tham gia giao thông được rẽ phải theo chiều mũi tên màu xanh ở bục cảnh sát giao thông",
				"Người tham gia giao thông ở các hướng đều phải dừng lại trừ các xe đã ở trong khu vực giao nhau",
				"Người ở hướng đối diện cảnh sát giao thông phải dừng lại, các hướng khác được đi trong đó có bạn." };
		Answer a1_answer_6_set1 = new Answer(a1_answers_6_set1, 2, idList.get(5));
		answerRepo.save(a1_answer_6_set1);

		String[] a1_answers_7_set1 = {
				"Cho xe đi trên bất kỳ làn đường nào hoặc giữa 02 làn đường nếu không có xe phía trước; khi cần thiết phải chuyển làn đường, người lái xe phải quan sát xe phía trước để đảm bảo an toàn.",
				"Phải cho xe đi trong một làn đường và chỉ được chuyển làn đường ở những nơi cho phép; khi chuyển làn phải có tín hiệu báo trước và phải bảo đảm an toàn.",
				"Phải cho xe đi trong một làn đường, khi cần thiết phải chuyển làn đường, người lái xe phải quan sát xe phía trước để bảo đảm an toàn." };
		Answer a1_answer_7_set1 = new Answer(a1_answers_7_set1, 1, idList.get(6));
		answerRepo.save(a1_answer_7_set1);

		String[] a1_answers_8_set1 = { "Phải nhường đường cho xe đi đến từ bên phải.",
				"Xe báo hiệu xin đường trước xe đó được đi trước", "Phải nhường đường cho xe đi đến từ bên trái." };
		Answer a1_answer_8_set1 = new Answer(a1_answers_8_set1, 0, idList.get(7));
		answerRepo.save(a1_answer_8_set1);

		String[] a1_answers_9_set1 = {
				"Nếu đủ điều khiện an toàn, người lái xe phải giảm tốc độ, đi sát về bên phải của phần đường xe chạy cho đến khi xe sau đã vượt qua, không được gây trở ngại đối với xe xin vượt.",
				"Lái xe vào lề đường bên trái và giảm tốc độ để xe phía sau vượt qua, không được gây trở ngại đối với xe xin vượt",
				"Nếu đủ điều kiện an toàn, người lái xe phải tăng tốc độ, đi sát về bên phải của phần đường xe chạy cho đến khi xe sau đã vượt qua." };
		Answer a1_answer_9_set1 = new Answer(a1_answers_9_set1, 0, idList.get(8));
		answerRepo.save(a1_answer_9_set1);

		String[] a1_answers_10_set1 = { "Gặp biển báo nguy hiển trên đường.", "Gặp biển chỉ dần trên đường",
				"Gặp biển báo hết mọi lệnh cấm", "Gặp biển báo hết hạn chế tốc độ tối đa cho phép." };
		Answer a1_answer_10_set1 = new Answer(a1_answers_10_set1, 0, idList.get(9));
		answerRepo.save(a1_answer_10_set1);

		String[] a1_answers_11_set1 = { "5 mét", "3 mét", "4 mét" };
		Answer a1_answer_11_set1 = new Answer(a1_answers_11_set1, 0, idList.get(10));
		answerRepo.save(a1_answer_11_set1);

		String[] a1_answers_12_set1 = {
				"Quan sát biển báo hiệu để biết nơi được phép quay đầu; quan sát kỹ địa hình nơi chọn để quay đầu; lựa chọn quỹ đạo quay đầu xe cho thích hợp;  quay đầu xe với tốc độ thấp; thường xuyên báo tín hiệu để người, các phương tiện xung quanh được biết; nếu quay đầu xe ở nơi nguy hiểm thì đưa đầu xe về phía nguy hiểm, đưa đuôi xe về phía an toàn.",
				"Quan sát biển báo để biết nơi được phép quay đầu; quan sát kỹ địa hình nơi chọn để quay đầu; lựa chọn quỹ đạo quay đầu xe cho thích hợp;  quay đầu xe với tốc độ tối đa; thường xuyên báo tín hiệu để người, các phương tiện xung quanh được biết; nếu quay đầu xe ở nơi nguy hiểm thì đưa đuôi xe về phía nguy hiểm và đầu xe về phía an toàn." };
		Answer a1_answer_12_set1 = new Answer(a1_answers_12_set1, 0, idList.get(11));
		answerRepo.save(a1_answer_12_set1);

		String[] a1_answers_13_set1 = { "Tăng ga thật nhanh, giảm ga từ từ.", "Tăng ga thật nhanh, giảm ga thật nhanh.",
				"Tăng ga từ từ, giảm ga thật nhanh.", "Tăng ga từ từ, giảm ga từ từ." };
		Answer a1_answer_13_set1 = new Answer(a1_answers_13_set1, 2, idList.get(12));
		answerRepo.save(a1_answer_13_set1);

		String[] a1_answers_14_set1 = { "Biển 1.", "Biển 1 và 2.", "Biển 2 và 3.", "Cả ba biển." };
		Answer a1_answer_14_set1 = new Answer(a1_answers_14_set1, 3, idList.get(13));
		answerRepo.save(a1_answer_14_set1);

		String[] a1_answers_15_set1 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a1_answer_15_set1 = new Answer(a1_answers_15_set1, 1, idList.get(14));
		answerRepo.save(a1_answer_15_set1);

		String[] a1_answers_16_set1 = { "Báo hiệu đường có ổ gà, lồi lõm.",
				"Báo hiệu đường có gồ giảm tốc phía trước." };
		Answer a1_answer_16_set1 = new Answer(a1_answers_16_set1, 1, idList.get(15));
		answerRepo.save(a1_answer_16_set1);

		String[] a1_answers_17_set1 = { "Biển 1 và 2.", "Biển 1 và 3.", "Biển 2 và 3.", "Cả ba biển." };
		Answer a1_answer_17_set1 = new Answer(a1_answers_17_set1, 0, idList.get(16));
		answerRepo.save(a1_answer_17_set1);

		String[] a1_answers_18_set1 = { "Tốc độ tối đa cho phép về ban đêm cho các phương tiện là 70 km/h.",
				"Tốc độ tối thiểu cho phép về ban đêm cho các phương tiện là 70 km/h." };
		Answer a1_answer_18_set1 = new Answer(a1_answers_18_set1, 3, idList.get(17));
		answerRepo.save(a1_answer_18_set1);

		String[] a1_answers_19_set1 = { "Biển 1", "Biển 2", "Biển 3", "Cả ba biển." };
		Answer a1_answer_19_set1 = new Answer(a1_answers_19_set1, 0, idList.get(18));
		answerRepo.save(a1_answer_19_set1);

		String[] a1_answers_20_set1 = { "Biển 1.", "Biển 2." };
		Answer a1_answer_20_set1 = new Answer(a1_answers_20_set1, 0, idList.get(19));
		answerRepo.save(a1_answer_20_set1);

		String[] a1_answers_21_set1 = { "Vạch 1", "Vạch 2", "Vạch 3", "Cả 3 vạch." };
		Answer a1_answer_21_set1 = new Answer(a1_answers_21_set1, 1, idList.get(20));
		answerRepo.save(a1_answer_21_set1);

		String[] a1_answers_22_set1 = { "Xe mô tô", "Xe cứu thương." };
		Answer a1_answer_22_set1 = new Answer(a1_answers_22_set1, 1, idList.get(21));
		answerRepo.save(a1_answer_22_set1);

		String[] a1_answers_23_set1 = { "Xe tải, xe con", "Xe khách, xe con", "Xe khách, xe tải." };
		Answer a1_answer_23_set1 = new Answer(a1_answers_23_set1, 2, idList.get(22));
		answerRepo.save(a1_answer_23_set1);

		String[] a1_answers_24_set1 = { "Xe con (A), mô tô, xe con (B), xe đạp",
				"Xe con (B), xe đạp, mô tô, xe con (A)", "Xe con (A), xe con (B), mô tô + xe đạp",
				"Mô tô + xe đạp, xe con (A), xe con (B)." };
		Answer a1_answer_24_set1 = new Answer(a1_answers_24_set1, 3, idList.get(23));
		answerRepo.save(a1_answer_24_set1);

		String[] a1_answers_25_set1 = { "Xe con ( E ), mô tô ( C )", "Xe tải ( A ), mô tô ( D )",
				"Xe khách ( B ), mô tô ( C )", "Xe khách  ( B ), mô tô ( D )." };
		Answer a1_answer_25_set1 = new Answer(a1_answers_25_set1, 0, idList.get(24));
		answerRepo.save(a1_answer_25_set1);

		String[] a1_answers_1_set2 = { "Bị nghiêm cấm tùy từng trường hợp", "Không bị nghiêm cấm", "Bị nghiêm cấm." };
		Answer a1_answer_1_set2 = new Answer(a1_answers_1_set2, 2, idList.get(25));
		answerRepo.save(a1_answer_1_set2);

		String[] a1_answers_2_set2 = { "Được phép nhưng phảo đảm bảo an toàn", "Không được phép",
				"Được phép tùy từng hoàn cảnh, điều kiện cụ thể." };
		Answer a1_answer_2_set2 = new Answer(a1_answers_2_set2, 1, idList.get(26));
		answerRepo.save(a1_answer_2_set2);

		String[] a1_answers_3_set2 = { "Là người điều khiển xe cơ giới", "Là người điều khiển xe thô sơ",
				"Là người điều khiển xe có súc vật kéo." };
		Answer a1_answer_3_set2 = new Answer(a1_answers_3_set2, 0, idList.get(27));
		answerRepo.save(a1_answer_3_set2);

		String[] a1_answers_4_set2 = { "Người điều khiển, người sử dụng phương tiện tham giao giao thông đường bộ",
				"Người điều khiển, dẫn dắt súc vật, người đi bộ trên đường", "Cả ý 1 và ý 2." };
		Answer a1_answer_4_set2 = new Answer(a1_answers_4_set2, 2, idList.get(28));
		answerRepo.save(a1_answer_4_set2);

		String[] a1_answers_5_set2 = { "Không được vượt", "Được vượt khi đang đi trên cầu",
				"Được phép vượt khi qua nơi giao nhau có ít phương tiện cùng tham gia giao thông",
				"Được vượt khi đảm bảo an toàn." };
		Answer a1_answer_5_set2 = new Answer(a1_answers_5_set2, 3, idList.get(29));
		answerRepo.save(a1_answer_5_set2);

		String[] a1_answers_6_set2 = { "Đỏ – Vàng – Xanh", "Cam – Vàng – Xanh", "Vàng – Xanh dương – Xanh lá",
				"Đỏ – Cam – Xanh." };
		Answer a1_answer_6_set2 = new Answer(a1_answers_6_set2, -1, idList.get(30));
		answerRepo.save(a1_answer_6_set2);

		String[] a1_answers_7_set2 = {
				"Xe thô sơ phải đi trên làn đường bên trái ngoài cùng, xe cơ giới, xe máy chuyên dùng đi trên làn đường bên phải.",
				"Xe thô sơ phải đi trên làn đường bên phải trong cùng; xe cơ giới, xe máy chuyên dùng đi trên làn đường bên trái",
				"Xe thô sơ đi trên làn đường phù hợp không gây cản trở giao thông, xe cơ giới, xe máy chuyên dùng đi trên làn đường bên phải." };
		Answer a1_answer_7_set2 = new Answer(a1_answers_7_set2, 1, idList.get(31));
		answerRepo.save(a1_answer_7_set2);

		String[] a1_answers_8_set2 = { "Khi tham gia giao thông đường bộ",
				"Chỉ khi đi trên đường chuyên dùng; đường cao tốc",
				"Khi tham gia giao thông trên đường tỉnh lộ hoặc quốc lộ." };
		Answer a1_answer_8_set2 = new Answer(a1_answers_8_set2, 0, idList.get(32));
		answerRepo.save(a1_answer_8_set2);

		String[] a1_answers_9_set2 = {
				"Phải đội mũ bảo hiểm đạt chuẩn, có cài quai đúng quy cách, mặc quần áo gọn gàng; không sử dụng ô, điện thoại di động, thiết bị âm thanh (trừ thiết bị trợ thính)",
				"Phải đội mũ bảo hiểm khi trời mưa gió hoặc trời quá nắng; có thể sử dụng ô, điện thoại di động thiết bị âm thanh nhưng đảm bảo an toàn.",
				"Phải đội mũ bảo hiểm khi cảm thấy mất an toàn giao thông hoặc khi chuẩn bị di chuyển quãng đường xa." };
		Answer a1_answer_9_set2 = new Answer(a1_answers_9_set2, 0, idList.get(33));
		answerRepo.save(a1_answer_9_set2);

		String[] a1_answers_10_set2 = { "Để làm đẹp", "Để tránh mưa nắng", "Để giảm thiểu chấn thương vùng đầu",
				"Để các loại phương tiện khác dễ quan sát." };
		Answer a1_answer_10_set2 = new Answer(a1_answers_10_set2, 2, idList.get(34));
		answerRepo.save(a1_answer_10_set2);

		String[] a1_answers_11_set2 = {
				"Xe cơ giới, xe máy chuyên dùng phải bật đèn; xe thô sơ phải bật đèn hoặc có vật phát sáng báo hiệu; chỉ được dừng xe, đỗ xe ở nơi quy định",
				"Xe cơ giới phải bật đèn ngay cả khi đường hầm sáng; phải cho xe chạy trên một làn đường và chỉ chuyển làn ở nơi được phép; được quay đầu xe, lùi xe khi cần thiết",
				"Xe máy chuyên dùng phải bật đèn ngay cả khi đường hầm sáng; phải cho xe chạy trên một làn đường và chỉ chuyển làn ở nơi được phép, được quay đầu xe, lui xe khi cần thiết." };
		Answer a1_answer_11_set2 = new Answer(a1_answers_11_set2, 0, idList.get(35));
		answerRepo.save(a1_answer_11_set2);

		String[] a1_answers_12_set2 = {
				"Quan sát cẩn thận các chướng ngại vật và báo hiệu bằng còi, đèn; giảm tốc độ tới mức cần thiết, về số thấp và thực hiện quay vòng với tốc độ phù hợp với bán kính cong của đường vòng.",
				"Quan sát cẩn thận các chướng ngại vật và báo hiệu bằng còi, đèn; tăng tốc nhanh chóng qua đường vòng và giảm tốc độ sau khi qua đường vòng." };
		Answer a1_answer_12_set2 = new Answer(a1_answers_12_set2, 0, idList.get(36));
		answerRepo.save(a1_answer_12_set2);

		String[] a1_answers_13_set2 = {
				"Đứng thẳng trên giá gác chân lái sau đó hơi gập đầu gối và khuỷu tay, đi chậm để không nẩy quá mạnh.",
				"Ngồi lùi lại phía sau, tăng ga vượt nhanh qua đoạn đường xóc.",
				"Ngồi lệch sang bên trái hoặc bên phải để lấy thăng bằng qua đoạn đường gồ ghề." };
		Answer a1_answer_13_set2 = new Answer(a1_answers_13_set2, 0, idList.get(37));
		answerRepo.save(a1_answer_13_set2);

		String[] a1_answers_14_set2 = { "Biển 1.", "Biển 2.", "Biển 3.", "Cả ba biển." };
		Answer a1_answer_14_set2 = new Answer(a1_answers_14_set2, 2, idList.get(38));
		answerRepo.save(a1_answer_14_set2);

		String[] a1_answers_15_set2 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a1_answer_15_set2 = new Answer(a1_answers_15_set2, 2, idList.get(39));
		answerRepo.save(a1_answer_15_set2);

		String[] a1_answers_16_set2 = { "Biển 1.", "Biển 2.", "Cả hai biển." };
		Answer a1_answer_16_set2 = new Answer(a1_answers_16_set2, 2, idList.get(40));
		answerRepo.save(a1_answer_16_set2);

		String[] a1_answers_17_set2 = { "Biển 1.", "Biển 2.", "Cả 2 biển." };
		Answer a1_answer_17_set2 = new Answer(a1_answers_17_set2, 0, idList.get(41));
		answerRepo.save(a1_answer_17_set2);

		String[] a1_answers_18_set2 = { "Được phép.", "Không được phép." };
		Answer a1_answer_18_set2 = new Answer(a1_answers_18_set2, 1, idList.get(42));
		answerRepo.save(a1_answer_18_set2);

		String[] a1_answers_19_set2 = { "Biển 1", "Biển 2", "Biển 3", "Biển 1 và 2." };
		Answer a1_answer_19_set2 = new Answer(a1_answers_19_set2, 3, idList.get(43));
		answerRepo.save(a1_answer_19_set2);

		String[] a1_answers_20_set2 = { "Biển 1", "Biển 2." };
		Answer a1_answer_20_set2 = new Answer(a1_answers_20_set2, 0, idList.get(44));
		answerRepo.save(a1_answer_20_set2);

		String[] a1_answers_21_set2 = { "Vạch 1", "Vạch 2", "Vạch 3", "Vạch 1 và 3." };
		Answer a1_answer_21_set2 = new Answer(a1_answers_21_set2, 3, idList.get(45));
		answerRepo.save(a1_answer_21_set2);

		String[] a1_answers_22_set2 = { "Xe con và xe khách", "Mô tô." };
		Answer a1_answer_22_set2 = new Answer(a1_answers_22_set2, 0, idList.get(46));
		answerRepo.save(a1_answer_22_set2);

		String[] a1_answers_23_set2 = { "Xe tải, xe khách, xe con, mô tô.", "Xe tải, mô tô, xe khách, xe con.",
				"xe khách, xe tải, xe con, mô tô.", "Mô tô, xe khách, xe tải, xe con." };
		Answer a1_answer_23_set2 = new Answer(a1_answers_23_set2, 1, idList.get(47));
		answerRepo.save(a1_answer_23_set2);

		String[] a1_answers_24_set2 = { "Xe khách, xe tải, mô tô, xe con.", "Xe con, xe khách, xe tải, mô tô",
				"Mô tô, xe tải, xe khách, xe con.", "Mô tô, xe tải, xe con, xe khách." };
		Answer a1_answer_24_set2 = new Answer(a1_answers_24_set2, 2, idList.get(48));
		answerRepo.save(a1_answer_24_set2);

		String[] a1_answers_25_set2 = { "Xe con (B), mô tô (C)", "Xe con (A), mô tô (C)", "Xe con (E), mô tô (D)",
				"Tất cả các loại xe trên." };
		Answer a1_answer_25_set2 = new Answer(a1_answers_25_set2, 2, idList.get(49));
		answerRepo.save(a1_answer_25_set2);

		String[] a1_answers_1_set3 = { "Không được vượt", "Được vượt khi đang đi trên cầu",
				"Được phép vượt khi đi qua nơi giao nhau có ít phương tiện cùng tham gia giao thông",
				"Được vượt khi đảm bảo an toàn." };
		Answer a1_answer_1_set3 = new Answer(a1_answers_1_set3, 0, idList.get(50));
		answerRepo.save(a1_answer_1_set3);

		String[] a1_answers_2_set3 = { "Không được vận chuyển", "Chỉ được vận chuyển khi đã chằng buộc cẩn thận",
				"Chỉ được vận chuyển vật cồng kềnh trên xe máy nếu khoảng cách về nhà ngắn hơn 2 km." };
		Answer a1_answer_2_set3 = new Answer(a1_answers_2_set3, 0, idList.get(51));
		answerRepo.save(a1_answer_2_set3);

		String[] a1_answers_3_set3 = {
				"Là bộ phận của đường để ngăn cách không cho các loại xe vào những nơi không được phép",
				"Là bộ phận của đường để phân tách phần đường xe chạy và hành lang an toàn giao thông.",
				"Là bộ phận của đường để phân chia mặt đường thành hai chiều xe chạy riêng biệt hoặc để phân chia phần đường của xe cơ giới và xe thô sơ." };
		Answer a1_answer_3_set3 = new Answer(a1_answers_3_set3, 2, idList.get(52));
		answerRepo.save(a1_answer_3_set3);

		String[] a1_answers_4_set3 = {
				"Là trạng thái đứng yên của phương tiện giao thông có giới hạn trong một khoảng thời gian cần thiết đủ để cho người lên, xuống phương tiện đó, xếp dỡ hàng hóa hoặc thực hiện công việc khác",
				"Là trạng thái đứng yên của phương tiện giao thông không giới hạn thời gian." };
		Answer a1_answer_4_set3 = new Answer(a1_answers_4_set3, 1, idList.get(53));
		answerRepo.save(a1_answer_4_set3);

		String[] a1_answers_5_set3 = {
				"Buông cả hai tay; sử dụng xe để kéo, đẩy xe khác, vật khác; sử dụng chân chống của xe quệt xuống đường khi xe đang chạy",
				"Buông một tay; sử dụng xe để chở người hoặc hàng hóa; để chân chạm xuống đất khi khởi hành",
				"Đội mũ bảo hiểm; chạy xe đúng tốc độ quy định và chấp hành đúng quy tắc giao thông đường bộ",
				"Chở người ngồi sau dưới 16 tuổi." };
		Answer a1_answer_5_set3 = new Answer(a1_answers_5_set3, 0, idList.get(54));
		answerRepo.save(a1_answer_5_set3);

		String[] a1_answers_6_set3 = { "Biển báo nguy hiểm", "Biển báo cấm", "Biển báo hiệu lệnh",
				"Biển báo chỉ dẫn." };
		Answer a1_answer_6_set3 = new Answer(a1_answers_6_set3, 1, idList.get(55));
		answerRepo.save(a1_answer_6_set3);

		String[] a1_answers_7_set3 = { "Phải báo hiệu bằng đèn hoặc còi", "Chỉ được báo hiệu bằng còi",
				"Phải báo hiệu bằng cả còi và đèn", "Chỉ được báo hiệu bằng đèn." };
		Answer a1_answer_7_set3 = new Answer(a1_answers_7_set3, 3, idList.get(56));
		answerRepo.save(a1_answer_7_set3);

		String[] a1_answers_8_set3 = { "Chở người bệnh đi cấp cứu; trẻ em dưới 14 tuổi",
				"Áp giải người có hành vi vi phạm pháp luật", "Cả ý 1 và ý 2." };
		Answer a1_answer_8_set3 = new Answer(a1_answers_8_set3, 2, idList.get(57));
		answerRepo.save(a1_answer_8_set3);

		String[] a1_answers_9_set3 = {
				"Là đoạn đường nằm trong khu công nghiệp có đông người và phương tiện tham gia giao thông và được xác định cụ thể bằng biển chỉ dẫn địa giới",
				"Là đoạn đường bộ nằm trong khu vực nội thành phố, nội thị xã, nội thị trấn và những đoạn đường có đông dân cư sinh sống sát dọc theo đường, có các hoạt động có thể ảnh hưởng đến an toàn giao thông; được xác định bằng biển báo hiệu là đường khu đông dân cư.",
				"Là đoạn đường nằm ngoài khu vực nội thành phố, nội thị xã có đông người và phương tiện tham gia giao thông và được xác định cụ thể bằng biển chỉ dẫn địa giới" };
		Answer a1_answer_9_set3 = new Answer(a1_answers_9_set3, 1, idList.get(58));
		answerRepo.save(a1_answer_9_set3);

		String[] a1_answers_10_set3 = { "Tăng tốc độ qua đường giao nhau để đi trước xe đi trên đường ưu tiên",
				"Giảm tốc độ qua đường giao nhau để đi trước xe đi trên đường ưu tiên",
				"Nhường đường cho xe đi trên đường ưu tiên hoặc từ bất kỳ hướng nào tới." };
		Answer a1_answer_10_set3 = new Answer(a1_answers_10_set3, 2, idList.get(59));
		answerRepo.save(a1_answer_10_set3);

		String[] a1_answers_11_set3 = {
				"Là sự hiểu biết và chấp hành nghiêm chỉnh pháp luật về giao thông; là ý thức trách nhiệm với cộng đồng khi tham gia giao thông",
				"Là ứng xử có văn hóa, có tình yêu thương con người trong  các tình huống không may xảy ra khi tham gia giao thông",
				"Cả ý 1 và ý 2" };
		Answer a1_answer_11_set3 = new Answer(a1_answers_11_set3, 2, idList.get(60));
		answerRepo.save(a1_answer_11_set3);

		String[] a1_answers_12_set3 = { "Sử dụng phanh trước.", "Sửu dụng phanh sau.",
				"Giảm hết ga; sử dụng đồng thời cả phanh sau và phanh trước." };
		Answer a1_answer_12_set3 = new Answer(a1_answers_12_set3, 2, idList.get(61));
		answerRepo.save(a1_answer_12_set3);

		String[] a1_answers_13_set3 = { "Giữ tay ga ở mức độ phù hợp, sử dụng phanh trước và phanh sau để giảm tốc độ.",
				"Nhả hết tay ga, tắt động cơ, sử dụng phanh trước và phanh sau để giảm tốc độ.",
				"Sử dụng phanh trước để giảm tốc độ kết hợp với tắt chìa khóa điện của xe." };
		Answer a1_answer_13_set3 = new Answer(a1_answers_13_set3, 0, idList.get(62));
		answerRepo.save(a1_answer_13_set3);

		String[] a1_answers_14_set3 = { "Biển 1.", "Biển 2 và 3.", "Biển 3." };
		Answer a1_answer_14_set3 = new Answer(a1_answers_14_set3, 0, idList.get(63));
		answerRepo.save(a1_answer_14_set3);

		String[] a1_answers_15_set3 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a1_answer_15_set3 = new Answer(a1_answers_15_set3, 0, idList.get(64));
		answerRepo.save(a1_answer_15_set3);

		String[] a1_answers_16_set3 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a1_answer_16_set3 = new Answer(a1_answers_16_set3, 0, idList.get(65));
		answerRepo.save(a1_answer_16_set3);

		String[] a1_answers_17_set3 = { "Biển 1.", "Biển 2.", "Cả ba biển." };
		Answer a1_answer_17_set3 = new Answer(a1_answers_17_set3, 1, idList.get(66));
		answerRepo.save(a1_answer_17_set3);

		String[] a1_answers_18_set3 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a1_answer_18_set3 = new Answer(a1_answers_18_set3, 1, idList.get(67));
		answerRepo.save(a1_answer_18_set3);

		String[] a1_answers_19_set3 = { "Biển 1", "Biển 2", "Biển 3", "Cả ba biển." };
		Answer a1_answer_19_set3 = new Answer(a1_answers_19_set3, 2, idList.get(68));
		answerRepo.save(a1_answer_19_set3);

		String[] a1_answers_20_set3 = { "Biển 1", "Biển 2", "Cả hai biển." };
		Answer a1_answer_20_set3 = new Answer(a1_answers_20_set3, 1, idList.get(69));
		answerRepo.save(a1_answer_20_set3);

		String[] a1_answers_21_set3 = { "Phân chia hai chiều xe chạy ngược nhiều nhau",
				"Phân chia các làn xe chạy cùng chiều nhau." };
		Answer a1_answer_21_set3 = new Answer(a1_answers_21_set3, 0, idList.get(70));
		answerRepo.save(a1_answer_21_set3);

		String[] a1_answers_22_set3 = { "Xe con", "Xe mô tô" };
		Answer a1_answer_22_set3 = new Answer(a1_answers_22_set3, 1, idList.get(71));
		answerRepo.save(a1_answer_22_set3);

		String[] a1_answers_23_set3 = { "Xe mô tô", "Xe con" };
		Answer a1_answer_23_set3 = new Answer(a1_answers_23_set3, 0, idList.get(72));
		answerRepo.save(a1_answer_23_set3);

		String[] a1_answers_24_set3 = { "Cả ba hướng", "Chỉ hướng 1 và 3", "Chỉ hướng 1." };
		Answer a1_answer_24_set3 = new Answer(a1_answers_24_set3, 0, idList.get(73));
		answerRepo.save(a1_answer_24_set3);

		String[] a1_answers_25_set3 = { "Xe khách", "Mô tô", "Xe con", "Xe con và mô tô." };
		Answer a1_answer_25_set3 = new Answer(a1_answers_25_set3, 2, idList.get(74));
		answerRepo.save(a1_answer_25_set3);

		String[] a1_answers_1_set4 = { "Được phép", "Không được phép", "Tùy từng trường hợp." };
		Answer a1_answer_1_set4 = new Answer(a1_answers_1_set4, 1, idList.get(75));
		answerRepo.save(a1_answer_1_set4);

		String[] a1_answers_2_set4 = { "Chỉ được kéo nếu đã nhìn thấy trạm xăng",
				"Chỉ được thực hiện trên đường vắng phương tiện cùng tham gia giao thông.", "Không được phép." };
		Answer a1_answer_2_set4 = new Answer(a1_answers_2_set4, 2, idList.get(76));
		answerRepo.save(a1_answer_2_set4);

		String[] a1_answers_3_set4 = { "Dải phân cách gồm loại cố định và loại di động",
				"Dải phân cách gồm tường chống ồn, hộ lan cứng và hộ lan mềm",
				"Dải phân cách gồm giá long môn và biển báo hiệu đường bộ." };
		Answer a1_answer_3_set4 = new Answer(a1_answers_3_set4, 0, idList.get(77));
		answerRepo.save(a1_answer_3_set4);

		String[] a1_answers_4_set4 = {
				"Là trạng thái đứng yên của phương tiện giao thông không giới hạn thời gian để cho người lên, xuống phương tiện, xếp dỡ hàng hóa hoặc thực hiện công việc khác",
				"Là trạng thái đứng yên tạm thời của phương tiện giao thông trong một khoảng thời gian cần thiết đủ để cho người lên, xuống phương tiện, xếp dỡ hàng hóa hoặc thực hiện công việc khác",
				"Là trạng thái đứng yên của phương tiện giao thông không giới hạn thời gian giữa 02 lần vận chuyển hàng hóa hoặc hành khách." };
		Answer a1_answer_4_set4 = new Answer(a1_answers_4_set4, 1, idList.get(78));
		answerRepo.save(a1_answer_4_set4);

		String[] a1_answers_5_set4 = { "Xe mô tô 2 bánh có dung tích xi-lanh từ 50 cm3 trở lên",
				"Xe gắn máy có dung tích xi-lanh dưới 50cm3", "Xe ô tô tải dưới 3.5 tấn; xe chở người đến 9 chỗ ngồi",
				"Tất cả các ý nêu trên." };
		Answer a1_answer_5_set4 = new Answer(a1_answers_5_set4, -1, idList.get(79));
		answerRepo.save(a1_answer_5_set4);

		String[] a1_answers_6_set4 = { "Biển báo nguy hiểm", "Biển báo cấm", "Biển báo hiệu lệnh",
				"Biển báo chỉ dẫn." };
		Answer a1_answer_6_set4 = new Answer(a1_answers_6_set4, 0, idList.get(80));
		answerRepo.save(a1_answer_6_set4);

		String[] a1_answers_7_set4 = {
				"Tăng tốc độ và ra hiệu cho xe sau vượt, không được gây trở ngại cho xe sau vượt.",
				"Người điều khiển phương tiện phía trước phải giảm tốc độ, đi sát về bên phải của phần đường xe chạy, cho đến khi xe sau đã vượt qua, không được gây trở ngại cho xe sau vượt.",
				"Cho xe tránh về bên trái mình và ra hiệu cho xe sau vượt; nếu có chướng ngại vật phía trước hoặc thiếu điều kiện an toàn chưa cho vượt được phải ra hiệu cho xe sau biết; cấm gây trở ngại cho xe xin vượt." };
		Answer a1_answer_7_set4 = new Answer(a1_answers_7_set4, 1, idList.get(81));
		answerRepo.save(a1_answer_7_set4);

		String[] a1_answers_8_set4 = {
				"Đi vào phần đường dành cho người đi bộ và phương tiện khác; sử dụng ô, điện thoại di động, thiết bị âm thanh (trừ thiết bị trợ thính), đi xe dàn hàng ngang",
				"Chở 02 người; trong đó, có người bệnh đi cấp cứu hoặc trẻ em dưới 14 tuổi hoặc áp giải người có hành vi vi phạm pháp luật",
				"Điều khiển phương tiện tham gia giao thông trên đường tỉnh lộ hoặc quốc lộ." };
		Answer a1_answer_8_set4 = new Answer(a1_answers_8_set4, 0, idList.get(82));
		answerRepo.save(a1_answer_8_set4);

		String[] a1_answers_9_set4 = { "50 km/h", "40 km/h", "60 km/h." };
		Answer a1_answer_9_set4 = new Answer(a1_answers_9_set4, 1, idList.get(83));
		answerRepo.save(a1_answer_9_set4);

		String[] a1_answers_10_set4 = { "Giảm tốc độ, đi từ từ để vượt qua trước người đi bộ",
				"Giảm tốc độ, có thể dừng lại nếu cần thiết trước vạch dừng xe để nhường đường cho người đi bộ qua đường",
				"Tăng tốc độ để vượt qua trước người đi bộ." };
		Answer a1_answer_10_set4 = new Answer(a1_answers_10_set4, 1, idList.get(84));
		answerRepo.save(a1_answer_10_set4);

		String[] a1_answers_11_set4 = {
				"Điều khiển xe đi bên phải theo chiều đi của mình; đi đúng phần đường, làn đường quy đinh; dừng, đỗ xe đúng nơi quy định; đã uống rượu, bia thì không lái xe.",
				"Điều khiển xe đi trên phần đường, làn đường có ít phương tiện giao thông; dừng xe, đỗ xe ở nơi thuận tiện hoặc theo yêu cầu của hành khách, của người thân.",
				"Dừng và đỗ xe ở nơi thuận tiện cho việc chuyên chở hành khách và giao nhận hàng hóa; sử dụng ít rượu, bia thì có thể lái xe." };
		Answer a1_answer_11_set4 = new Answer(a1_answers_11_set4, 0, idList.get(85));
		answerRepo.save(a1_answer_11_set4);

		String[] a1_answers_12_set4 = {
				"Giảm tốc độ để đảm bảo an toàn với xe phía trước và sử dụng điện để thoại để liên lạc.",
				"Giảm tốc độ để dừng xe ở nơi cho phép dừng xe sau đó sử dụng điện thoại để liên lạc.",
				"Tăng tốc độ để cách ra xe phía sau và sử dụng điện thoại để liên lạc." };
		Answer a1_answer_12_set4 = new Answer(a1_answers_12_set4, 1, idList.get(86));
		answerRepo.save(a1_answer_12_set4);

		String[] a1_answers_13_set4 = { "Hiệu lệnh của nhân viên gác chắn", "Đèn đỏ sáng nháy, cờ đỏ, biển đỏ",
				"Còi, chuông kêu, chắn đã đóng", "Tất cả các ý trên." };
		Answer a1_answer_13_set4 = new Answer(a1_answers_13_set4, 3, idList.get(87));
		answerRepo.save(a1_answer_13_set4);

		String[] a1_answers_14_set4 = { "Biển 1 và 2.", "Biển 1 và 3.", "Biển 2 và 3.", "Cả ba biển." };
		Answer a1_answer_14_set4 = new Answer(a1_answers_14_set4, 1, idList.get(88));
		answerRepo.save(a1_answer_14_set4);

		String[] a1_answers_15_set4 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a1_answer_15_set4 = new Answer(a1_answers_15_set4, 1, idList.get(89));
		answerRepo.save(a1_answer_15_set4);

		String[] a1_answers_16_set4 = { "Không biển nào.", "Biển 1 và 2.", "Biển 2 và 3.", "Cả 3 biển." };
		Answer a1_answer_16_set4 = new Answer(a1_answers_16_set4, 2, idList.get(90));
		answerRepo.save(a1_answer_16_set4);

		String[] a1_answers_17_set4 = { "Biển 1.", "Biển 2.", "Biển 1 và 2." };
		Answer a1_answer_17_set4 = new Answer(a1_answers_17_set4, 2, idList.get(91));
		answerRepo.save(a1_answer_17_set4);

		String[] a1_answers_18_set4 = { "Biển 1 và 2.", "Biển 1 và 3.", "Biển 2 và 3." };
		Answer a1_answer_18_set4 = new Answer(a1_answers_18_set4, 1, idList.get(92));
		answerRepo.save(a1_answer_18_set4);

		String[] a1_answers_19_set4 = { "Biển 1", "Biển 2", "Biển 3", "Biển 2 và 3." };
		Answer a1_answer_19_set4 = new Answer(a1_answers_19_set4, 3, idList.get(93));
		answerRepo.save(a1_answer_19_set4);

		String[] a1_answers_20_set4 = { "Biển 1", "Biển 2", "Cả 2 biển", "Không biển nào." };
		Answer a1_answer_20_set4 = new Answer(a1_answers_20_set4, 0, idList.get(94));
		answerRepo.save(a1_answer_20_set4);

		String[] a1_answers_21_set4 = { "Vạch 1", "Vạch 2", "Vạch 3", "Vạch 1 và vạch 3" };
		Answer a1_answer_21_set4 = new Answer(a1_answers_21_set4, 3, idList.get(95));
		answerRepo.save(a1_answer_21_set4);

		String[] a1_answers_22_set4 = { "Xe tải, mô tô", "Xe khách, mô tô", "Xe tải, xe con", "Mô tô, xe con." };
		Answer a1_answer_22_set4 = new Answer(a1_answers_22_set4, 1, idList.get(96));
		answerRepo.save(a1_answer_22_set4);

		String[] a1_answers_23_set4 = { "Xe của bạn, mô tô, xe con", "Xe con, xe của bạn, mô tô",
				"Mô tô, xe con, xe của bạn." };
		Answer a1_answer_23_set4 = new Answer(a1_answers_23_set4, 2, idList.get(97));
		answerRepo.save(a1_answer_23_set4);

		String[] a1_answers_24_set4 = { "Cả ba hướng", "Hướng 1 và 2", "Hướng 1 và 3", "Hướng 2 và 3." };
		Answer a1_answer_24_set4 = new Answer(a1_answers_24_set4, 2, idList.get(98));
		answerRepo.save(a1_answer_24_set4);

		String[] a1_answers_25_set4 = { "Xe của bạn, mô tô, xe con", "Xe con, xe của bạn, mô tô",
				"Mô tô, xe con, xe của bạn." };
		Answer a1_answer_25_set4 = new Answer(a1_answers_25_set4, 1, idList.get(99));
		answerRepo.save(a1_answer_25_set4);

		String[] a1_answers_1_set5 = { "Được phép",
				"Nếu phương tiện được kéo, đẩy có khối lượng nhỏ hơn phương tiện của mình", "Tùy trường hợp",
				"Không được phép." };
		Answer a1_answer_1_set5 = new Answer(a1_answers_1_set5, 3, idList.get(100));
		answerRepo.save(a1_answer_1_set5);

		String[] a1_answers_2_set5 = { "Chỉ được phép nếu cả hai đội mũ bảo hiểm", "Không được phép",
				"Chỉ được phép thực hiện trên đường thật vắng", "Chỉ được phép khi người đi xe đạp đã quá mệt." };
		Answer a1_answer_2_set5 = new Answer(a1_answers_2_set5, 1, idList.get(101));
		answerRepo.save(a1_answer_2_set5);

		String[] a1_answers_3_set5 = {
				"Gồm xe ô tô, máy kéo, xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy, xe cơ giới dùng cho người khuyết tật và xe máy chuyên dùng",
				"Gồm xe ô tô; máy kéo; rơ moóc hoặc sơ mi rơ moóc được kéo bởi xe ô tô, máy kéo; xe mô tô hai bánh; xe mô tô ba bánh, xe gắn máy (kể cả xe máy điện) và các loại xe tương tự." };
		Answer a1_answer_3_set5 = new Answer(a1_answers_3_set5, 1, idList.get(102));
		answerRepo.save(a1_answer_3_set5);

		String[] a1_answers_4_set5 = { "Diễn ra trên đường phố không có người qua lại", "Được người dân ủng hộ",
				"Được cơ quan có thẩm quyền cấp phép." };
		Answer a1_answer_4_set5 = new Answer(a1_answers_4_set5, 2, idList.get(103));
		answerRepo.save(a1_answer_4_set5);

		String[] a1_answers_5_set5 = { "16 Tuổi", "18 Tuổi", "17 Tuổi" };
		Answer a1_answer_5_set5 = new Answer(a1_answers_5_set5, 1, idList.get(104));
		answerRepo.save(a1_answer_5_set5);

		String[] a1_answers_6_set5 = { "Biển báo nguy hiểm", "Biển báo cấm", "Biển báo hiệu lệnh phải thi hành",
				"Biển báo chỉ dẫn." };
		Answer a1_answer_6_set5 = new Answer(a1_answers_6_set5, 2, idList.get(105));
		answerRepo.save(a1_answer_6_set5);

		String[] a1_answers_7_set5 = { "Quan sát gương, ra tín hiệu, quan sát an toàn và chuyển hướng.",
				"Quan sát gương, giảm tốc độ, ra tín hiệu chuyển hướng, quan sát an toàn và chuyển hướng.",
				"Quan sát gương, tăng tốc độ, ra tín hiệu và chuyển hướng." };
		Answer a1_answer_7_set5 = new Answer(a1_answers_7_set5, 1, idList.get(106));
		answerRepo.save(a1_answer_7_set5);

		String[] a1_answers_8_set5 = {
				"Khi có báo hiệu cảnh báo nguy hiểm hoặc có chướng ngại vật trên đường; khi chuyển hướng xe chạy hoặc tầm nhìn bị hạn chế; khi qua nơi đường giao nhau, nơi đường bộ giao nhau với đường sắt; đường vòng; đường có địa hình quanh co, đèo dốc.",
				"Khi qua cầu, cống hẹp; khi lên gần đỉnh dốc, khi xuống dốc, khi qua trường học, khu đông dân cư, khu vực đang thi công trên đường bộ; hiện trường xảy ra tai nạn giao thông.",
				"Khi điều khiển xe vượt xe khác trên đường quốc lộ, đường cao tốc", "Cả ý 1 và ý 2." };
		Answer a1_answer_8_set5 = new Answer(a1_answers_8_set5, 3, idList.get(107));
		answerRepo.save(a1_answer_8_set5);

		String[] a1_answers_9_set5 = { "60 Km/h", "50 Km/h", "40 Km/h" };
		Answer a1_answer_9_set5 = new Answer(a1_answers_9_set5, 0, idList.get(108));
		answerRepo.save(a1_answer_9_set5);

		String[] a1_answers_10_set5 = {
				"Phải cho xe dừng lại trước vạch dừng, trường hợp đã đi quá vạch dừng hoặc đã quá gần vạch dừng nếu dừng lại thấy nguy hiểm thì được đi tiếp",
				"Trong trường hợp tín hiệu vàng nhấp nháy là được đi nhưng phải giảm tốc độ, chú ý quan sát nhường đường cho người đi bộ qua đường",
				"Nhanh chóng tăng tốc độ, vượt qua nút giao và chú ý đảm bảo an toàn", "Cả ý 1 và ý 2." };
		Answer a1_answer_10_set5 = new Answer(a1_answers_10_set5, 0, idList.get(109));
		answerRepo.save(a1_answer_10_set5);

		String[] a1_answers_11_set5 = {
				"Điều khiển xe đi bên phải theo chiều đi của mình, đi đúng phần đường, làn đường quy định, đội mũ bảo hiểm đạt chuẩn, cài quai đúng quy cách.",
				"Điều khiển xe đi trên phần đường, làn đường có ít phương tiện tham gia giao thông.",
				"Điều khiển xe và đội mũ bảo hiểm ở nơi có biển báo bắt buộc đội mũ bảo hiểm." };
		Answer a1_answer_11_set5 = new Answer(a1_answers_11_set5, 0, idList.get(110));
		answerRepo.save(a1_answer_11_set5);

		String[] a1_answers_12_set5 = { "Để quan sát an toàn phía bên trái khi chuẩn bị rẽ trái.",
				"Để quan sát an toàn phía bên phải khi chuẩn bị rẽ phải.",
				"Để quan sát an toàn phía sau cả bên trái và bên phải trước khi chuyển hướng.",
				"Để quan sát an toàn phía trước cả bên trái và bên phải trước khi chuyển hướng." };
		Answer a1_answer_12_set5 = new Answer(a1_answers_12_set5, 2, idList.get(111));
		answerRepo.save(a1_answer_12_set5);

		String[] a1_answers_13_set5 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a1_answer_13_set5 = new Answer(a1_answers_13_set5, 0, idList.get(112));
		answerRepo.save(a1_answer_13_set5);

		String[] a1_answers_14_set5 = { "Biển 1 và 2.", "Biển 1 và 3.", "Biển 2 và 3.", "Cả ba biển." };
		Answer a1_answer_14_set5 = new Answer(a1_answers_14_set5, 2, idList.get(113));
		answerRepo.save(a1_answer_14_set5);

		String[] a1_answers_15_set5 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a1_answer_15_set5 = new Answer(a1_answers_15_set5, 1, idList.get(114));
		answerRepo.save(a1_answer_15_set5);

		String[] a1_answers_16_set5 = { "Biển 1.", "Biển 2.", "Không biển nào.", "Cả hai biển." };
		Answer a1_answer_16_set5 = new Answer(a1_answers_16_set5, 1, idList.get(115));
		answerRepo.save(a1_answer_16_set5);

		String[] a1_answers_17_set5 = { "Biển 1.", "Biển 2.", "Cả ba biển." };
		Answer a1_answer_17_set5 = new Answer(a1_answers_17_set5, 1, idList.get(116));
		answerRepo.save(a1_answer_17_set5);

		String[] a1_answers_18_set5 = { "Biển 1.", "Biển 2.", "Biển 3.", "Biển 1 và 3." };
		Answer a1_answer_18_set5 = new Answer(a1_answers_18_set5, 0, idList.get(117));
		answerRepo.save(a1_answer_18_set5);

		String[] a1_answers_19_set5 = { "Biển 1", "Biển 2", "Biển 3", "Cả ba biển." };
		Answer a1_answer_19_set5 = new Answer(a1_answers_19_set5, 1, idList.get(118));
		answerRepo.save(a1_answer_19_set5);

		String[] a1_answers_20_set5 = { "Biển 1", "Biển 2", "Cả 2 biển", "Không biển nào." };
		Answer a1_answer_20_set5 = new Answer(a1_answers_20_set5, 1, idList.get(119));
		answerRepo.save(a1_answer_20_set5);

		String[] a1_answers_21_set5 = { "Vị trí dừng xe của các phương tiện vận tải hành khách công cộng",
				"Báo cho người điều khiển được dừng phương tiện trong phạm vi phần mặt đường có bố trí vạch để tránh ùn tắc giao thông.",
				"Dùng để xác định vị trí giữa các phương tiện trên đường." };
		Answer a1_answer_21_set5 = new Answer(a1_answers_21_set5, 0, idList.get(120));
		answerRepo.save(a1_answer_21_set5);

		String[] a1_answers_22_set5 = { "Chỉ xe khách, mô tô", "Tất cả các loại xe trên",
				"Không xe nào chấp hành đúng quy tắc giao thông." };
		Answer a1_answer_22_set5 = new Answer(a1_answers_22_set5, 1, idList.get(121));
		answerRepo.save(a1_answer_22_set5);

		String[] a1_answers_23_set5 = { "Xe tải", "Xe con và mô tô", "Cả ba xe", "Xe con và xe tải." };
		Answer a1_answer_23_set5 = new Answer(a1_answers_23_set5, 0, idList.get(122));
		answerRepo.save(a1_answer_23_set5);

		String[] a1_answers_24_set5 = { "Xe mô tô", "Xe con." };
		Answer a1_answer_24_set5 = new Answer(a1_answers_24_set5, 1, idList.get(123));
		answerRepo.save(a1_answer_24_set5);

		String[] a1_answers_25_set5 = { "Tăng tốc độ, rẽ phải trước xe tải và xe đạp",
				"Giảm tốc độ, rẽ phải sau xe tải và xe đạp", "Tăng tốc độ, rẽ phải trước xe đạp." };
		Answer a1_answer_25_set5 = new Answer(a1_answers_25_set5, 1, idList.get(124));
		answerRepo.save(a1_answer_25_set5);

		String[] a1_answers_1_set6 = { "Được phép", "Tùy trường hợp", "Không được phép." };
		Answer a1_answer_1_set6 = new Answer(a1_answers_1_set6, 2, idList.get(125));
		answerRepo.save(a1_answer_1_set6);

		String[] a1_answers_2_set6 = { "Người điều khiển: Xe ô tô, xe mô tô, xe đạp, xe gắn máy",
				"Người ngồi phía sau người điều khiển xe cơ giới", "Người đi bộ", "Cả ý 1 và ý 2." };
		Answer a1_answer_2_set6 = new Answer(a1_answers_2_set6, 0, idList.get(126));
		answerRepo.save(a1_answer_2_set6);

		String[] a1_answers_3_set6 = {
				"Gồm xe đạp (kể cả xe đạp máy, xe đạp điện), xe xích lô, xe lăn dùng cho người khuyết tật, xe súc vật kéo và các loại xe tương tự",
				"Gồm xe đạp (kể cả xe đạp máy, xe đạp điện), xe gắn máy, xe cơ giới dùng cho người khuyết tật và xe máy chuyên dùng",
				"Gồm xe ô tô, máy kéo, rơ moóc hoặc sơ mi rơ moóc được kéo bởi xe ô tô, máy kéo." };
		Answer a1_answer_3_set6 = new Answer(a1_answers_3_set6, 0, idList.get(127));
		answerRepo.save(a1_answer_3_set6);

		String[] a1_answers_4_set6 = { "Chỉ bị nhắc nhở",
				"Bị xử phạt hành chính hoặc có thể bị xử lý hình sự tùy theo mức độ vi phạm",
				"Không bị xử lý hình sự." };
		Answer a1_answer_4_set6 = new Answer(a1_answers_4_set6, 1, idList.get(128));
		answerRepo.save(a1_answer_4_set6);

		String[] a1_answers_5_set6 = { "Xe mô tô có dung tích xi-lanh 125 cm3",
				"Xe mô tô có dung tích xi-lanh từ 175 cm3 trở lên", "Xe mô tô có dung tích xi-lanh 100 cm3." };
		Answer a1_answer_5_set6 = new Answer(a1_answers_5_set6, -1, idList.get(129));
		answerRepo.save(a1_answer_5_set6);

		String[] a1_answers_6_set6 = { "Biển báo nguy hiểm", "Biển báo cấm", "Biển báo hiệu lệnh phải thi hành",
				"Biển báo chỉ dẫn." };
		Answer a1_answer_6_set6 = new Answer(a1_answers_6_set6, 3, idList.get(130));
		answerRepo.save(a1_answer_6_set6);

		String[] a1_answers_7_set6 = {
				"Nơi đường hẹp chỉ đủ cho một xe chạy và có chỗ tránh xe thì xe nào ở gần chỗ tránh hơn phải vào vị trí tránh, nhường đường cho xe kia đi",
				"Xe xuống dốc phải nhường đường cho xe đang lên dốc; xe nào có chướng ngại vật phía trước phải nhường đường cho xe không có chướng ngại vật đi trước",
				"Xe lên dốc phải nhường đường cho xe xuống dốc; xe nào không có chướng ngại vật phía trước phải nhường đường cho xe có chướng ngại vật đi trước",
				"Cả ý 1 và ý 2" };
		Answer a1_answer_7_set6 = new Answer(a1_answers_7_set6, 3, idList.get(131));
		answerRepo.save(a1_answer_7_set6);

		String[] a1_answers_8_set6 = { "Khi cho xe chạy thẳng", "Trước khi thay đổi làn đường",
				"Sau khi thay đổi làn đường." };
		Answer a1_answer_8_set6 = new Answer(a1_answers_8_set6, 1, idList.get(132));
		answerRepo.save(a1_answer_8_set6);

		String[] a1_answers_9_set6 = { "60 Km/h", "50 Km/h", "40 Km/h" };
		Answer a1_answer_9_set6 = new Answer(a1_answers_9_set6, 1, idList.get(133));
		answerRepo.save(a1_answer_9_set6);

		String[] a1_answers_10_set6 = { "Ra tín hiệu bằng tay rồi cho xe vượt qua",
				"Tăng ga mạnh để gây sự chú ý rồi cho xe vượt qua", "Bạn phải có tín hiệu bằng đèn hoặc còi." };
		Answer a1_answer_10_set6 = new Answer(a1_answers_10_set6, 2, idList.get(134));
		answerRepo.save(a1_answer_10_set6);

		String[] a1_answers_11_set6 = { "Bấm còi liên tục thúc giục các phương tiện phía trước nhường đường.",
				"Đi lên vỉa hè, tận dụng mọi khoảng trống để nhanh chóng thoát khỏi nơi ùn tắc.",
				"Lấn sang trái đường cố gắng vượt lên xe khác.", "Tất cả các ý nêu trên." };
		Answer a1_answer_11_set6 = new Answer(a1_answers_11_set6, 3, idList.get(135));
		answerRepo.save(a1_answer_11_set6);

		String[] a1_answers_12_set6 = {
				"Bật tín hiệu báo rẽ trước khi quay đầu, từ từ giảm tốc độ đến mức có thể dừng lại.",
				"Chỉ quay đầu xe tại những nơi được phép quay đầu.",
				"Quan sát an toàn các phương tiện tới từ phía trước, phía sau, hai bên đồng thời nhường đường cho xe từ bên phải và phía trước đi tới.",
				"Tất cả các ý nêu trên." };
		Answer a1_answer_12_set6 = new Answer(a1_answers_12_set6, 3, idList.get(136));
		answerRepo.save(a1_answer_12_set6);

		String[] a1_answers_13_set6 = { "Biển 1.", "Biển 2.", "Biển 3.", "Biển 2 và 3." };
		Answer a1_answer_13_set6 = new Answer(a1_answers_13_set6, 0, idList.get(137));
		answerRepo.save(a1_answer_13_set6);

		String[] a1_answers_14_set6 = { "Biển 1.", "Biển 2.", "Biển 3.", "Biển 1 và 3." };
		Answer a1_answer_14_set6 = new Answer(a1_answers_14_set6, 0, idList.get(138));
		answerRepo.save(a1_answer_14_set6);

		String[] a1_answers_15_set6 = { "Biển 1", "Biển 2 và 3", "Cả ba biển." };
		Answer a1_answer_15_set6 = new Answer(a1_answers_15_set6, 1, idList.get(139));
		answerRepo.save(a1_answer_15_set6);

		String[] a1_answers_16_set6 = { "Biển 1.", "Biển 2.", "Cả hai biển." };
		Answer a1_answer_16_set6 = new Answer(a1_answers_16_set6, 0, idList.get(140));
		answerRepo.save(a1_answer_16_set6);

		String[] a1_answers_17_set6 = { "Biển 1.", "Biển 2.", "Cả hai biển." };
		Answer a1_answer_17_set6 = new Answer(a1_answers_17_set6, 0, idList.get(141));
		answerRepo.save(a1_answer_17_set6);

		String[] a1_answers_18_set6 = { "Biển 1.", "Biển 1 và 3.", "Biển 3.", "Cả ba biển." };
		Answer a1_answer_18_set6 = new Answer(a1_answers_18_set6, 2, idList.get(142));
		answerRepo.save(a1_answer_18_set6);

		String[] a1_answers_19_set6 = { "Biển 1", "Biển 2", "Biển 3." };
		Answer a1_answer_19_set6 = new Answer(a1_answers_19_set6, 1, idList.get(143));
		answerRepo.save(a1_answer_19_set6);

		String[] a1_answers_20_set6 = { "Dừng xe tại khu vực có trạm Cảnh sát giao thông.",
				"Tiếp tục lưu thông với tốc độ bình thường",
				"Phải giảm tốc độ đến mức an toàn và không được vượt khi đi qua khu vực này." };
		Answer a1_answer_20_set6 = new Answer(a1_answers_20_set6, 2, idList.get(144));
		answerRepo.save(a1_answer_20_set6);

		String[] a1_answers_21_set6 = { "Các xe phía tay phải và tay trái của người điều khiển được phép đi thẳng",
				"Cho phép các xe ở mọi hướng được rẽ phải",
				"Tất cả các xe phải dừng lại trước ngã tư, trừ những xe đã ở trong ngã tư được phép tiếp tục đi." };
		Answer a1_answer_21_set6 = new Answer(a1_answers_21_set6, 2, idList.get(145));
		answerRepo.save(a1_answer_21_set6);

		String[] a1_answers_22_set6 = { "Xe khách, xe tải, mô tô", "Xe tải, xe con, mô tô",
				"Xe khách, xe con, mô tô." };
		Answer a1_answer_22_set6 = new Answer(a1_answers_22_set6, 0, idList.get(146));
		answerRepo.save(a1_answer_22_set6);

		String[] a1_answers_23_set6 = { "Cả hai xe", "Không xe nào vi phạm", "Chỉ xe mô tô vi phạm",
				"Chỉ xe tải vi phạm." };
		Answer a1_answer_23_set6 = new Answer(a1_answers_23_set6, 0, idList.get(147));
		answerRepo.save(a1_answer_23_set6);

		String[] a1_answers_24_set6 = { "Cho phép", "Không được vượt." };
		Answer a1_answer_24_set6 = new Answer(a1_answers_24_set6, 1, idList.get(148));
		answerRepo.save(a1_answer_24_set6);

		String[] a1_answers_25_set6 = { "Xe con", "Xe mô tô", "Cả 2 xe đều đúng." };
		Answer a1_answer_25_set6 = new Answer(a1_answers_25_set6, 0, idList.get(149));
		answerRepo.save(a1_answer_25_set6);

		String[] a1_answers_1_set7 = { "Được mang, vác tùy trường hợp cụ thể", "Không được mang, vác",
				"Được mang, vác nhưng phải đảm bảo an toàn", "Được mang, vác tùy theo sức khỏe của bản thân." };
		Answer a1_answer_1_set7 = new Answer(a1_answers_1_set7, 1, idList.get(150));
		answerRepo.save(a1_answer_1_set7);

		String[] a1_answers_2_set7 = { "Phần mặt đường và lề đường", "Phần đường xe chạy", "Phần đường xe cơ giới." };
		Answer a1_answer_2_set7 = new Answer(a1_answers_2_set7, 1, idList.get(151));
		answerRepo.save(a1_answer_2_set7);

		String[] a1_answers_3_set7 = { "Phương tiện giao thông cơ giới đường bộ",
				"Phương tiện giao thông thô sơ đường bộ và xe máy chuyên dùng", "Cả ý 1 và 2" };
		Answer a1_answer_3_set7 = new Answer(a1_answers_3_set7, 2, idList.get(152));
		answerRepo.save(a1_answer_3_set7);

		String[] a1_answers_4_set7 = { "Bất cứ đèn nào miễn là mắt nhìn rõ phía trước",
				"Chỉ bật đèn chiếu xa (đèn pha) khi không nhìn rõ đường",
				"Đèn chiếu xa (đèn pha) khi đường vắng, đèn pha chiếu gần (đèn cốt) khi có xe đi ngược chiều.",
				"Đèn chiếu gần (đèn cốt)." };
		Answer a1_answer_4_set7 = new Answer(a1_answers_4_set7, 3, idList.get(153));
		answerRepo.save(a1_answer_4_set7);

		String[] a1_answers_5_set7 = { "Xe mô tô hai bánh có dung tích xi-lanh từ 50 cm3 đến dưới 175 cm3",
				"Xe mô tô ba bánh dùng cho người khuyết tật", "Cả ý 1 và ý 2." };
		Answer a1_answer_5_set7 = new Answer(a1_answers_5_set7, 2, idList.get(154));
		answerRepo.save(a1_answer_5_set7);

		String[] a1_answers_6_set7 = { "Biển báo hiệu cố định", "Báo hiệu tạm thời." };
		Answer a1_answer_6_set7 = new Answer(a1_answers_6_set7, 1, idList.get(155));
		answerRepo.save(a1_answer_6_set7);

		String[] a1_answers_7_set7 = { "Tiếp tục đi và xe lên dốc phải nhường đường cho mình",
				"Nhường đường cho xe lên dốc", "Chỉ nhường đường khi xe lên dốc nháy đèn." };
		Answer a1_answer_7_set7 = new Answer(a1_answers_7_set7, 1, idList.get(156));
		answerRepo.save(a1_answer_7_set7);

		String[] a1_answers_8_set7 = { "Xe bị vượt bất ngờ tăng tốc độ và cố tình không nhường đường",
				"Xe bị vượt giảm tốc độ và nhường đường", "Phát hiện có xe đi ngược chiều", "Cả ý 1 và ý 3." };
		Answer a1_answer_8_set7 = new Answer(a1_answers_8_set7, 3, idList.get(157));
		answerRepo.save(a1_answer_8_set7);

		String[] a1_answers_9_set7 = { "Ô tô con, ô tô tải, ô tô chở người trên 30 chỗ",
				"Xe gắn máy, xe máy chuyên dung", "Cả ý 1 và ý 2" };
		Answer a1_answer_9_set7 = new Answer(a1_answers_9_set7, 0, idList.get(158));
		answerRepo.save(a1_answer_9_set7);

		String[] a1_answers_10_set7 = { "Đường ướt, đường có sỏi cát trên nền đường",
				"Đường hẹp có nhiều điểm giao cắt từ hai phía", "Đường đèo dốc, vòng liên tục",
				"Tất cả các ý nêu trên." };
		Answer a1_answer_10_set7 = new Answer(a1_answers_10_set7, 3, idList.get(159));
		answerRepo.save(a1_answer_10_set7);

		String[] a1_answers_11_set7 = {
				"Thực hiện sơ cứu ban đầu trong trường hợp khẩn cấp; thông báo vụ tai nạn đến cơ quan thi hành pháp luật.",
				"Nhanh chóng lái xe gây tai nạn hoặc đi nhờ xe khác ra khỏi hiện trường vụ tai nạn.",
				"Cả ý 1 và ý 2." };
		Answer a1_answer_11_set7 = new Answer(a1_answers_11_set7, 0, idList.get(160));
		answerRepo.save(a1_answer_11_set7);

		String[] a1_answers_12_set7 = { "Để điều khiển xe chạy về phía trước.",
				"Để điều tiết công suất động cơ qua đó điều khiển tốc độ của xe.", "Để điều khiển xe chạy lùi.",
				"Cả ý 1 và ý 2." };
		Answer a1_answer_12_set7 = new Answer(a1_answers_12_set7, 3, idList.get(161));
		answerRepo.save(a1_answer_12_set7);

		String[] a1_answers_13_set7 = { "Biển 1 và 3.", "Biển 2.", "Biển 3." };
		Answer a1_answer_13_set7 = new Answer(a1_answers_13_set7, 1, idList.get(162));
		answerRepo.save(a1_answer_13_set7);

		String[] a1_answers_14_set7 = { "Biển 1 và 2.", "Biển 1 và 3.", "Biển 2 và 3.", "Cả ba biển." };
		Answer a1_answer_14_set7 = new Answer(a1_answers_14_set7, 0, idList.get(163));
		answerRepo.save(a1_answer_14_set7);

		String[] a1_answers_15_set7 = { "Biển 1", "Biển 2" };
		Answer a1_answer_15_set7 = new Answer(a1_answers_15_set7, 1, idList.get(164));
		answerRepo.save(a1_answer_15_set7);

		String[] a1_answers_16_set7 = { "Biển 1.", "Biển 2.", "Không biển nào." };
		Answer a1_answer_16_set7 = new Answer(a1_answers_16_set7, 1, idList.get(165));
		answerRepo.save(a1_answer_16_set7);

		String[] a1_answers_17_set7 = { "Biển 1.", "Biển 2.", "Biển 3.", "Biển 1 và 2." };
		Answer a1_answer_17_set7 = new Answer(a1_answers_17_set7, 3, idList.get(166));
		answerRepo.save(a1_answer_17_set7);

		String[] a1_answers_18_set7 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a1_answer_18_set7 = new Answer(a1_answers_18_set7, 0, idList.get(167));
		answerRepo.save(a1_answer_18_set7);

		String[] a1_answers_19_set7 = { "Biển 1.", "Biển 2.", "Không biển nào." };
		Answer a1_answer_19_set7 = new Answer(a1_answers_19_set7, 1, idList.get(168));
		answerRepo.save(a1_answer_19_set7);

		String[] a1_answers_20_set7 = { "Đi thẳng hoặc rẽ trái trên cầu vượt", "Đi thẳng hoặc rẽ phải trên cầu vượt",
				"Báo hiệu cầu vượt liên thông." };
		Answer a1_answer_20_set7 = new Answer(a1_answers_20_set7, 2, idList.get(169));
		answerRepo.save(a1_answer_20_set7);

		String[] a1_answers_21_set7 = { "Mô tô, xe con", "Xe con, xe tải", "Mô tô, xe tải", "Cả ba xe." };
		Answer a1_answer_21_set7 = new Answer(a1_answers_21_set7, 2, idList.get(170));
		answerRepo.save(a1_answer_21_set7);

		String[] a1_answers_22_set7 = { "Xe con", "Xe tải", "Xe con và xe tải." };
		Answer a1_answer_22_set7 = new Answer(a1_answers_22_set7, 1, idList.get(171));
		answerRepo.save(a1_answer_22_set7);

		String[] a1_answers_23_set7 = { "Chỉ mô tô", "Chỉ xe tải", "Cả 3 xe", "Chỉ mô tô và xe tải." };
		Answer a1_answer_23_set7 = new Answer(a1_answers_23_set7, 2, idList.get(172));
		answerRepo.save(a1_answer_23_set7);

		String[] a1_answers_24_set7 = { "Xe mô tô", "Xe ô tô con", "Không xe nào vi phạm", "Cả hai xe." };
		Answer a1_answer_24_set7 = new Answer(a1_answers_24_set7, 3, idList.get(173));
		answerRepo.save(a1_answer_24_set7);

		String[] a1_answers_25_set7 = { "Quan sát nếu thấy không có tàu thì tăng tốc, cho xe vượt qua đường sắt",
				"Dừng lại trước rào chắn một khoảng cách an toàn",
				"Ra tín hiệu, yêu cầu người gác chắn tàu kéo chậm Barie để xe bạn qua." };
		Answer a1_answer_25_set7 = new Answer(a1_answers_25_set7, 1, idList.get(174));
		answerRepo.save(a1_answer_25_set7);

		String[] a1_answers_1_set8 = { "Được phép", "Được bám trong trường hợp phương tiện của mình bị hỏng",
				"Được kéo, đẩy trong trường hợp phương tiện khác bị hỏng", "Không được phép." };
		Answer a1_answer_1_set8 = new Answer(a1_answers_1_set8, 3, idList.get(175));
		answerRepo.save(a1_answer_1_set8);

		String[] a1_answers_2_set8 = {
				"Là một phần của phần đường xe chạy được chia theo chiều dọc của đường, sử dụng cho xe chạy.",
				"Là một phần của phần đường xe chạy được chia theo chiều dọc của đường, có bề rộng đủ cho xe chạy an toàn",
				"Là đường cho xe ô tô chạy, dừng, đỗ an toàn." };
		Answer a1_answer_2_set8 = new Answer(a1_answers_2_set8, 1, idList.get(176));
		answerRepo.save(a1_answer_2_set8);

		String[] a1_answers_3_set8 = { "Người điều khiển xe cơ giới, người điều khiển xe thô sơ",
				"Người điều khiển xe máy chuyên dùng tham gia giao thông đường bộ.", "Cả ý 1 và ý 2" };
		Answer a1_answer_3_set8 = new Answer(a1_answers_3_set8, 2, idList.get(177));
		answerRepo.save(a1_answer_3_set8);

		String[] a1_answers_4_set8 = { "Từ 22 giờ đêm đến 5 giờ sáng", "Từ 5 giờ sáng đến 22 giờ tối",
				"Từ 23 giờ đêm đến 5 giờ sáng hôm sau." };
		Answer a1_answer_4_set8 = new Answer(a1_answers_4_set8, 1, idList.get(178));
		answerRepo.save(a1_answer_4_set8);

		String[] a1_answers_5_set8 = { "Người tham gia giao thông ở các hướng phải dừng lại.",
				"Người tham gia giao thông ở các hướng được đi theo chiều gậy chỉ của cảnh sát giao thông",
				"Người tham gia giao thông ở phía trước và phía sau người điều khiển được đi tất cả các hướng, người tham gia giao thông ở phía bên phải và phía bên trái người điều khiển phải dừng lại",
				"Người tham gia giao thông ở phía trước và phía sau người điều khiển phải dừng lại; người tham gia giao thông ở phía bên phải và bên trái người điều khiển được đi tất cả các hướng." };
		Answer a1_answer_5_set8 = new Answer(a1_answers_5_set8, 3, idList.get(179));
		answerRepo.save(a1_answer_5_set8);

		String[] a1_answers_6_set8 = { "02 Năm", "03 Năm", "05 Năm", "04 Năm." };
		Answer a1_answer_6_set8 = new Answer(a1_answers_6_set8, 2, idList.get(180));
		answerRepo.save(a1_answer_6_set8);

		String[] a1_answers_7_set8 = { "Nhường đường cho xe đi ở bên phải mình tới",
				"Nhường đường cho xe đi ở bên trái mình tới",
				"Nhường đường cho xe đi trên đường ưu tiên hoặc đường chính từ bất kỳ hướng nào tới." };
		Answer a1_answer_7_set8 = new Answer(a1_answers_7_set8, 2, idList.get(181));
		answerRepo.save(a1_answer_7_set8);

		String[] a1_answers_8_set8 = {
				"Nhường đường cho người đi bộ đang đi trên phần đường dành cho người đi bộ sang đường; nhường đường cho xe đi trên đường ưu tiên, đường chính từ bất kì hướng nào tới; nhường đường cho xe ưu tiên, xe đi từ bên phải đến",
				"Nhường đường cho người đi bộ đang đứng chờ đi qua phần đường dành cho người đi bộ sang đường; nhường đường cho xe đi trên đường ngược chiều, đường nhánh từ bất kỳ hướng nào tới; nhường đường cho xe đi từ bên trái đến",
				"Không phải nhường đường." };
		Answer a1_answer_8_set8 = new Answer(a1_answers_8_set8, 0, idList.get(182));
		answerRepo.save(a1_answer_8_set8);

		String[] a1_answers_9_set8 = { "Chủ động giữ khoảng cách an toàn phù hợp với xe chạy liền trước xe của mình",
				"Đảm bảo khoảng cách an toàn theo mật độ phương tiện, tình hình giao thông thực tế", "Cả ý 1 và ý 2." };
		Answer a1_answer_9_set8 = new Answer(a1_answers_9_set8, 2, idList.get(183));
		answerRepo.save(a1_answer_9_set8);

		String[] a1_answers_10_set8 = { "Tăng tốc độ nhanh chóng vượt qua bên đỗ",
				"Giảm tốc độ đến mức an toàn có thể và quan sát người qua đường và từ từ vượt qua xe buýt",
				"Yêu cầu phải dừng lại phía sau xe buýt chờ xe rời bến mới đi tiếp." };
		Answer a1_answer_10_set8 = new Answer(a1_answers_10_set8, 1, idList.get(184));
		answerRepo.save(a1_answer_10_set8);

		String[] a1_answers_11_set8 = {
				"Không nên đi cố vào đường hẹp; xe đi ở phía sườn núi nên dừng lại trước để nhường đường; khi dừng xe nhường đường phải đổ ngay ngắn.",
				"Trong khi tránh nhau không nên đổi số; khi tránh nhau ban đêm phải tắt đèn pha, bật đèn cốt.",
				"Khi tránh nhau ban đêm, phải thường xuyên bật đèn pha tắt đèn cốt.", "Cả ý 1 và ý 2." };
		Answer a1_answer_11_set8 = new Answer(a1_answers_11_set8, 3, idList.get(185));
		answerRepo.save(a1_answer_11_set8);

		String[] a1_answers_12_set8 = { "Sử dụng còi.", "Phanh đồng thời cả phanh trước và phanh sau.",
				"Chỉ sử dụng phanh trước." };
		Answer a1_answer_12_set8 = new Answer(a1_answers_12_set8, 2, idList.get(186));
		answerRepo.save(a1_answer_12_set8);

		String[] a1_answers_13_set8 = { "Biển 1 và 2.", "Biển 1 và 3.", "Biển 2 và 3.", "Cả ba biển." };
		Answer a1_answer_13_set8 = new Answer(a1_answers_13_set8, 1, idList.get(187));
		answerRepo.save(a1_answer_13_set8);

		String[] a1_answers_14_set8 = { "Biển 1.", "Biển 1 và 3.", "Biển 2 và 3.", "Cả ba biển." };
		Answer a1_answer_14_set8 = new Answer(a1_answers_14_set8, 2, idList.get(188));
		answerRepo.save(a1_answer_14_set8);

		String[] a1_answers_15_set8 = { "Biển 1", "Biển 2", "Biển 3." };
		Answer a1_answer_15_set8 = new Answer(a1_answers_15_set8, 1, idList.get(189));
		answerRepo.save(a1_answer_15_set8);

		String[] a1_answers_16_set8 = { "Biển 1 và 2.", "Biển 1 và 3.", "Biển 2 và 3.", "Cả ba biển." };
		Answer a1_answer_16_set8 = new Answer(a1_answers_16_set8, 0, idList.get(190));
		answerRepo.save(a1_answer_16_set8);

		String[] a1_answers_17_set8 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a1_answer_17_set8 = new Answer(a1_answers_17_set8, 2, idList.get(191));
		answerRepo.save(a1_answer_17_set8);

		String[] a1_answers_18_set8 = { "Biển 1", "Biển 2", "Không biển nào." };
		Answer a1_answer_18_set8 = new Answer(a1_answers_18_set8, 2, idList.get(192));
		answerRepo.save(a1_answer_18_set8);

		String[] a1_answers_19_set8 = { "Biển 1.", "Biển 2.", "Cả 2 biển." };
		Answer a1_answer_19_set8 = new Answer(a1_answers_19_set8, 2, idList.get(193));
		answerRepo.save(a1_answer_19_set8);

		String[] a1_answers_20_set8 = { "Vạch 1", "Vạch 2", "Vạch 3", "Vạch 1 và 2." };
		Answer a1_answer_20_set8 = new Answer(a1_answers_20_set8, 3, idList.get(194));
		answerRepo.save(a1_answer_20_set8);

		String[] a1_answers_21_set8 = { "Xe công an, xe quân sự, xe con + mô tô",
				"Xe quân sự, xe công an, xe con + mô tô", "Xe mô tô + xe con, xe quân sự, xe công an." };
		Answer a1_answer_21_set8 = new Answer(a1_answers_21_set8, 1, idList.get(195));
		answerRepo.save(a1_answer_21_set8);

		String[] a1_answers_22_set8 = { "Xe con, xe tải, xe khách", "Xe tải, xe khách, xe mô tô",
				"Xe khách, xe mô tô, xe con", "Cả bốn xe." };
		Answer a1_answer_22_set8 = new Answer(a1_answers_22_set8, 1, idList.get(196));
		answerRepo.save(a1_answer_22_set8);

		String[] a1_answers_23_set8 = { "Xe tải, xe con, mô tô", "Xe con, xe tải, mô tô", "Mô tô, xe con, xe tải",
				"Xe con, mô tô, xe tải." };
		Answer a1_answer_23_set8 = new Answer(a1_answers_23_set8, 2, idList.get(197));
		answerRepo.save(a1_answer_23_set8);

		String[] a1_answers_24_set8 = { "Đúng", "Không đúng." };
		Answer a1_answer_24_set8 = new Answer(a1_answers_24_set8, 1, idList.get(198));
		answerRepo.save(a1_answer_24_set8);

		String[] a1_answers_25_set8 = { "Vượt về phía bên phải để đi tiếp",
				"Giảm tốc độ chờ xe container rẽ xong rồi tiếp tục đi", "Vượt về phía bên trái để đi tiếp." };
		Answer a1_answer_25_set8 = new Answer(a1_answers_25_set8, 1, idList.get(199));
		answerRepo.save(a1_answer_25_set8);

		// Câu trả lời câu hỏi bằng A2
		String[] a2_answers_1_set1 = { "Phần mặt đường và lề đường.", "Phần đường xe chạy.", "Phần đường xe cơ giới." };
		Answer a2_answer_1_set1 = new Answer(a2_answers_1_set1, 1, idList.get(0));
		answerRepo.save(a2_answer_1_set1);

		String[] a2_answers_2_set1 = { "Diễn ra trên đường phố không có người qua lại.", "Được người dân ủng hộ.",
				"Được cơ quan có thẩm quyền cấp phép." };
		Answer a2_answer_2_set1 = new Answer(a2_answers_2_set1, 2, idList.get(1));
		answerRepo.save(a2_answer_2_set1);

		String[] a2_answers_3_set1 = { "Được phép.", "Không được phép.", "Tùy từng trường hợp." };
		Answer a2_answer_3_set1 = new Answer(a2_answers_3_set1, 1, idList.get(2));
		answerRepo.save(a2_answer_3_set1);

		String[] a2_answers_4_set1 = { "Xe mô tô có dung tích xi-lanh 125 cm3.",
				"Xe mô tô có dung tích xi-lanh từ 175 cm3 trở lên.", "Xe mô tô có dung tích xi-lanh 100 cm3." };
		Answer a2_answer_4_set1 = new Answer(a2_answers_4_set1, 1, idList.get(3));
		answerRepo.save(a2_answer_4_set1);

		String[] a2_answers_5_set1 = {
				"Tăng tốc độ và ra hiệu cho xe sau vượt, không được gây trở ngại cho xe sau vượt.",
				"Người điều khiển phương tiện phía trước phải giảm tốc độ, đi sát về bên phải của phần đường xe chạy cho đến khi xe sau đã vượt qua, không được gây trở ngại cho xe sau vượt.",
				"Cho xe tránh về bên trái mình và ra hiệu cho xe sau vượt; nếu có chướng ngại vật phía trước hoặc thiếu điều kiện an toàn chưa cho vượt được phải ra hiệu cho xe sau biết; cấm gây trở ngại cho xe xin vượt." };
		Answer a2_answer_5_set1 = new Answer(a2_answers_5_set1, 1, idList.get(4));
		answerRepo.save(a2_answer_5_set1);

		String[] a2_answers_6_set1 = { "Được phép nhưng phải đảm bảo an toàn.", "Không được phép.",
				"Được phép tùy từng hoàn cảnh, điều kiện cụ thể." };
		Answer a2_answer_6_set1 = new Answer(a2_answers_6_set1, 1, idList.get(5));
		answerRepo.save(a2_answer_6_set1);

		String[] a2_answers_7_set1 = {
				"Ô tô kéo rơ moóc, ô tô kéo xe khác, ô tô trộn vữa, ô tô trộn bê tông, ô tô xi téc.",
				"Ô tô chở người trên 30 chỗ (trừ ô tô buýt), ô tô tải có trọng tải trên 3,5 tấn.",
				"Xe ô tô con, xe ô tô chở người đến 30 chỗ (trừ xe buýt), ô tô tải có trọng tải nhỏ hơn hoặc bằng 3,5 tấn." };
		Answer a2_answer_7_set1 = new Answer(a2_answers_7_set1, 0, idList.get(6));
		answerRepo.save(a2_answer_7_set1);

		String[] a2_answers_8_set1 = { "Giảm tốc độ cho xe vượt qua đường sắt.",
				"Nhanh chóng cho xe vượt qua đường sắt trước khi tàu hỏa tới.",
				"Giảm tốc độ cho xe vượt qua đường sắt trước khi tàu hỏa tới.",
				"Cho xe dừng ngay lại và giữ khoảng cách tối thiểu 5 mét tính từ ray gần nhất." };
		Answer a2_answer_8_set1 = new Answer(a2_answers_8_set1, 3, idList.get(7));
		answerRepo.save(a2_answer_8_set1);

		String[] a2_answers_9_set1 = { "Cho xe chạy thật nhanh qua vũng nước.",
				"Giảm tốc độ cho xe chạy chậm qua vũng nước.",
				"Giảm tốc độ cho xe chạy qua làn đường dành cho mô tô để tránh vũng nước." };
		Answer a2_answer_9_set1 = new Answer(a2_answers_9_set1, 1, idList.get(8));
		answerRepo.save(a2_answer_9_set1);

		String[] a2_answers_10_set1 = {
				"Không nên đi cố vào đường hẹp; xe đi ở phía sườn núi nên | dừng lại trước để nhường đường, khi dừng xe nhườngđường phải đỗ ngay ngắn.",
				"Trong khi tránh nhau không nên đối số; khi tránh nhau ban đêm, phải tắt đèn pha bật đèn cốt.",
				"Khi tránh nhau ban đêm, phải thường xuyên bật đèn pha tắt đèn cốt.", "Cả ý 1 và ý 2." };
		Answer a2_answer_10_set1 = new Answer(a2_answers_10_set1, 3, idList.get(9));
		answerRepo.save(a2_answer_10_set1);

		String[] a2_answers_11_set1 = {
				"Là loại động cơ: Để hoàn thành một chu trình công tác của động cơ, pít tông thực hiện 2 (hai) hành trình, trong đó có một lần sinh công.",
				"Là loại động cơ: Để hoàn thành một chu trình công tác của động cơ, pít tông thực hiện 4 (bốn) hành trình, trong đó có một lần sinh công." };
		Answer a2_answer_11_set1 = new Answer(a2_answers_11_set1, 1, idList.get(10));
		answerRepo.save(a2_answer_11_set1);

		String[] a2_answers_12_set1 = { "Biển 1.", "Biển 2.", "Cả hai biển." };
		Answer a2_answer_12_set1 = new Answer(a2_answers_12_set1, 0, idList.get(11));
		answerRepo.save(a2_answer_12_set1);

		String[] a2_answers_13_set1 = { "Biển 1.", "Biển 2.", "Biển 3.", "Biển 1 và biển 2." };
		Answer a2_answer_13_set1 = new Answer(a2_answers_13_set1, 3, idList.get(12));
		answerRepo.save(a2_answer_13_set1);

		String[] a2_answers_14_set1 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_14_set1 = new Answer(a2_answers_14_set1, 1, idList.get(13));
		answerRepo.save(a2_answer_14_set1);

		String[] a2_answers_15_set1 = { "Biển 1.", "Biển 1 và 3.", "Biến 3.", "Cả ba biển." };
		Answer a2_answer_15_set1 = new Answer(a2_answers_15_set1, 2, idList.get(14));
		answerRepo.save(a2_answer_15_set1);

		String[] a2_answers_16_set1 = { "Biển 1.", "Biển 2.", "Biển 3.", "Cả ba biển." };
		Answer a2_answer_16_set1 = new Answer(a2_answers_16_set1, 2, idList.get(15));
		answerRepo.save(a2_answer_16_set1);

		String[] a2_answers_17_set1 = { "Biển 1.", "Biển 2.", "Biển 3.", "Biển 1 và Biển 2." };
		Answer a2_answer_17_set1 = new Answer(a2_answers_17_set1, 2, idList.get(16));
		answerRepo.save(a2_answer_17_set1);

		String[] a2_answers_18_set1 = {
				"Để báo trước gần tới đoạn đường có hiện tượng đất đá từ trên ta luy dương sụt lở bất ngờ gây nguy hiểm cho xe cộ và người đi đường.",
				"Để báo trước nơi có kết cấu mặt đường rời rạc, khi phương tiện đi qua, làm cho các viên đá, sỏi văng lên gây nguy hiếm và mất an toàn cho người và phương tiện tham gia giao thông.",
				"Để cảnh báo những đoạn nền đường yếu, đoạn đường đang theo dõi lún mà việc vận hành xe ở tốc độ cao có thể gây nguy hiểm." };
		Answer a2_answer_18_set1 = new Answer(a2_answers_18_set1, 0, idList.get(17));
		answerRepo.save(a2_answer_18_set1);

		String[] a2_answers_19_set1 = { "Biển 1.", "Biển 2.", "Biển 3.", "Không biển nào." };
		Answer a2_answer_19_set1 = new Answer(a2_answers_19_set1, 2, idList.get(18));
		answerRepo.save(a2_answer_19_set1);

		String[] a2_answers_20_set1 = { "Đi thẳng hoặc rẽ trái trên cầu vượt.", "Đi thẳng hoặc rẽ phải trên cầu vượt.",
				"Báo hiệu cầu vượt liên thông." };
		Answer a2_answer_20_set1 = new Answer(a2_answers_20_set1, 2, idList.get(19));
		answerRepo.save(a2_answer_20_set1);

		String[] a2_answers_21_set1 = { "Vạch 1.", "Vạch 2.", "Vạch 3.", "Vạch 1 và 3." };
		Answer a2_answer_21_set1 = new Answer(a2_answers_21_set1, 3, idList.get(20));
		answerRepo.save(a2_answer_21_set1);

		String[] a2_answers_22_set1 = { "Hướng 2, 3, 4.", "Chỉ hướng 1.", "Hướng 1 và 2.", "Hướng 3 và 4." };
		Answer a2_answer_22_set1 = new Answer(a2_answers_22_set1, 1, idList.get(21));
		answerRepo.save(a2_answer_22_set1);

		String[] a2_answers_23_set1 = { "Các xe ở phía tay phải và tay trái của người điều khiển được phép đi thắng.",
				"Cho phép các xe ở mọi hướng được rẽ phải.",
				"Tất cả các xe phải dừng lại trước ngã tư, trừ những xe đã ở trong ngã tư được phép tiếp tục đi." };
		Answer a2_answer_23_set1 = new Answer(a2_answers_23_set1, 2, idList.get(22));
		answerRepo.save(a2_answer_23_set1);

		String[] a2_answers_24_set1 = { "Xe của bạn.", "Xe tải." };
		Answer a2_answer_24_set1 = new Answer(a2_answers_24_set1, 1, idList.get(23));
		answerRepo.save(a2_answer_24_set1);

		String[] a2_answers_25_set1 = { "Tăng tốc độ, rẽ phải trước xe tải và xe đạp.",
				"Giảm tốc độ, rẽ phải sau xe tải và xe đạp.", "Tăng tốc độ, rẽ phải trước xe đạp." };
		Answer a2_answer_25_set1 = new Answer(a2_answers_25_set1, 1, idList.get(24));
		answerRepo.save(a2_answer_25_set1);

		String[] a2_answers_1_set2 = {
				"Là một phần của phần đường xe chạy được chia theo chiều dọc của đường, sử dụng cho xe chạy.",
				"Là một phần của phần đường xe chạy được chia theo chiều dọc của đường, có bề rộng đủ cho xe chạy an toàn.",
				"Là đường cho xe ô tô chạy, dừng, đỗ an toàn." };
		Answer a2_answer_1_set2 = new Answer(a2_answers_1_set2, 1, idList.get(25));
		answerRepo.save(a2_answer_1_set2);

		String[] a2_answers_2_set2 = { "Bị nghiêm cấm.", "Không bị nghiêm cấm.",
				"Không bị nghiêm cấm, nếu có chất ma tuý ở mức nhẹ, có thể điều khiển phương tiện tham gia giao thông." };
		Answer a2_answer_2_set2 = new Answer(a2_answers_2_set2, 0, idList.get(26));
		answerRepo.save(a2_answer_2_set2);

		String[] a2_answers_3_set2 = { "Không được vượt.", "Được vượt khi đang đi trên cầu.",
				"Được phép vượt khi đi qua nơi giao nhau có ít phương tiện cùng tham gia giao thông.",
				"Được vượt khi đảm bảo an toàn." };
		Answer a2_answer_3_set2 = new Answer(a2_answers_3_set2, 3, idList.get(27));
		answerRepo.save(a2_answer_3_set2);

		String[] a2_answers_4_set2 = { "Xe mô tô hai bánh có dung tích xi-lanh từ 50 cm3 đến dưới 175 cm3.",
				"Xe mô tô ba bánh dùng cho người khuyết tật.", "Cả ý 1 và ý 2." };
		Answer a2_answer_4_set2 = new Answer(a2_answers_4_set2, 2, idList.get(28));
		answerRepo.save(a2_answer_4_set2);

		String[] a2_answers_5_set2 = { "Ở nơi đường giao nhau và nơi có biển báo cho phép quay đầu xe.",
				"Ở nơi có đường rộng để cho các loại xe chạy một chiều.", "Ở bất kỳ nơi nào." };
		Answer a2_answer_5_set2 = new Answer(a2_answers_5_set2, 0, idList.get(29));
		answerRepo.save(a2_answer_5_set2);

		String[] a2_answers_6_set2 = {
				"Khi có báo hiệu cảnh báo nguy hiểm hoặc có chướng ngại vật trên đường, khi chuyển hướng xe chạy hoặc tầm nhìn bị hạn chế; khi qua nơi đường giao nhau, nơi đường bộ giao nhau với đường sắt, đường vòng; đường có địa hình quanh co, đèo dốc.",
				"Khi qua cầu, cống hẹp; khi lên gần đỉnh dốc, khi xuống dốc, khi qua trường học, khu đông dân cư, khu vực đang thi công trên đường bộ; hiện trường xảy ra tai nạn giao thông.",
				"Khi điều khiển xe vượt xe khác trên đường quốc lộ, đường | cao tốc.", "Cả ý 1 và ý 2." };
		Answer a2_answer_6_set2 = new Answer(a2_answers_6_set2, 3, idList.get(30));
		answerRepo.save(a2_answer_6_set2);

		String[] a2_answers_7_set2 = { "Ô tô kéo rơ moóc, ô tô kéo xe khác, xe gắn máy.",
				"Ô tô chở người trên 30 chỗ (trừ ô tô buýt), ô tô tải có trọng tải trên 3,5 tấn.",
				"Xe ô tô con, xe ô tô chở người đến 30 chỗ (trừ xe buýt), ô tô tải có trọng tải nhỏ hơn hoặc bằng 3,5 tấn." };
		Answer a2_answer_7_set2 = new Answer(a2_answers_7_set2, 2, idList.get(31));
		answerRepo.save(a2_answer_7_set2);

		String[] a2_answers_8_set2 = { "Đi đúng làn đường, đúng tốc độ quy định, không được vượt xe khác.",
				"Đi sang làn đường của xe ngược chiều để mở rộng tầm nhìn và vượt xe khác.",
				"Cho xe đi sát bên phải làn đường, bật tín hiệu báo hiệu để vượt bên phải xe khác." };
		Answer a2_answer_8_set2 = new Answer(a2_answers_8_set2, 0, idList.get(32));
		answerRepo.save(a2_answer_8_set2);

		String[] a2_answers_9_set2 = { "Là bình thường.", "Là thiếu văn hóa giao thông.", "Là có văn hóa giao thông." };
		Answer a2_answer_9_set2 = new Answer(a2_answers_9_set2, 1, idList.get(33));
		answerRepo.save(a2_answer_9_set2);

		String[] a2_answers_10_set2 = {
				"Quan sát cẩn thận các chướng ngại vật và báo hiệu bằng còi, đèn; giảm tốc độ tới mức cần thiết, về số thấp và thực hiện quay vòng với tốc độ phù hợp với bán kính cong của đường vòng.",
				"Quan sát cẩn thận các chướng ngại vật và báo hiệu bằng còi, đèn; tăng tốc để nhanh chóng qua đường vòng và giảm tốc độ sau khi qua đường vòng." };
		Answer a2_answer_10_set2 = new Answer(a2_answers_10_set2, 0, idList.get(34));
		answerRepo.save(a2_answer_10_set2);

		String[] a2_answers_11_set2 = {
				"Có tác dụng cảnh báo cho các xe phía sau biết xe đang giảm tốc độ để chủ động tránh hoặc giảm tốc để tránh va chạm.",
				"Có tác dụng định vị vào ban đêm với các xe từ phía sau tới để tránh va chạm.", "Cả ý 1 và ý 2." };
		Answer a2_answer_11_set2 = new Answer(a2_answers_11_set2, 2, idList.get(35));
		answerRepo.save(a2_answer_11_set2);

		String[] a2_answers_12_set2 = { "Biển 1.", "Biển 2.", "Không biến nào." };
		Answer a2_answer_12_set2 = new Answer(a2_answers_12_set2, 1, idList.get(36));
		answerRepo.save(a2_answer_12_set2);

		String[] a2_answers_13_set2 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_13_set2 = new Answer(a2_answers_13_set2, 2, idList.get(37));
		answerRepo.save(a2_answer_13_set2);

		String[] a2_answers_14_set2 = { "Cấm các loại xe ở biển phụ đi vào.",
				"Cấm các loại xe cơ giới đi vào trừ loại xe ở biển phụ." };
		Answer a2_answer_14_set2 = new Answer(a2_answers_14_set2, 0, idList.get(38));
		answerRepo.save(a2_answer_14_set2);

		String[] a2_answers_15_set2 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_15_set2 = new Answer(a2_answers_15_set2, 0, idList.get(39));
		answerRepo.save(a2_answer_15_set2);

		String[] a2_answers_16_set2 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_16_set2 = new Answer(a2_answers_16_set2, 0, idList.get(40));
		answerRepo.save(a2_answer_16_set2);

		String[] a2_answers_17_set2 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_17_set2 = new Answer(a2_answers_17_set2, 1, idList.get(41));
		answerRepo.save(a2_answer_17_set2);

		String[] a2_answers_18_set2 = {
				"Đế báo trước gần tới đoạn đường có hiện tượng đất đá từ trên ta luy dương sụt lở bất ngờ gây nguy hiểm cho xe cộ và người đi đường.",
				"Để báo trước nơi có kết cấu mặt đường rời rạc, khi phương tiện đi qua, làm cho các viên đá, sỏi văng lên gây nguy hiểm và mất an toàn cho người và phương tiện tham gia giao thông.",
				"Để cảnh báo những đoạn nền đường yếu, đoạn đường đang theo dõi lún mà việc vận hành xe ở tốc độ cao có thể gây nguy hiểm." };
		Answer a2_answer_18_set2 = new Answer(a2_answers_18_set2, 1, idList.get(42));
		answerRepo.save(a2_answer_18_set2);

		String[] a2_answers_19_set2 = { "Biển 1.", "Biển 2.", "Biển 3.", "Biển 1 và 3." };
		Answer a2_answer_19_set2 = new Answer(a2_answers_19_set2, 0, idList.get(43));
		answerRepo.save(a2_answer_19_set2);

		String[] a2_answers_20_set2 = { "Biến 1 và 2.", "Biển 1 và 3.", "Biển 2 và 3." };
		Answer a2_answer_20_set2 = new Answer(a2_answers_20_set2, 0, idList.get(44));
		answerRepo.save(a2_answer_20_set2);

		String[] a2_answers_21_set2 = { "Phân chia hai chiều xe chạy ngược chiều nhau.",
				"Phân chia các làn xe chạy cùng chiều nhau." };
		Answer a2_answer_21_set2 = new Answer(a2_answers_21_set2, 0, idList.get(45));
		answerRepo.save(a2_answer_21_set2);

		String[] a2_answers_22_set2 = { "Xe khách, xe tải, mô tô.", "Xe tải, xe con, mô tô.",
				"Xe khách, xe con, mô tô." };
		Answer a2_answer_22_set2 = new Answer(a2_answers_22_set2, 0, idList.get(46));
		answerRepo.save(a2_answer_22_set2);

		String[] a2_answers_23_set2 = { "Mô tô, xe con.", "Xe con, xe tải.", "Mô tô, xe tải.", "Cả ba xe." };
		Answer a2_answer_23_set2 = new Answer(a2_answers_23_set2, 2, idList.get(47));
		answerRepo.save(a2_answer_23_set2);

		String[] a2_answers_24_set2 = { "Quay đầu theo hướng A.", "Quay đầu theo hướng B.", "Cấm quay đầu." };
		Answer a2_answer_24_set2 = new Answer(a2_answers_24_set2, 0, idList.get(48));
		answerRepo.save(a2_answer_24_set2);

		String[] a2_answers_25_set2 = { "Tăng tốc độ, rẽ phải trước xe con màu xanh phía trước và người đi bộ.",
				"Giảm tốc độ, để người đi bộ qua đường và rẽ phải trước xe con màu xanh.",
				"Giảm tốc độ, để người đi bộ qua đường và rẽ phải sau xe con màu xanh." };
		Answer a2_answer_25_set2 = new Answer(a2_answers_25_set2, 2, idList.get(49));
		answerRepo.save(a2_answer_25_set2);

		String[] a2_answers_1_set3 = {
				"Là khoảng trống có kích thước giới hạn về chiều cao, chiều rộng của đường, cầu, bến phà, hầm đường bộ để các xe kể cả hàng hóa xếp trên xe đi qua được an toàn.",
				"Là khoảng trống có kích thước giới hạn về chiều rộng của đường, cầu, bến phà, hầm trên đường bộ để các xe kể cả hàng hóa xếp trên xe đi qua được an toàn.",
				"Là khoảng trống có kích thước giới hạn về chiều cao của cầu, bến phà, hầm trên đường bộ để các xe đi qua được an toàn." };
		Answer a2_answer_1_set3 = new Answer(a2_answers_1_set3, 0, idList.get(50));
		answerRepo.save(a2_answer_1_set3);

		String[] a2_answers_2_set3 = { "Không được phép.", "Chỉ được lái ở tốc độ chậm và quãng đường ngắn.",
				"Chỉ được lái nếu trong cơ thể có nồng độ cồn thấp." };
		Answer a2_answer_2_set3 = new Answer(a2_answers_2_set3, 0, idList.get(51));
		answerRepo.save(a2_answer_2_set3);

		String[] a2_answers_3_set3 = { "Không được vượt.", "Được vượt khi đang đi trên cầu.",
				"Được phép vượt khi đi qua nơi giao nhau có ít phương tiện cùng tham gia giao thông.",
				"Được vượt khi đảm bảo an toàn." };
		Answer a2_answer_3_set3 = new Answer(a2_answers_3_set3, 0, idList.get(52));
		answerRepo.save(a2_answer_3_set3);

		String[] a2_answers_4_set3 = { "Xe mô tô ba bánh.",
				"Xe mô tô hai bánh có dung tích xi-lanh từ 175 cm3 trở lên và các loại xe quy định cho giấy phép lái xe hạng A1.",
				"Các loại máy kéo nhỏ có trọng tải đến 1.000 kg." };
		Answer a2_answer_4_set3 = new Answer(a2_answers_4_set3, 1, idList.get(53));
		answerRepo.save(a2_answer_4_set3);

		String[] a2_answers_5_set3 = { "Không được quay đầu xe.",
				"Lợi dụng chỗ rộng và phải có người làm tín hiệu sau xe để bảo đảm an toàn.",
				"Lợi dụng chỗ rộng có thể quay đầu được để quay đầu xe cho an toàn." };
		Answer a2_answer_5_set3 = new Answer(a2_answers_5_set3, 0, idList.get(54));
		answerRepo.save(a2_answer_5_set3);

		String[] a2_answers_6_set3 = { "Từ từ đi cắt qua đoàn người, đoàn xe.",
				"Không được cắt ngang qua đoàn người, đoàn xe.",
				"Báo hiệu từ từ cho xe đi cắt qua để bảo đảm an toàn." };
		Answer a2_answer_6_set3 = new Answer(a2_answers_6_set3, 1, idList.get(55));
		answerRepo.save(a2_answer_6_set3);

		String[] a2_answers_7_set3 = { "Ô tô kéo rơ moóc, ô tô kéo xe khác, xe gắn máy.",
				"Ô tô chở người trên 30 chỗ (trừ ô tô buýt), ô tô tải có trọng | tải trên 3,5 tấn (trừ ô tô xi téc).",
				"Xe ô tô con, xe ô tô chở người đến 30 chỗ (trừ xe buýt), ô tô tải có trọng tải nhỏ hơn hoặc bằng 3,5 tấn." };
		Answer a2_answer_7_set3 = new Answer(a2_answers_7_set3, 1, idList.get(56));
		answerRepo.save(a2_answer_7_set3);

		String[] a2_answers_8_set3 = { "Giảm tốc độ, đi từ từ đế vượt qua trước người đi bộ.",
				"Giảm tốc độ, có thể dừng lại nếu cần thiết trước vạch dừng xe để nhường đường cho người đi bộ qua đường.",
				"Tăng tốc độ để vượt qua trước người đi bộ." };
		Answer a2_answer_8_set3 = new Answer(a2_answers_8_set3, 1, idList.get(57));
		answerRepo.save(a2_answer_8_set3);

		String[] a2_answers_9_set3 = { "Thực hiện cầm máu trực tiếp.",
				"Thực hiện cầm máu không trực tiếp (chặn động mạch)." };
		Answer a2_answer_9_set3 = new Answer(a2_answers_9_set3, 1, idList.get(58));
		answerRepo.save(a2_answer_9_set3);

		String[] a2_answers_10_set3 = {
				"Khi có chuông báo hoặc thanh chắn đã hạ xuống, người | lái xe phải dừng xe tạm thời đúng khoảng cách an toàn,kéo phanh tay nếu đường dốc hoặc phải chờ lâu.",
				"Khi không có chuông báo hoặc thanh chắn không hạ xuống,người lái xe phải quan sát nếu thấy đủ điều kiện an toàn thì về số thấp, tăng ga nhẹ và không thay đổi số trong quá trình vượt qua đường sắt để tránh động cơ chết máy cho xe vượt qua.",
				"Cả ý 1 và ý 2." };
		Answer a2_answer_10_set3 = new Answer(a2_answers_10_set3, 2, idList.get(59));
		answerRepo.save(a2_answer_10_set3);

		String[] a2_answers_11_set3 = { "Dùng để kích (hay nâng) xe ô tô.", "Vặn ốc lắp bánh xe.",
				"Ổn định chuyển động của xe ô tô khi đi vào đường vòng.",
				"Giữ chặt người lái và hành khách trên ghế ngồi khi xe ô tô đột ngột dừng lại." };
		Answer a2_answer_11_set3 = new Answer(a2_answers_11_set3, 0, idList.get(60));
		answerRepo.save(a2_answer_11_set3);

		String[] a2_answers_12_set3 = { "Biển 1.", "Biển 2.", "Cả hai biến." };
		Answer a2_answer_12_set3 = new Answer(a2_answers_12_set3, 2, idList.get(61));
		answerRepo.save(a2_answer_12_set3);

		String[] a2_answers_13_set3 = { "Đường cấm súc vật vận tải hàng hóa.", "Đường cấm súc vật vận tải hành khách.",
				"Đường cấm súc vật vận tải hàng hóa hoặc hành khách dù kéo xe hay chở trên lưng đi qua." };
		Answer a2_answer_13_set3 = new Answer(a2_answers_13_set3, 2, idList.get(62));
		answerRepo.save(a2_answer_13_set3);

		String[] a2_answers_14_set3 = { "Biển 1.", "Biển 2.", "Cả hai biển." };
		Answer a2_answer_14_set3 = new Answer(a2_answers_14_set3, 0, idList.get(63));
		answerRepo.save(a2_answer_14_set3);

		String[] a2_answers_15_set3 = { "Biển 1.", "Biển 1 và 2.", "Biển 2 và 3.", "Cả ba biến." };
		Answer a2_answer_15_set3 = new Answer(a2_answers_15_set3, 3, idList.get(64));
		answerRepo.save(a2_answer_15_set3);

		String[] a2_answers_16_set3 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_16_set3 = new Answer(a2_answers_16_set3, 1, idList.get(65));
		answerRepo.save(a2_answer_16_set3);

		String[] a2_answers_17_set3 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_17_set3 = new Answer(a2_answers_17_set3, 1, idList.get(66));
		answerRepo.save(a2_answer_17_set3);

		String[] a2_answers_18_set3 = { "Đi chậm, quan sát và dừng lại nếu gặp gia súc trên đường.",
				"Bấm còi to để gia súc tránh đường và nhanh chóng di chuyển qua đoạn đường có gia súc." };
		Answer a2_answer_18_set3 = new Answer(a2_answers_18_set3, 0, idList.get(67));
		answerRepo.save(a2_answer_18_set3);

		String[] a2_answers_19_set3 = { "Biển 1 và 2.", "Biển 1 và 3.", "Biển 2 và 3.", "Cả ba biển." };
		Answer a2_answer_19_set3 = new Answer(a2_answers_19_set3, 0, idList.get(68));
		answerRepo.save(a2_answer_19_set3);

		String[] a2_answers_20_set3 = { "Chỉ dẫn khoảng cách đến làn đường cứu nạn (làn thoát xe khẩn cấp).",
				"Báo hiệu đường cụt phía trước.", "Báo hiệu nút giao gần nhất phía trước.",
				"Báo hiệu trạm dừng nghỉ phía trước." };
		Answer a2_answer_20_set3 = new Answer(a2_answers_20_set3, 0, idList.get(69));
		answerRepo.save(a2_answer_20_set3);

		String[] a2_answers_21_set3 = { "Phân chia hai chiều xe chạy ngược chiều nhau.",
				"Phân chia các làn xe chạy cùng chiều nhau." };
		Answer a2_answer_21_set3 = new Answer(a2_answers_21_set3, 1, idList.get(70));
		answerRepo.save(a2_answer_21_set3);

		String[] a2_answers_22_set3 = { "Xe khách, xe tải, mô tô, xe con.", "Xe con, xe khách, xe tải, mô tô.",
				"Mô tô, xe tải, xe khách, xe con.", "Mô tô, xe tải, xe con, xe khách." };
		Answer a2_answer_22_set3 = new Answer(a2_answers_22_set3, 2, idList.get(71));
		answerRepo.save(a2_answer_22_set3);

		String[] a2_answers_23_set3 = { "Đúng.", "Không đúng." };
		Answer a2_answer_23_set3 = new Answer(a2_answers_23_set3, 0, idList.get(72));
		answerRepo.save(a2_answer_23_set3);

		String[] a2_answers_24_set3 = { "Xe con và xe tải, xe của bạn.", "Xe của bạn, xe tải, xe con.",
				"Xe của bạn và xe con, xe tải.", "Xe của bạn, xe tải + xe con." };
		Answer a2_answer_24_set3 = new Answer(a2_answers_24_set3, 2, idList.get(73));
		answerRepo.save(a2_answer_24_set3);

		String[] a2_answers_25_set3 = { "Nhường đường cho xe khách và đi trước xe đạp.",
				"Nhường đường cho xe đạp và đi trước xe khách.", "Nhường đường cho xe đạp và xe khách." };
		Answer a2_answer_25_set3 = new Answer(a2_answers_25_set3, 2, idList.get(74));
		answerRepo.save(a2_answer_25_set3);

		String[] a2_answers_1_set4 = {
				"Là bộ phận của đường để ngăn cách không cho các loại xe vào những nơi không được phép.",
				"Là bộ phận của đường để phân tách phần đường xe chạy và hành lang an toàn giao thông.",
				"Là bộ phận của đường để phân chia mặt đường thành hai chiều xe chạy riêng biệt hoặc để phân chia phần đường của xe cơ giới và xe thô sơ." };
		Answer a2_answer_1_set4 = new Answer(a2_answers_1_set4, 2, idList.get(75));
		answerRepo.save(a2_answer_1_set4);

		String[] a2_answers_2_set4 = { "Bị nghiêm cấm.", "Không bị nghiêm cấm.",
				"Không bị nghiêm cấm, nếu nồng độ cồn trong máu ở mức nhẹ, có thể điều khiển phương tiện tham gia giao thông." };
		Answer a2_answer_2_set4 = new Answer(a2_answers_2_set4, 0, idList.get(76));
		answerRepo.save(a2_answer_2_set4);

		String[] a2_answers_3_set4 = {
				"Ở phần đường dành cho người đi bộ qua đường, trên cầu, đầu cầu, đường cao tốc, đường hẹp, đường dốc, tại nơi đường bộ giao nhau cùng mức với đường sắt.",
				"Ở phía trước hoặc phía sau của phần đường dành cho người đi bộ qua đường, trên đường quốc lộ, tại nơi đường bộ giao nhau không cùng mức với đường sắt.",
				"Cả ý 1 và ý 2." };
		Answer a2_answer_3_set4 = new Answer(a2_answers_3_set4, 0, idList.get(77));
		answerRepo.save(a2_answer_3_set4);

		String[] a2_answers_4_set4 = { "Xe mô tô ba bánh.",
				"Xe mô tô hai bánh có dung tích xi-lanh từ 175 cm3 trở lên.",
				"Các loại máy kéo nhỏ có trọng tải đến 1.000 kg." };
		Answer a2_answer_4_set4 = new Answer(a2_answers_4_set4, 0, idList.get(78));
		answerRepo.save(a2_answer_4_set4);

		String[] a2_answers_5_set4 = { "Quan sát gương, ra tín hiệu, quan sát an toàn và chuyển hướng.",
				"Quan sát gương, giảm tốc độ, ra tín hiệu chuyển hướng, quan sát an toàn và chuyển hướng.",
				"Quan sát gương, tăng tốc độ, ra tín hiệu và chuyển hướng." };
		Answer a2_answer_5_set4 = new Answer(a2_answers_5_set4, 1, idList.get(79));
		answerRepo.save(a2_answer_5_set4);

		String[] a2_answers_6_set4 = {
				"Nhường đường cho người đi bộ đang đi trên phần đường dành cho người đi bộ sang đường, nhường đường cho xe đi trên đường ưu tiên, đường chính từ bất kỳ hướng nào tới; nhường đường cho xe ưu tiên, xe đi từ bên phải đến.",
				"Nhường đường cho người đi bộ đang đứng chờ đi qua phần đường dành cho người đi bộ sang đường, nhường đường cho xe đi trên đường ngược chiều, đường nhánh từ bất kỳ hướng nào tới; nhường đường cho xe đi từ bên trái đến.",
				"Không phải nhường đường." };
		Answer a2_answer_6_set4 = new Answer(a2_answers_6_set4, 0, idList.get(80));
		answerRepo.save(a2_answer_6_set4);

		String[] a2_answers_7_set4 = { "Ô tô kéo rơ moóc, ô tô kéo xe khác, xe gắn máy.",
				"Ô tô chở người trên 30 chỗ (trừ ô tô buýt), ô tô tải có trọng tải trên 3,5 tấn.",
				"Ô tô buýt, ô tô đầu kéo kéo sơ mi rơ moóc, xe mô tô, ô tô chuyên dùng (trừ ô tô trộn vữa, ô tô trộn bê tông)." };
		Answer a2_answer_7_set4 = new Answer(a2_answers_7_set4, 2, idList.get(81));
		answerRepo.save(a2_answer_7_set4);

		String[] a2_answers_8_set4 = {
				"Khi có người đi bộ, xe lăn của người khuyết tật qua đường; đến gần bến xe buýt, điểm dừng đỗ xe có khách đang lên, xuống xe.",
				"Khi điều khiển phương tiện đi qua khu vực trạm kiểm soát tải trọng xe, trạm cảnh sát giao thông, trạm giao dịch thanh toán đối với các phương tiện sử dụng đường bộ.",
				"Cả ý 1 và ý 2." };
		Answer a2_answer_8_set4 = new Answer(a2_answers_8_set4, 2, idList.get(82));
		answerRepo.save(a2_answer_8_set4);

		String[] a2_answers_9_set4 = {
				"Có trách nhiệm với bản thân và với cộng đồng; tôn trọng, nhường nhịn người khác.",
				"Tận tình giúp đỡ người tham gia giao thông gặp hoạn nạn; giúp đỡ người khuyết tật, trẻ em và người cao tuổi.",
				"Cả ý 1 và ý 2." };
		Answer a2_answer_9_set4 = new Answer(a2_answers_9_set4, 2, idList.get(83));
		answerRepo.save(a2_answer_9_set4);

		String[] a2_answers_10_set4 = { "Bảo dưỡng xe theo định kỳ và có kế hoạch lộ trình trước khi chạy xe.",
				"Kiểm tra áp suất lốp theo quy định và chạy xe với tốc độ phù hợp với tình trạng mặt đường và mật độ giao thông trên đường.",
				"Cả ý 1 và ý 2." };
		Answer a2_answer_10_set4 = new Answer(a2_answers_10_set4, 2, idList.get(84));
		answerRepo.save(a2_answer_10_set4);

		String[] a2_answers_11_set4 = { "Thay lốp xe.", "Chữa cháy.",
				"Phá cửa kính xe ô tô trong các trường hợp khẩn cấp.", "Vặn ốc để tháo lắp bánh xe." };
		Answer a2_answer_11_set4 = new Answer(a2_answers_11_set4, 2, idList.get(85));
		answerRepo.save(a2_answer_11_set4);

		String[] a2_answers_12_set4 = { "Biển 1 và 2.", "Biển 1 và 3.", "Biển 2 và 3.", "Cả ba biển." };
		Answer a2_answer_12_set4 = new Answer(a2_answers_12_set4, 0, idList.get(86));
		answerRepo.save(a2_answer_12_set4);

		String[] a2_answers_13_set4 = { "Tốc độ tối đa cho phép về ban đêm cho các phương tiện là 70 km/h.",
				"Tốc độ tối thiểu cho phép về ban đêm cho các phương tiện là 70 km/h." };
		Answer a2_answer_13_set4 = new Answer(a2_answers_13_set4, 0, idList.get(87));
		answerRepo.save(a2_answer_13_set4);

		String[] a2_answers_14_set4 = { "Biển 1.", "Biển 2.", "Cả hai biển." };
		Answer a2_answer_14_set4 = new Answer(a2_answers_14_set4, 1, idList.get(88));
		answerRepo.save(a2_answer_14_set4);

		String[] a2_answers_15_set4 = { "Biến 1.", "Biển 2 và 3.", "Biến 3." };
		Answer a2_answer_15_set4 = new Answer(a2_answers_15_set4, 0, idList.get(89));
		answerRepo.save(a2_answer_15_set4);

		String[] a2_answers_16_set4 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_16_set4 = new Answer(a2_answers_16_set4, 0, idList.get(90));
		answerRepo.save(a2_answer_16_set4);

		String[] a2_answers_17_set4 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_17_set4 = new Answer(a2_answers_17_set4, 1, idList.get(91));
		answerRepo.save(a2_answer_17_set4);

		String[] a2_answers_18_set4 = { "Báo hiệu khu vực nguy hiểm thường xuyên có sét đánh.",
				"Báo hiệu khu vực có đường dây điện cắt ngang phía trên tuyến đường." };
		Answer a2_answer_18_set4 = new Answer(a2_answers_18_set4, 1, idList.get(92));
		answerRepo.save(a2_answer_18_set4);

		String[] a2_answers_19_set4 = { "Biến 1.", "Biển 2.", "Cả hai biển." };
		Answer a2_answer_19_set4 = new Answer(a2_answers_19_set4, 2, idList.get(93));
		answerRepo.save(a2_answer_19_set4);

		String[] a2_answers_20_set4 = { "Được phép chuyển sang làn khác.",
				"Không được phép chuyển sang làn khác, chỉ được đi trong làn quy định theo biên." };
		Answer a2_answer_20_set4 = new Answer(a2_answers_20_set4, 0, idList.get(94));
		answerRepo.save(a2_answer_20_set4);

		String[] a2_answers_21_set4 = { "Vạch 1.", "Vạch 2.", "Vạch 3.", "Vạch 1 và vạch 3." };
		Answer a2_answer_21_set4 = new Answer(a2_answers_21_set4, 3, idList.get(95));
		answerRepo.save(a2_answer_21_set4);

		String[] a2_answers_22_set4 = { "Xe tải.", "Xe con và mô tô,", "Cả ba xe.", "Xe con và xe tải." };
		Answer a2_answer_22_set4 = new Answer(a2_answers_22_set4, 0, idList.get(96));
		answerRepo.save(a2_answer_22_set4);

		String[] a2_answers_23_set4 = { "Xe tải.", "Cả hai xe.", "Xe con." };
		Answer a2_answer_23_set4 = new Answer(a2_answers_23_set4, 2, idList.get(97));
		answerRepo.save(a2_answer_23_set4);

		String[] a2_answers_24_set4 = { "Xe khách, mô tô.", "Xe tải, mô tô.", "Xe con, xe tải." };
		Answer a2_answer_24_set4 = new Answer(a2_answers_24_set4, 2, idList.get(98));
		answerRepo.save(a2_answer_24_set4);

		String[] a2_answers_25_set4 = { "Xe con.", "Xe tải.", "Xe của bạn." };
		Answer a2_answer_25_set4 = new Answer(a2_answers_25_set4, 1, idList.get(99));
		answerRepo.save(a2_answer_25_set4);

		String[] a2_answers_1_set5 = { "Dải phân cách gồm loại cố định và loại di động.",
				"Dải phân cách gồm tường chống ồn, hộ lan cứng và hộ lan mềm.",
				"Dải phân cách gồm giá long môn và biển báo hiệu đường bộ." };
		Answer a2_answer_1_set5 = new Answer(a2_answers_1_set5, 0, idList.get(100));
		answerRepo.save(a2_answer_1_set5);

		String[] a2_answers_2_set5 = { "Chỉ bị nhắc nhở.",
				"Bị xử phạt hành chính hoặc có thể bị xử lý hình sự tùy theo mức độ vi phạm.",
				"Không bị xử lý hình sự." };
		Answer a2_answer_2_set5 = new Answer(a2_answers_2_set5, 1, idList.get(101));
		answerRepo.save(a2_answer_2_set5);

		String[] a2_answers_3_set5 = { "Đi tiếp đến điểm giao cắt gần nhất hoặc nơi có biển báo cho phép quay đầu xe.",
				"Bấm đèn khẩn cấp và quay đầu xe từ từ bảo đảm an toàn.",
				"Bấm còi liên tục khi quay đầu để cảnh báo các xe khác.",
				"Nhờ một người ra hiệu giao thông trên đường chậm lại trước khi quay đâu." };
		Answer a2_answer_3_set5 = new Answer(a2_answers_3_set5, 0, idList.get(102));
		answerRepo.save(a2_answer_3_set5);

		String[] a2_answers_4_set5 = { "Biển báo nguy hiểm.", "Biển báo cấm.", "Biển báo hiệu lệnh.",
				"Biển báo chỉ dẫn." };
		Answer a2_answer_4_set5 = new Answer(a2_answers_4_set5, 1, idList.get(103));
		answerRepo.save(a2_answer_4_set5);

		String[] a2_answers_5_set5 = { "Quan sát phía trước và cho lùi xe ở tốc độ chậm.",
				"Lợi dụng nơi đường giao nhau đủ chiều rộng để lùi.",
				"Phải quan sát phía sau, có tín hiệu cần thiết và chỉ khi nào thấy không nguy hiểm mới được lùi." };
		Answer a2_answer_5_set5 = new Answer(a2_answers_5_set5, 2, idList.get(104));
		answerRepo.save(a2_answer_5_set5);

		String[] a2_answers_6_set5 = { "Khi cho xe chạy thẳng.", "Trước khi thay đổi làn đường.",
				"Sau khi thay đổi làn đường." };
		Answer a2_answer_6_set5 = new Answer(a2_answers_6_set5, 1, idList.get(105));
		answerRepo.save(a2_answer_6_set5);

		String[] a2_answers_7_set5 = { "Ô tô kéo rơ moóc, ô tô kéo xe khác, xe gắn máy.",
				"Ô tô chở người trên 30 chỗ (trừ ô tô buýt), ô tô tải có trọng tải trên 3,5 tấn.",
				"Ô tô kéo rơ moóc, ô tô kéo xe khác, ô tô trộn vữa, ô tô trộn bê tông, ô tô xi téc." };
		Answer a2_answer_7_set5 = new Answer(a2_answers_7_set5, 2, idList.get(106));
		answerRepo.save(a2_answer_7_set5);

		String[] a2_answers_8_set5 = {
				"Người điều khiển phương tiện tham gia giao thông không hạn chế tốc độ và khoảng cách an toàn tối thiểu giữa hai",
				"Người điều khiển phương tiện tham gia giao thông không hạn chế tốc độ và khoảng cách an toàn tối thiểu giữa hai xe vào ban đêm.",
				"Người điều khiển phương tiện tham gia giao thông phải nghiêm chỉnh chấp hành quy định về tốc độ, khoảng cách an toàn tối thiếu giữa hai xe." };
		Answer a2_answer_8_set5 = new Answer(a2_answers_8_set5, 2, idList.get(107));
		answerRepo.save(a2_answer_8_set5);

		String[] a2_answers_9_set5 = {
				"Điều khiển xe đi trên phần đường, làn đường có ít phương tiện tham gia giao thông, chỉ đội mũ bảo hiểm ở nơi có biển báo bắt buộc đội mũ bảo hiểm.",
				"Chấp hành quy định về tốc độ, đèn tín hiệu, biển báo hiệu, vạch kẻ đường khi lái xe; chấp hành hiệu lệnh, chỉ dẫn của người điều khiển giao thông; nhường đường cho người đi bộ, người già, trẻ em, người khuyết tật.",
				"Cả ý 1 và ý 2." };
		Answer a2_answer_9_set5 = new Answer(a2_answers_9_set5, 1, idList.get(108));
		answerRepo.save(a2_answer_9_set5);

		String[] a2_answers_10_set5 = { "Tăng tốc độ, chạy gần xe trước, nhìn đèn hậu để định hướng.",
				"Giảm tốc độ, chạy cách xa xe trước với khoảng cách an toàn, bật đèn sương mù và đèn chiếu gần.",
				"Tăng tốc độ, bật đèn pha vượt qua xe chạy trước." };
		Answer a2_answer_10_set5 = new Answer(a2_answers_10_set5, 1, idList.get(109));
		answerRepo.save(a2_answer_10_set5);

		String[] a2_answers_11_set5 = { "Thay lốp xe.", "Chữa cháy trong các trường hợp hỏa hoạn.",
				"Phá cửa kính xe ô tô trong các trường hợp khẩn cấp.", "Cầm máu cho người bị nạn." };
		Answer a2_answer_11_set5 = new Answer(a2_answers_11_set5, 1, idList.get(110));
		answerRepo.save(a2_answer_11_set5);

		String[] a2_answers_12_set5 = { "Biển 1 và 2.", "Biển 1 và 3.", "Biển 2 và 3.", "Cả ba biển." };
		Answer a2_answer_12_set5 = new Answer(a2_answers_12_set5, 0, idList.get(111));
		answerRepo.save(a2_answer_12_set5);

		String[] a2_answers_13_set5 = { "Được phép.", "Không được phép." };
		Answer a2_answer_13_set5 = new Answer(a2_answers_13_set5, 1, idList.get(112));
		answerRepo.save(a2_answer_13_set5);

		String[] a2_answers_14_set5 = { "Cấm dừng xe về hướng bên trái.",
				"Cấm đỗ xe và cấm dừng xe theo hướng bên phải.", "Được phép đỗ xe và dừng xe theo hướng bên phải." };
		Answer a2_answer_14_set5 = new Answer(a2_answers_14_set5, 1, idList.get(113));
		answerRepo.save(a2_answer_14_set5);

		String[] a2_answers_15_set5 = { "Biển 1.", "Biển 2.", "Biển 3.", "Cả ba biển." };
		Answer a2_answer_15_set5 = new Answer(a2_answers_15_set5, 2, idList.get(114));
		answerRepo.save(a2_answer_15_set5);

		String[] a2_answers_16_set5 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_16_set5 = new Answer(a2_answers_16_set5, 1, idList.get(115));
		answerRepo.save(a2_answer_16_set5);

		String[] a2_answers_17_set5 = { "Biển 1.", "Biển 2.", "Biển 3.", "Không biển nào." };
		Answer a2_answer_17_set5 = new Answer(a2_answers_17_set5, 0, idList.get(116));
		answerRepo.save(a2_answer_17_set5);

		String[] a2_answers_18_set5 = { "Biển 1.", "Biển 2.", "Cả hai biển." };
		Answer a2_answer_18_set5 = new Answer(a2_answers_18_set5, 1, idList.get(117));
		answerRepo.save(a2_answer_18_set5);

		String[] a2_answers_19_set5 = { "Biển 1.", "Biển 2.", "Biển 3.", "Biển 1 và 2." };
		Answer a2_answer_19_set5 = new Answer(a2_answers_19_set5, 2, idList.get(118));
		answerRepo.save(a2_answer_19_set5);

		String[] a2_answers_20_set5 = { "Có.", "Không." };
		Answer a2_answer_20_set5 = new Answer(a2_answers_20_set5, 0, idList.get(119));
		answerRepo.save(a2_answer_20_set5);

		String[] a2_answers_21_set5 = { "Để xác định làn đường.", "Báo hiệu người lái xe chỉ được phép đi thẳng.",
				"Dùng để xác định khoảng cách giữa các phương tiện trên đường.", "Để trang trí" };
		Answer a2_answer_21_set5 = new Answer(a2_answers_21_set5, 2, idList.get(120));
		answerRepo.save(a2_answer_21_set5);

		String[] a2_answers_22_set5 = { "Xe tải.", "Xe con (B).", "Xe con (A)." };
		Answer a2_answer_22_set5 = new Answer(a2_answers_22_set5, 1, idList.get(121));
		answerRepo.save(a2_answer_22_set5);

		String[] a2_answers_23_set5 = { "Xe chữa cháy.", "Xe tải.", "Cả hai xe." };
		Answer a2_answer_23_set5 = new Answer(a2_answers_23_set5, 1, idList.get(122));
		answerRepo.save(a2_answer_23_set5);

		String[] a2_answers_24_set5 = { "Xe tải.", "Xe khách.", "Xe con." };
		Answer a2_answer_24_set5 = new Answer(a2_answers_24_set5, 1, idList.get(123));
		answerRepo.save(a2_answer_24_set5);

		String[] a2_answers_25_set5 = { "Tăng tốc độ, chuyển sang làn đường bên trái để vượt.",
				"Không được vượt những người đi xe đạp." };
		Answer a2_answer_25_set5 = new Answer(a2_answers_25_set5, 1, idList.get(124));
		answerRepo.save(a2_answer_25_set5);

		String[] a2_answers_1_set6 = { "Là người điều khiển xe cơ giới.", "Là người điều khiển xe thô sơ.",
				"Là người điều khiển xe có súc vật kéo." };
		Answer a2_answer_1_set6 = new Answer(a2_answers_1_set6, 0, idList.get(125));
		answerRepo.save(a2_answer_1_set6);

		String[] a2_answers_2_set6 = { "Người điều khiển: Xe ô tô, xe mô tô, xe đạp, xe gắn máy.",
				"Người ngồi phía sau người điều khiển xe cơ giới.", "Người đi bộ.", "Cả ý 1 và ý 2." };
		Answer a2_answer_2_set6 = new Answer(a2_answers_2_set6, 0, idList.get(126));
		answerRepo.save(a2_answer_2_set6);

		String[] a2_answers_3_set6 = { "Ở khu vực cho phép đỗ xe.",
				"Ở khu vực cấm dừng và trên phần đường dành cho người đi bộ qua đường.",
				"Nơi đường bộ giao nhau, đường bộ giao nhau cùng mức với đường sắt, nơi tầm nhìn bị che khuất, trong hầm đường bộ, đường cao tốc.",
				"Cả ý 2 và ý 3." };
		Answer a2_answer_3_set6 = new Answer(a2_answers_3_set6, 3, idList.get(127));
		answerRepo.save(a2_answer_3_set6);

		String[] a2_answers_4_set6 = { "Biển báo nguy hiểm.", "Biển báo cấm.", "Biển báo hiệu lệnh.",
				"Biển báo chỉ dẫn." };
		Answer a2_answer_4_set6 = new Answer(a2_answers_4_set6, 0, idList.get(128));
		answerRepo.save(a2_answer_4_set6);

		String[] a2_answers_5_set6 = {
				"Nơi đường hẹp chỉ đủ cho một xe chạy và có chỗ tránh | xe thì xe nào ở gần chỗ tránh hơn phải vào vị trí tránh, nhường đường cho xe kia đi.",
				"Xe xuống dốc phải nhường đường cho xe đang lên dốc; xe nào có chướng ngại vật phía trước phải nhường đường cho xe không có chướng ngại vật đi trước.",
				"Xe lên dốc phải nhường đường cho xe xuống dốc; xe nào không có chướng ngại vật phía trước phải nhường đường cho xe có chướng ngại vật đi trước.",
				"Cả ý 1 và ý 2." };
		Answer a2_answer_5_set6 = new Answer(a2_answers_5_set6, 3, idList.get(129));
		answerRepo.save(a2_answer_5_set6);

		String[] a2_answers_6_set6 = { "Xe bị vượt bất ngờ tăng tốc độ và cố tình không nhường đường.",
				"Xe bị vượt giảm tốc độ và nhường đường.", "Phát hiện có xe đi ngược chiều.", "Cả ý 1 và ý 3." };
		Answer a2_answer_6_set6 = new Answer(a2_answers_6_set6, 3, idList.get(130));
		answerRepo.save(a2_answer_6_set6);

		String[] a2_answers_7_set6 = { "35 m.", "55 m.", "70 m." };
		Answer a2_answer_7_set6 = new Answer(a2_answers_7_set6, 1, idList.get(131));
		answerRepo.save(a2_answer_7_set6);

		String[] a2_answers_8_set6 = { "Đỏ – Vàng – Xanh.", "Cam – Vàng – Xanh.", "Vàng – Xanh dương – Xanh lá.",
				"Đỏ – Cam – Xanh." };
		Answer a2_answer_8_set6 = new Answer(a2_answers_8_set6, 0, idList.get(132));
		answerRepo.save(a2_answer_8_set6);

		String[] a2_answers_9_set6 = {
				"Điều khiển xe đi bên phải theo chiều đi của mình; đi đúng phần đường, làn đường quy định; đội mũ bảo hiểm đạt chuẩn, cài quai đúng quy cách.",
				"Điều khiển xe đi trên phần đường, làn đường có ít phương tiện tham gia giao thông.",
				"Điều khiển xe và đội mũ bảo hiểm ở nơi có biển báo bắt buộc đội mũ bảo hiểm." };
		Answer a2_answer_9_set6 = new Answer(a2_answers_9_set6, 0, idList.get(133));
		answerRepo.save(a2_answer_9_set6);

		String[] a2_answers_10_set6 = { "Giảm tốc độ, nếu cần thiết có thể dừng xe lại.",
				"Bật đèn pha chiếu xa và giữ nguyên tốc độ.", "Tăng tốc độ, bật đèn pha đối diện xe phía trước." };
		Answer a2_answer_10_set6 = new Answer(a2_answers_10_set6, 0, idList.get(134));
		answerRepo.save(a2_answer_10_set6);

		String[] a2_answers_11_set6 = { "Biển 1.", "Biển 2.", "Biển 1 và 3.", "Cả ba biển." };
		Answer a2_answer_11_set6 = new Answer(a2_answers_11_set6, 0, idList.get(135));
		answerRepo.save(a2_answer_11_set6);

		String[] a2_answers_12_set6 = { "Biển 1 và 2.", "Biển 1 và 3.", "Biển 2 và 3.", "Không biến nào." };
		Answer a2_answer_12_set6 = new Answer(a2_answers_12_set6, 0, idList.get(136));
		answerRepo.save(a2_answer_12_set6);

		String[] a2_answers_13_set6 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_13_set6 = new Answer(a2_answers_13_set6, 0, idList.get(137));
		answerRepo.save(a2_answer_13_set6);

		String[] a2_answers_14_set6 = { "Không được phép.", "Được phép." };
		Answer a2_answer_14_set6 = new Answer(a2_answers_14_set6, 1, idList.get(138));
		answerRepo.save(a2_answer_14_set6);

		String[] a2_answers_15_set6 = { "Biển 1 và 2.", "Biển 1 và 3.", "Biển 2 và 3.", "Cả ba biến." };
		Answer a2_answer_15_set6 = new Answer(a2_answers_15_set6, 1, idList.get(139));
		answerRepo.save(a2_answer_15_set6);

		String[] a2_answers_16_set6 = { "Biển 1.", "Biển 2.", "Biển 3.", "Biển 2 và 3." };
		Answer a2_answer_16_set6 = new Answer(a2_answers_16_set6, 2, idList.get(140));
		answerRepo.save(a2_answer_16_set6);

		String[] a2_answers_17_set6 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_17_set6 = new Answer(a2_answers_17_set6, 1, idList.get(141));
		answerRepo.save(a2_answer_17_set6);

		String[] a2_answers_18_set6 = { "Biển 1.", "Biển 2.", "Cả hai biển." };
		Answer a2_answer_18_set6 = new Answer(a2_answers_18_set6, 2, idList.get(142));
		answerRepo.save(a2_answer_18_set6);

		String[] a2_answers_19_set6 = { "Biển chỉ dẫn hết cấm đỗ xe theo giờ trong khu vực.",
				"Biển chỉ dẫn hết hiệu lực khu vực đỗ xe trên các tuyến đường đối ngoại.",
				"Biển chỉ dẫn khu vực đỗ xe trên các tuyến đường đối ngoại." };
		Answer a2_answer_19_set6 = new Answer(a2_answers_19_set6, 0, idList.get(143));
		answerRepo.save(a2_answer_19_set6);

		String[] a2_answers_20_set6 = { "Có.", "Không." };
		Answer a2_answer_20_set6 = new Answer(a2_answers_20_set6, 1, idList.get(144));
		answerRepo.save(a2_answer_20_set6);

		String[] a2_answers_21_set6 = {
				"Báo cho người điều khiển không được dùng phương tiện trong phạm vi phần mặt đường có bố trí vạch để tránh ùn tắc giao thông.",
				"Báo hiệu sắp đến chỗ có bố trí vạch đi bộ qua đường.",
				"Dùng để xác định khoảng cách giữa các phương tiện trên đường." };
		Answer a2_answer_21_set6 = new Answer(a2_answers_21_set6, 1, idList.get(145));
		answerRepo.save(a2_answer_21_set6);

		String[] a2_answers_22_set6 = { "Cả ba hướng.", "Chỉ hướng 1 và 3.", "Chỉ hướng 1." };
		Answer a2_answer_22_set6 = new Answer(a2_answers_22_set6, 0, idList.get(146));
		answerRepo.save(a2_answer_22_set6);

		String[] a2_answers_23_set6 = { "Xe khách, xe tải, xe con.", "Xe con và xe tải, xe khách.",
				"Xe tải, xe khách, xe con." };
		Answer a2_answer_23_set6 = new Answer(a2_answers_23_set6, 2, idList.get(147));
		answerRepo.save(a2_answer_23_set6);

		String[] a2_answers_24_set6 = { "Cho phép.", "Không được vượt." };
		Answer a2_answer_24_set6 = new Answer(a2_answers_24_set6, 1, idList.get(148));
		answerRepo.save(a2_answer_24_set6);

		String[] a2_answers_25_set6 = { "Tăng tốc độ và đi thẳng qua ngã tư.", "Dừng xe trước vạch dừng.",
				"Giảm tốc độ và đi thẳng qua ngã tư." };
		Answer a2_answer_25_set6 = new Answer(a2_answers_25_set6, 2, idList.get(149));
		answerRepo.save(a2_answer_25_set6);

		String[] a2_answers_1_set7 = { "Đường không ưu tiên.", "Đường tỉnh lộ.", "Đường quốc lộ.", "Đường ưu tiên." };
		Answer a2_answer_1_set7 = new Answer(a2_answers_1_set7, 3, idList.get(150));
		answerRepo.save(a2_answer_1_set7);

		String[] a2_answers_2_set7 = { "Chỉ được thực hiện nếu đã hướng dẫn đầy đủ.", "Không được phép.",
				"Được phép tuỳ từng trường hợp.", "Chỉ được phép thực hiện với thành viên trong gia đình." };
		Answer a2_answer_2_set7 = new Answer(a2_answers_2_set7, 1, idList.get(151));
		answerRepo.save(a2_answer_2_set7);

		String[] a2_answers_3_set7 = { "Được dừng xe, đỗ xe trong trường hợp cần thiết.", "Không được dừng xe, đỗ xe.",
				"Được dừng xe, không được đỗ xe." };
		Answer a2_answer_3_set7 = new Answer(a2_answers_3_set7, 1, idList.get(152));
		answerRepo.save(a2_answer_3_set7);

		String[] a2_answers_4_set7 = { "Biển báo nguy hiểm.", "Biển báo cấm.", "Biển báo hiệu lệnh phải thi hành.",
				"Biển báo chỉ dẫn." };
		Answer a2_answer_4_set7 = new Answer(a2_answers_4_set7, 2, idList.get(153));
		answerRepo.save(a2_answer_4_set7);

		String[] a2_answers_5_set7 = { "Tiếp tục đi vì xe lên dốc phải nhường đường cho mình.",
				"Nhường đường cho xe lên dốc.", "Chỉ nhường đường khi xe lên dốc nháy đèn." };
		Answer a2_answer_5_set7 = new Answer(a2_answers_5_set7, 1, idList.get(154));
		answerRepo.save(a2_answer_5_set7);

		String[] a2_answers_6_set7 = { "Tăng tốc độ kết hợp với nghe nhạc và đi tiếp.",
				"Quan sát, dừng xe tại nơi quy định; nghỉ cho đến khi hết buồn ngủ và đi tiếp.",
				"Sử dụng một ít rượu và bia để hết buồn ngủ và đi tiếp." };
		Answer a2_answer_6_set7 = new Answer(a2_answers_6_set7, 1, idList.get(155));
		answerRepo.save(a2_answer_6_set7);

		String[] a2_answers_7_set7 = { "35 m.", "55 m.", "70 m." };
		Answer a2_answer_7_set7 = new Answer(a2_answers_7_set7, 2, idList.get(156));
		answerRepo.save(a2_answer_7_set7);

		String[] a2_answers_8_set7 = {
				"Phải cho xe dừng lại trước vạch dừng, trường hợp đã đi quá vạch dừng hoặc đã quá gần vạch dừng nếu dừng lại thấy nguy hiểm thì được đi tiếp.",
				"Trong trường hợp tín hiệu vàng nhấp nháy là được đi nhưng phải giảm tốc độ, chú ý quan sát nhường đường | cho người đi bộ qua đường.",
				"Nhanh chóng tăng tốc độ, vượt qua nút giao và chú ý đảm bảo an toàn.", "Cả ý 1 và ý 2." };
		Answer a2_answer_8_set7 = new Answer(a2_answers_8_set7, 3, idList.get(157));
		answerRepo.save(a2_answer_8_set7);

		String[] a2_answers_9_set7 = {
				"Điều khiển xe đi bên phải theo chiều đi của mình; đi đúng phần đường, làn đường quy định; dừng, đỗ xe đúng nơi quy định; đã uống rượu, bia thì không lái xe.",
				"Điều khiển xe đi trên phần đường, làn đường có ít phương tiện giao thông; dừng xe, đỗ xe ở nơi thuận tiện hoặc theo yêu cầu của hành khách, của người thân.",
				"Dừng và đỗ xe ở nơi thuận tiện cho việc chuyên chở hành khách và giao nhận hàng hóa; sử dụng ít rượu, bia thì có thể lái xe." };
		Answer a2_answer_9_set7 = new Answer(a2_answers_9_set7, 0, idList.get(158));
		answerRepo.save(a2_answer_9_set7);

		String[] a2_answers_10_set7 = { "Sử dụng phanh trước.", "Sử dụng phanh sau.",
				"Giảm hết gay sử dụng đồng thời cả phanh sau và phanh trước." };
		Answer a2_answer_10_set7 = new Answer(a2_answers_10_set7, 2, idList.get(159));
		answerRepo.save(a2_answer_10_set7);

		String[] a2_answers_11_set7 = { "Cả ba biển.", "Biển 2 và 3.", "Biển 1 và 3.", "Biến 1 và 2." };
		Answer a2_answer_11_set7 = new Answer(a2_answers_11_set7, 3, idList.get(160));
		answerRepo.save(a2_answer_11_set7);

		String[] a2_answers_12_set7 = { "Biển 1.", "Biển 2.", "Biển 3.", "Biển 1 và 3." };
		Answer a2_answer_12_set7 = new Answer(a2_answers_12_set7, 2, idList.get(161));
		answerRepo.save(a2_answer_12_set7);

		String[] a2_answers_13_set7 = { "Cho phép ô tô có tải trọng trục lớn hơn 7 tấn đi qua.",
				"Cho phép ô tô có tải trọng trên trục xe từ 7 tấn trở xuống đi qua." };
		Answer a2_answer_13_set7 = new Answer(a2_answers_13_set7, 1, idList.get(162));
		answerRepo.save(a2_answer_13_set7);

		String[] a2_answers_14_set7 = { "Không được phép.", "Được phép." };
		Answer a2_answer_14_set7 = new Answer(a2_answers_14_set7, 0, idList.get(163));
		answerRepo.save(a2_answer_14_set7);

		String[] a2_answers_15_set7 = { "Biển 1 và 2.", "Biển 1 và 3.", "Biển 2 và 3.", "Cả ba biển." };
		Answer a2_answer_15_set7 = new Answer(a2_answers_15_set7, 2, idList.get(164));
		answerRepo.save(a2_answer_15_set7);

		String[] a2_answers_16_set7 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_16_set7 = new Answer(a2_answers_16_set7, 1, idList.get(165));
		answerRepo.save(a2_answer_16_set7);

		String[] a2_answers_17_set7 = { "Biển 1.", "Biển 2.", "Biến 3.", "Cả 3 biển." };
		Answer a2_answer_17_set7 = new Answer(a2_answers_17_set7, 1, idList.get(166));
		answerRepo.save(a2_answer_17_set7);

		String[] a2_answers_18_set7 = { "Biển 1.", "Biển 2." };
		Answer a2_answer_18_set7 = new Answer(a2_answers_18_set7, 0, idList.get(167));
		answerRepo.save(a2_answer_18_set7);

		String[] a2_answers_19_set7 = { "Biển chỉ dẫn khu vực cấm đỗ xe trên các tuyến đường đối ngoại.",
				"Biển chỉ dẫn khu vực đỗ xe trên các tuyến đường đối ngoại.",
				"Biển hạn chế tốc độ tối đa trong khu vực.",
				"Biển chỉ dẫn hết hiệu lực khu vực cấm đỗ xe theo giờ trên các tuyến đường đối ngoại." };
		Answer a2_answer_19_set7 = new Answer(a2_answers_19_set7, 2, idList.get(168));
		answerRepo.save(a2_answer_19_set7);

		String[] a2_answers_20_set7 = { "Biển 1.", "Biển 2.", "Biến 3." };
		Answer a2_answer_20_set7 = new Answer(a2_answers_20_set7, 1, idList.get(169));
		answerRepo.save(a2_answer_20_set7);

		String[] a2_answers_21_set7 = { "Vị trí dừng xe của các phương tiện vận tải hành khách công cộng.",
				"Báo cho người điều khiển được dùng phương tiện trong phạm vi phần mặt đường có bố trí vạch để tránh ùn tắc giao thông.",
				"Dùng để xác định vị trí giữa các phương tiện trên đường." };
		Answer a2_answer_21_set7 = new Answer(a2_answers_21_set7, 0, idList.get(170));
		answerRepo.save(a2_answer_21_set7);

		String[] a2_answers_22_set7 = { "Cả hai xe.", "Không xe nào vi phạm.", "Chỉ xe mô tô vi phạm.",
				"Chỉ xe tải vi phạm." };
		Answer a2_answer_22_set7 = new Answer(a2_answers_22_set7, 0, idList.get(171));
		answerRepo.save(a2_answer_22_set7);

		String[] a2_answers_23_set7 = { "Xe khách và xe tải, xe con.", "Xe tải, xe khách, xe con.",
				"Xe con, xe khách, xe tải." };
		Answer a2_answer_23_set7 = new Answer(a2_answers_23_set7, 0, idList.get(172));
		answerRepo.save(a2_answer_23_set7);

		String[] a2_answers_24_set7 = { "Xe mô tô.", "Xe ô tô con.", "Không xe nào vi phạm.", "Cả hai xe." };
		Answer a2_answer_24_set7 = new Answer(a2_answers_24_set7, 3, idList.get(173));
		answerRepo.save(a2_answer_24_set7);

		String[] a2_answers_25_set7 = { "Xe của bạn, mô tô, xe đạp.", "Xe mô tô, xe đạp, xe của bạn.",
				"Xe đạp, xe mô tô, xe của bạn." };
		Answer a2_answer_25_set7 = new Answer(a2_answers_25_set7, 2, idList.get(174));
		answerRepo.save(a2_answer_25_set7);

		String[] a2_answers_1_set8 = {
				"Gồm xe ô tô, máy kéo, xe mô tô hai bánh; xe mô tô ba bánh, xe gắn máy, xe cơ giới dùng cho người khuyết tật và xe máy chuyên dùng.",
				"Gồm xe ô tô, máy kéo, rơ moóc hoặc sơ mi rơ moóc được kéo bởi xe ô tô, máy kéo; xe mô tô hai bánh; xe mô tô ba bánh, xe gắn máy (kể cả xe máy điện) và các loại xe tương tự." };
		Answer a2_answer_1_set8 = new Answer(a2_answers_1_set8, 1, idList.get(175));
		answerRepo.save(a2_answer_1_set8);

		String[] a2_answers_2_set8 = { "Bị nghiêm cấm tuỳ từng trường hợp.", "Không bị nghiêm cấm.", "Bị nghiêm cấm." };
		Answer a2_answer_2_set8 = new Answer(a2_answers_2_set8, 2, idList.get(176));
		answerRepo.save(a2_answer_2_set8);

		String[] a2_answers_3_set8 = { "Chỉ được thực hiện trên đường quốc lộ có hai làn xe một chiều.",
				"Chỉ được thực hiện trên đường cao tốc.", "Không được thực hiện vào ban ngày", "Không được phép." };
		Answer a2_answer_3_set8 = new Answer(a2_answers_3_set8, 3, idList.get(177));
		answerRepo.save(a2_answer_3_set8);

		String[] a2_answers_4_set8 = { "Biển báo nguy hiểm.", "Biển báo cấm.", "Biển báo hiệu lệnh phải thi hành.",
				"Biển báo chỉ dẫn." };
		Answer a2_answer_4_set8 = new Answer(a2_answers_4_set8, 3, idList.get(178));
		answerRepo.save(a2_answer_4_set8);

		String[] a2_answers_5_set8 = { "Nhường đường cho xe đi ở bên phải mình tới.",
				"Nhường đường cho xe đi ở bên trái mình tới.",
				"Nhường đường cho xe đi trên đường ưu tiên hoặc đường chính từ bất kỳ hướng nào tới." };
		Answer a2_answer_5_set8 = new Answer(a2_answers_5_set8, 2, idList.get(179));
		answerRepo.save(a2_answer_5_set8);

		String[] a2_answers_6_set8 = {
				"Nếu đủ điều kiện an toàn, người lái xe phải giảm tốc độ, đi sát về bên phải của phần đường xe chạy cho đến khi xe sau đã vượt qua, không được gây trở ngại đối với xe xin vượt.",
				"Lái xe vào lề đường bên trái và giảm tốc độ đế xe phía sau vượt qua, không được gây trở ngại đối với xe xin vượt.",
				"Nếu đủ điều kiện an toàn, người lái xe phải tăng tốc độ, đi sát về bên phải của phần đường xe chạy cho đến khi xe sau đã vượt qua." };
		Answer a2_answer_6_set8 = new Answer(a2_answers_6_set8, 0, idList.get(180));
		answerRepo.save(a2_answer_6_set8);

		String[] a2_answers_7_set8 = { "55 m.", "70 m.", "100 m." };
		Answer a2_answer_7_set8 = new Answer(a2_answers_7_set8, 2, idList.get(181));
		answerRepo.save(a2_answer_7_set8);

		String[] a2_answers_8_set8 = { "Ra tín hiệu bằng tay rồi cho xe vượt qua.",
				"Tăng ga mạnh để gây sự chú ý rồi cho xe vượt qua.", "Bạn phải có tín hiệu bằng đèn hoặc còi." };
		Answer a2_answer_8_set8 = new Answer(a2_answers_8_set8, 2, idList.get(182));
		answerRepo.save(a2_answer_8_set8);

		String[] a2_answers_9_set8 = {
				"Có giấy phép lái xe phù hợp với loại xe được phép điều khiển; xe cơ giới đảm bảo các quy định về chất lượng, an toàn kỹ thuật và bảo vệ môi trường.",
				"Có giấy chứng nhận bảo hiểm trách nhiệm dân sự của chủ xe cơ giới còn hiệu lực; nộp phí sử dụng đường bộ theo quy định.",
				"Cả ý 1 và ý 2." };
		Answer a2_answer_9_set8 = new Answer(a2_answers_9_set8, 2, idList.get(183));
		answerRepo.save(a2_answer_9_set8);

		String[] a2_answers_10_set8 = { "Giảm tốc độ, về số thấp và giữ đều ga.", "Tăng tốc độ cho xe lướt qua nhanh.",
				"Tăng tốc độ, đánh lái liên tục để tránh “ổ gà”." };
		Answer a2_answer_10_set8 = new Answer(a2_answers_10_set8, 0, idList.get(184));
		answerRepo.save(a2_answer_10_set8);

		String[] a2_answers_11_set8 = { "Biển 1.", "Biển 2 và 3.", "Biển 1 và 3.", "Cả ba biến." };
		Answer a2_answer_11_set8 = new Answer(a2_answers_11_set8, 1, idList.get(185));
		answerRepo.save(a2_answer_11_set8);

		String[] a2_answers_12_set8 = { "Biển 1.", "Biển 2.", "Không biển nào." };
		Answer a2_answer_12_set8 = new Answer(a2_answers_12_set8, 1, idList.get(186));
		answerRepo.save(a2_answer_12_set8);

		String[] a2_answers_13_set8 = { "Cấm các loại xe có tải trọng toàn bộ trên 10 tấn đi qua.",
				"Hạn chế khối lượng hàng hóa chở trên xe.", "Hạn chế tải trọng trên trục xe." };
		Answer a2_answer_13_set8 = new Answer(a2_answers_13_set8, 0, idList.get(187));
		answerRepo.save(a2_answer_13_set8);

		String[] a2_answers_14_set8 = { "Không được phép.", "Được phép." };
		Answer a2_answer_14_set8 = new Answer(a2_answers_14_set8, 0, idList.get(188));
		answerRepo.save(a2_answer_14_set8);

		String[] a2_answers_15_set8 = { "Biển 1.", "Biển 2.", "Biến 3.", "Biến 1 và 3." };
		Answer a2_answer_15_set8 = new Answer(a2_answers_15_set8, 0, idList.get(189));
		answerRepo.save(a2_answer_15_set8);

		String[] a2_answers_16_set8 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_16_set8 = new Answer(a2_answers_16_set8, 2, idList.get(190));
		answerRepo.save(a2_answer_16_set8);

		String[] a2_answers_17_set8 = { "Biển 1.", "Biển 2." };
		Answer a2_answer_17_set8 = new Answer(a2_answers_17_set8, 1, idList.get(191));
		answerRepo.save(a2_answer_17_set8);

		String[] a2_answers_18_set8 = { "Biển 1.", "Biển 2." };
		Answer a2_answer_18_set8 = new Answer(a2_answers_18_set8, 1, idList.get(192));
		answerRepo.save(a2_answer_18_set8);

		String[] a2_answers_19_set8 = { "Biến 1.", "Biển 2.", "Biến 3." };
		Answer a2_answer_19_set8 = new Answer(a2_answers_19_set8, 1, idList.get(193));
		answerRepo.save(a2_answer_19_set8);

		String[] a2_answers_20_set8 = { "Chỉ dẫn sắp đến vị trí nhập làn xe.", "Chỉ dẫn vị trí nhập làn xe cách 250 m.",
				"Chỉ dẫn vị trí nhập làn cách trạm thu phí 250 m." };
		Answer a2_answer_20_set8 = new Answer(a2_answers_20_set8, 1, idList.get(194));
		answerRepo.save(a2_answer_20_set8);

		String[] a2_answers_21_set8 = { "Xe tải, xe khách, xe con, mô tô", "Xe tải, mô tô, xe khách, xe con",
				"Xe khách, xe tải, xe con, mô tô" };
		Answer a2_answer_21_set8 = new Answer(a2_answers_21_set8, 1, idList.get(195));
		answerRepo.save(a2_answer_21_set8);

		String[] a2_answers_22_set8 = { "Chỉ mô tô.", "Chỉ xe tải.", "Cả ba xe.", "Chỉ mô tô và xe tải." };
		Answer a2_answer_22_set8 = new Answer(a2_answers_22_set8, 2, idList.get(196));
		answerRepo.save(a2_answer_22_set8);

		String[] a2_answers_23_set8 = { "Cả bốn hướng.", "Chỉ hướng 1 và 2.", "Trừ hướng 4." };
		Answer a2_answer_23_set8 = new Answer(a2_answers_23_set8, 2, idList.get(197));
		answerRepo.save(a2_answer_23_set8);

		String[] a2_answers_24_set8 = { "Xe con.", "Xe tải.", "Xe con, xe tải." };
		Answer a2_answer_24_set8 = new Answer(a2_answers_24_set8, 1, idList.get(198));
		answerRepo.save(a2_answer_24_set8);

		String[] a2_answers_25_set8 = { "Xe của bạn, xe tải, xe con.", "Xe con, xe tải, xe của bạn.",
				"Xe tải, xe của bạn, xe con.", "Xe của bạn, xe con, xe tải." };
		Answer a2_answer_25_set8 = new Answer(a2_answers_25_set8, 3, idList.get(199));
		answerRepo.save(a2_answer_25_set8);

		String[] a2_answers_1_set9 = {
				"Gồm xe đạp (kể cả xe đạp máy, xe đạp điện), xe xích lô, xe lăn dùng cho người khuyết tật, xe súc vật kéo và các loại xe tương tự.",
				"Gồm xe đạp (kể cả xe đạp máy, xe đạp điện), xe gắn máy, xe cơ giới dùng cho người khuyết tật và xe máy chuyên dùng.",
				"Gồm xe ô tô, máy kéo, rơ moóc hoặc sơ mi rơ moóc được kéo bởi xe ô tô, máy kéo." };
		Answer a2_answer_1_set9 = new Answer(a2_answers_1_set9, 0, idList.get(200));
		answerRepo.save(a2_answer_1_set9);

		String[] a2_answers_2_set9 = { "Chỉ lớn hơn tốc độ tối đa cho phép khi đường vắng.",
				"Chỉ lớn hơn tốc độ tối đa cho phép vào ban đêm.", "Không vượt quá tốc độ cho phép." };
		Answer a2_answer_2_set9 = new Answer(a2_answers_2_set9, 2, idList.get(201));
		answerRepo.save(a2_answer_2_set9);

		String[] a2_answers_3_set9 = { "Được phép.",
				"Nếu phương tiện được kéo, đẩy có khối lượng nhỏ hơn phương tiện của mình.", "Tuỳ trường hợp.",
				"Không được phép." };
		Answer a2_answer_3_set9 = new Answer(a2_answers_3_set9, 3, idList.get(202));
		answerRepo.save(a2_answer_3_set9);

		String[] a2_answers_4_set9 = {
				"Tại các cơ sở đào tạo lái xe có đủ điều kiện và phải bảo đảm công khai, minh bạch.",
				"Tại sân tập lái của cơ sở đào tạo lái xe và phải đảm bảo công khai, minh bạch.",
				"Tại các trung tâm sát hạch lái xe có đủ điều kiện hoạt động và phải bảo đảm công khai, minh bạch." };
		Answer a2_answer_4_set9 = new Answer(a2_answers_4_set9, 2, idList.get(203));
		answerRepo.save(a2_answer_4_set9);

		String[] a2_answers_5_set9 = { "Phải nhường đường cho xe đi đến từ bên phải.",
				"Xe báo hiệu xin đường trước xe đó được đi trước.", "Phải nhường đường cho xe đi đến từ bên trái." };
		Answer a2_answer_5_set9 = new Answer(a2_answers_5_set9, 0, idList.get(204));
		answerRepo.save(a2_answer_5_set9);

		String[] a2_answers_6_set9 = {
				"Phải đội mũ bảo hiểm đạt chuẩn, có cài quai đúng quy cách, mặc quần áo gọn gàng, không sử dụng ô, điện thoại di động, thiết bị âm thanh (trừ thiết bị trợ thính).",
				"Phải đội mũ bảo hiểm khi trời mưa gió hoặc trời quá nắng; có thể sử dụng ô, điện thoại di động, thiết bị âm thanh nhưng phải đảm bảo an toàn.",
				"Phải đội mũ bảo hiểm khi cảm thấy mất an toàn giao thông hoặc khi chuẩn bị di chuyển quãng đường xa." };
		Answer a2_answer_6_set9 = new Answer(a2_answers_6_set9, 0, idList.get(205));
		answerRepo.save(a2_answer_6_set9);

		String[] a2_answers_7_set9 = { "Chủ động giữ khoảng cách an toàn phù hợp với xe chạy liền trước xe của mình.",
				"Đảm bảo khoảng cách an toàn theo mật độ phương tiện, tình hình giao thông thực tế.",
				"Cả ý 1 và ý 2." };
		Answer a2_answer_7_set9 = new Answer(a2_answers_7_set9, 2, idList.get(206));
		answerRepo.save(a2_answer_7_set9);

		String[] a2_answers_8_set9 = { "0,25 mét.", "0,3 mét.", "0,4 mét.", "0,5 mét." };
		Answer a2_answer_8_set9 = new Answer(a2_answers_8_set9, 0, idList.get(207));
		answerRepo.save(a2_answer_8_set9);

		String[] a2_answers_9_set9 = {
				"Đặt các biến cảnh báo hoặc vật báo hiệu ở phía trước và phía sau hiện trường xảy ra tai nạn để cảnh báo; kiểm tra khả năng xảy ra hỏa hoạn do nhiên liệu bị rò rỉ; bảo vệ hiện trường vụ tai nạn và cấp cứu người bị thương.",
				"Đặt các biến cảnh báo hoặc vật báo hiệu ở phía trên nóc xe xảy ra tai nạn để cảnh báo; kiểm tra khả năng xảy ra mất an toàn do nước làm mát bị rò rỉ.",
				"Cả ý 1 và ý 2." };
		Answer a2_answer_9_set9 = new Answer(a2_answers_9_set9, 0, idList.get(208));
		answerRepo.save(a2_answer_9_set9);

		String[] a2_answers_10_set9 = { "Giảm tốc độ, nhường đường cho xe trên đường chính từ bất kì hướng nào tới.",
				"Nháy đèn, bấm còi để xe đi trên đường chính biết và tăng tốc độ cho xe đi ra đường chính.",
				"Quan sát xe đang đi trên đường chính, nếu là xe có kích thước lớn hơn thì nhường đường, xe có kích thước nhỏ hơn thì tăng tốc độ cho xe đi ra đường chính." };
		Answer a2_answer_10_set9 = new Answer(a2_answers_10_set9, 0, idList.get(209));
		answerRepo.save(a2_answer_10_set9);

		String[] a2_answers_11_set9 = { "Biển 1 và 2.", "Biển 1 và 3.", "Biến 2 và 3." };
		Answer a2_answer_11_set9 = new Answer(a2_answers_11_set9, 0, idList.get(210));
		answerRepo.save(a2_answer_11_set9);

		String[] a2_answers_12_set9 = { "Biển 1.", "Biển 2.", "Không biển nào." };
		Answer a2_answer_12_set9 = new Answer(a2_answers_12_set9, 1, idList.get(211));
		answerRepo.save(a2_answer_12_set9);

		String[] a2_answers_13_set9 = { "Biến 1.", "Biển 2.", "Cả hai biển." };
		Answer a2_answer_13_set9 = new Answer(a2_answers_13_set9, 2, idList.get(212));
		answerRepo.save(a2_answer_13_set9);

		String[] a2_answers_14_set9 = { "Cấm ô tô buýt.", "Cấm ô tô chở khách.", "Cấm ô tô con." };
		Answer a2_answer_14_set9 = new Answer(a2_answers_14_set9, 1, idList.get(213));
		answerRepo.save(a2_answer_14_set9);

		String[] a2_answers_15_set9 = { "Biển 1.", "Biển 2.", "Biến 3.",
				"Chú thích: Biển 1: cầu vòng, Biển 2: cửa chui, Biển 3: đường hầm. Đáp án đúng là đáp án 2." };
		Answer a2_answer_15_set9 = new Answer(a2_answers_15_set9, 1, idList.get(214));
		answerRepo.save(a2_answer_15_set9);

		String[] a2_answers_16_set9 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_16_set9 = new Answer(a2_answers_16_set9, 2, idList.get(215));
		answerRepo.save(a2_answer_16_set9);

		String[] a2_answers_17_set9 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_17_set9 = new Answer(a2_answers_17_set9, 1, idList.get(216));
		answerRepo.save(a2_answer_17_set9);

		String[] a2_answers_18_set9 = { "Biển 1.", "Biển 2." };
		Answer a2_answer_18_set9 = new Answer(a2_answers_18_set9, 0, idList.get(217));
		answerRepo.save(a2_answer_18_set9);

		String[] a2_answers_19_set9 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_19_set9 = new Answer(a2_answers_19_set9, 2, idList.get(218));
		answerRepo.save(a2_answer_19_set9);

		String[] a2_answers_20_set9 = { "Bắt đầu đường cao tốc.",
				"Chỉ dẫn địa giới đường cao tốc, chiều dài đường cao tốc.",
				"Tên và ký hiệu đường cao tốc, giá trị hạn chế tốc độ tối đa và tối thiểu.", "Cả ý 1 và ý 3." };
		Answer a2_answer_20_set9 = new Answer(a2_answers_20_set9, 3, idList.get(219));
		answerRepo.save(a2_answer_20_set9);

		String[] a2_answers_21_set9 = { "Xe tải, xe con, mô tô,", "Xe con, xe tải, mô tô,", "Mô tô, xe con, xe tải.",
				"Xe con, mô tô, xe tải." };
		Answer a2_answer_21_set9 = new Answer(a2_answers_21_set9, 2, idList.get(220));
		answerRepo.save(a2_answer_21_set9);

		String[] a2_answers_22_set9 = { "Đúng.", "Không đúng." };
		Answer a2_answer_22_set9 = new Answer(a2_answers_22_set9, 1, idList.get(221));
		answerRepo.save(a2_answer_22_set9);

		String[] a2_answers_23_set9 = { "Xe công an, xe con, xe tải, xe khách.",
				"Xe con, xe khách và xe công an, xe tải.", "Xe công an, xe con, xe khách, xe tải.",
				"Xe con, xe tải, xe khách, xe công an." };
		Answer a2_answer_23_set9 = new Answer(a2_answers_23_set9, 0, idList.get(222));
		answerRepo.save(a2_answer_23_set9);

		String[] a2_answers_24_set9 = { "Xe tải, xe con.", "Xe khách, xe con.", "Xe khách, xe tải." };
		Answer a2_answer_24_set9 = new Answer(a2_answers_24_set9, 2, idList.get(223));
		answerRepo.save(a2_answer_24_set9);

		String[] a2_answers_25_set9 = { "Xe của bạn.", "Xe con." };
		Answer a2_answer_25_set9 = new Answer(a2_answers_25_set9, 0, idList.get(224));
		answerRepo.save(a2_answer_25_set9);

		String[] a2_answers_1_set10 = { "Phương tiện giao thông cơ giới đường bộ.",
				"Phương tiện giao thông thô sơ đường bộ và xe máy chuyên dùng.", "Cả ý 1 và ý 2." };
		Answer a2_answer_1_set10 = new Answer(a2_answers_1_set10, 2, idList.get(225));
		answerRepo.save(a2_answer_1_set10);

		String[] a2_answers_2_set10 = { "Đi về phía bên trái.", "Đi về phía bên phải.", "Đi ở giữa." };
		Answer a2_answer_2_set10 = new Answer(a2_answers_2_set10, 1, idList.get(226));
		answerRepo.save(a2_answer_2_set10);

		String[] a2_answers_3_set10 = { "Được phép.", "Tuỳ trường hợp.", "Không được phép." };
		Answer a2_answer_3_set10 = new Answer(a2_answers_3_set10, 2, idList.get(227));
		answerRepo.save(a2_answer_3_set10);

		String[] a2_answers_4_set10 = {
				"Giấy chứng nhận tốt nghiệp khoá đào tạo của hạng xe đang điêu khiên, đăng ký xe, giấy phép lưu hành xe.",
				"Giấy phép lái xe phù hợp với loại xe đó; lệnh vận chuyển, đăng ký xe, giấy chứng nhận kiểm tra chất lượng an toàn kỹ thuật và bảo vệ môi trường của xe cơ giới sau khi cải tạo; giấy phép vận chuyển (nếu loại xe đó cần phải có).",
				"Giấy phép lái xe phù hợp với loại xe đó, đăng ký xe, giấy chứng nhận kiểm định kỹ thuật và bảo vệ môi trường, giấy chứng nhận bảo hiểm trách nhiệm dân sự của chủ xe cơ giới và giấy phép vận chuyển (nếu loại xe đó cần phải có), các giấy tờ phải còn giá trị sử dụng." };
		Answer a2_answer_4_set10 = new Answer(a2_answers_4_set10, 2, idList.get(228));
		answerRepo.save(a2_answer_4_set10);

		String[] a2_answers_5_set10 = { "Phương tiện nào bên phải không vướng.",
				"Phương tiện nào ra tín hiệu xin đường trước.", "Phương tiện giao thông đường sắt." };
		Answer a2_answer_5_set10 = new Answer(a2_answers_5_set10, 2, idList.get(229));
		answerRepo.save(a2_answer_5_set10);

		String[] a2_answers_6_set10 = {
				"Là đoạn đường nằm trong khu công nghiệp có đông người và phương tiện tham gia giao thông và được xác định cụ thế bằng biển chỉ dẫn địa giới.",
				"Là đoạn đường bộ nằm trong khu vực nội thành phố, nội thị xã, nội thị trấn và những đoạn đường có đông dân cư sinh sống sát dọc theo đường, có các hoạt động ảnh hưởng đến an toàn giao thông; được xác định bằng biển báo hiệu là đường khu đông dân cư.",
				"Là đoạn đường nằm ngoài khu vực nội thành phố, nội thi xã có đông người và phương tiện tham gia giao thông và được xác định cụ thể bằng biển chỉ dẫn địa giới." };
		Answer a2_answer_6_set10 = new Answer(a2_answers_6_set10, 1, idList.get(230));
		answerRepo.save(a2_answer_6_set10);

		String[] a2_answers_7_set10 = { "Gặp biển báo nguy hiểm trên đường.", "Gặp biển chỉ dẫn trên đường.",
				"Gặp biển báo hết mọi lệnh cấm.", "Gặp biển báo hết hạn chế tốc độ tối đa cho phép." };
		Answer a2_answer_7_set10 = new Answer(a2_answers_7_set10, 0, idList.get(231));
		answerRepo.save(a2_answer_7_set10);

		String[] a2_answers_8_set10 = { "5 mét.", "10 mét.", "15 mét.", "20 mét." };
		Answer a2_answer_8_set10 = new Answer(a2_answers_8_set10, 3, idList.get(232));
		answerRepo.save(a2_answer_8_set10);

		String[] a2_answers_9_set10 = {
				"Thực hiện sơ cứu ban đầu trong trường hợp khẩn cấp; thông báo vụ tai nạn đến cơ quan thi hành pháp luật.",
				"Nhanh chóng lái xe gây tai nạn hoặc đi nhờ xe khác ra khỏi hiện trường vụ tai nạn.",
				"Cả ý 1 và ý 2." };
		Answer a2_answer_9_set10 = new Answer(a2_answers_9_set10, 0, idList.get(233));
		answerRepo.save(a2_answer_9_set10);

		String[] a2_answers_10_set10 = {
				"Giảm tốc độ để đảm bảo an toàn với xe phía trước và sử dụng điện thoại để liên lạc.",
				"Giảm tốc độ để dừng xe ở nơi cho phép dừng xe sau đó sử dụng điện thoại để liên lạc.",
				"Tăng tốc độ để cách xa xe phía sau và sử dụng điện thoại để liên lạc." };
		Answer a2_answer_10_set10 = new Answer(a2_answers_10_set10, 1, idList.get(234));
		answerRepo.save(a2_answer_10_set10);

		String[] a2_answers_11_set10 = { "Biển 1.", "Biển 2.", "Cả hai biển.",
				"Giải thích: Biển 1 cấm mô tô, biển 2 cấm ô tô. Không phải cấm xe gắn máy." };
		Answer a2_answer_11_set10 = new Answer(a2_answers_11_set10, 2, idList.get(235));
		answerRepo.save(a2_answer_11_set10);

		String[] a2_answers_12_set10 = { "Biển 1.", "Biển 2.", "Cả hai biển." };
		Answer a2_answer_12_set10 = new Answer(a2_answers_12_set10, 2, idList.get(236));
		answerRepo.save(a2_answer_12_set10);

		String[] a2_answers_13_set10 = { "Được đi vào.", "Không được đi vào." };
		Answer a2_answer_13_set10 = new Answer(a2_answers_13_set10, 0, idList.get(237));
		answerRepo.save(a2_answer_13_set10);

		String[] a2_answers_14_set10 = { "Hạn chế chiều cao của xe và hàng.", "Hạn chế chiều ngang của xe và hàng.",
				"Hạn chế chiều dài của xe và hàng." };
		Answer a2_answer_14_set10 = new Answer(a2_answers_14_set10, 1, idList.get(238));
		answerRepo.save(a2_answer_14_set10);

		String[] a2_answers_15_set10 = { "Để chỉ nơi đường sắt giao vuông góc với đường bộ không có rào chắn.",
				"Để báo trước sắp đến vị trí giao cắt đường bộ với đường sắt cùng mức, không vuông góc và không có người gác, không có rào chắn.",
				"Nơi đường sắt giao nhau với đường bộ." };
		Answer a2_answer_15_set10 = new Answer(a2_answers_15_set10, 0, idList.get(239));
		answerRepo.save(a2_answer_15_set10);

		String[] a2_answers_16_set10 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_16_set10 = new Answer(a2_answers_16_set10, 0, idList.get(240));
		answerRepo.save(a2_answer_16_set10);

		String[] a2_answers_17_set10 = { "Biển 1.", "Biển 2.", "Biến 3.", "Cả 3 biển." };
		Answer a2_answer_17_set10 = new Answer(a2_answers_17_set10, 1, idList.get(241));
		answerRepo.save(a2_answer_17_set10);

		String[] a2_answers_18_set10 = { "Chỉ hướng đi phải theo.",
				"Biển báo hiệu cho người lái xe biết số lượng làn đường | trên mặt đường và hướng đi trên mỗi làn đường phải theo.",
				"Chỉ hướng đường phải theo." };
		Answer a2_answer_18_set10 = new Answer(a2_answers_18_set10, 1, idList.get(242));
		answerRepo.save(a2_answer_18_set10);

		String[] a2_answers_19_set10 = { "Biển 1 và 2.", "Cả ba biển.", "Không biển nào." };
		Answer a2_answer_19_set10 = new Answer(a2_answers_19_set10, 0, idList.get(243));
		answerRepo.save(a2_answer_19_set10);

		String[] a2_answers_20_set10 = { "Xăng dầu, ăn uống, thông tin, sửa chữa xe.",
				"Xăng dầu, ăn uống, nhà nghỉ, sửa chữa xe.", "Xăng dầu, ăn uống, cấp cứu, sửa chữa xe." };
		Answer a2_answer_20_set10 = new Answer(a2_answers_20_set10, 0, idList.get(244));
		answerRepo.save(a2_answer_20_set10);

		String[] a2_answers_21_set10 = { "Mô tô.", "Xe con." };
		Answer a2_answer_21_set10 = new Answer(a2_answers_21_set10, 1, idList.get(245));
		answerRepo.save(a2_answer_21_set10);

		String[] a2_answers_22_set10 = { "Hướng 2 và 5.", "Chỉ hướng 1." };
		Answer a2_answer_22_set10 = new Answer(a2_answers_22_set10, 1, idList.get(246));
		answerRepo.save(a2_answer_22_set10);

		String[] a2_answers_23_set10 = { "Chỉ xe khách, mô tô,", "Tất cả các loại xe trên.",
				"Không xe nào chấp hành đúng quy tắc giao thông." };
		Answer a2_answer_23_set10 = new Answer(a2_answers_23_set10, 1, idList.get(247));
		answerRepo.save(a2_answer_23_set10);

		String[] a2_answers_24_set10 = { "Xe con, xe tải, xe khách.", "Xe tải, xe khách, xe mô tô.",
				"Xe khách, xe mô tô, xe con.", "Cả bốn xe." };
		Answer a2_answer_24_set10 = new Answer(a2_answers_24_set10, 1, idList.get(248));
		answerRepo.save(a2_answer_24_set10);

		String[] a2_answers_25_set10 = {
				"Tăng tốc độ, đi qua vạch người đi bộ sang đường, để người đi bộ sang đường sau.",
				"Giảm tốc độ, đi qua vạch người đi bộ sang đường, để người đi bộ sang đường sau.",
				"Giảm tốc độ, để người đi bộ sang đường trước, sau đó cho xe đi qua vạch người đi bộ sang đường." };
		Answer a2_answer_25_set10 = new Answer(a2_answers_25_set10, 2, idList.get(249));
		answerRepo.save(a2_answer_25_set10);

		String[] a2_answers_1_set11 = { "Người điều khiển, người sử dụng phương tiện tham gia giao thông đường bộ.",
				"Người điều khiển, dẫn dắt súc vật, người đi bộ trên đường bộ.", "Cả ý 1 và ý 2." };
		Answer a2_answer_1_set11 = new Answer(a2_answers_1_set11, 2, idList.get(250));
		answerRepo.save(a2_answer_1_set11);

		String[] a2_answers_2_set11 = { "Đi ở làn bên phải trong cùng.", "Đi ở làn phía bên trái.", "Đi ở làn giữa.",
				"Đi ở bất cứ làn nào nhưng phải bấm đèn cảnh báo nguy hiểm để báo hiệu cho các phương tiện khác." };
		Answer a2_answer_2_set11 = new Answer(a2_answers_2_set11, 0, idList.get(251));
		answerRepo.save(a2_answer_2_set11);

		String[] a2_answers_3_set11 = {
				"Buông cả hai tay, sử dụng xe để kéo, đẩy xe khác, vật khác, sử dụng chân chống của xe quệt xuống đường khi xe đang chạy.",
				"Buông một tay, sử dụng xe để chở người hoặc hàng hoá để chân chạm xuống đất khi khởi hành.",
				"Đội mũ bảo hiểm; chạy xe đúng tốc độ quy định và chấp hành đúng quy tắc giao thông đường bộ.",
				"Chở người ngồi sau dưới 16 tuổi." };
		Answer a2_answer_3_set11 = new Answer(a2_answers_3_set11, 0, idList.get(252));
		answerRepo.save(a2_answer_3_set11);

		String[] a2_answers_4_set11 = { "02 năm.", "03 năm.", "05 năm.", "04 năm." };
		Answer a2_answer_4_set11 = new Answer(a2_answers_4_set11, 2, idList.get(253));
		answerRepo.save(a2_answer_4_set11);

		String[] a2_answers_5_set11 = { "5 mét.", "3 mét.", "4 mét." };
		Answer a2_answer_5_set11 = new Answer(a2_answers_5_set11, 0, idList.get(254));
		answerRepo.save(a2_answer_5_set11);

		String[] a2_answers_6_set11 = { "50 km/h.", "40 km/h.", "60 km/h." };
		Answer a2_answer_6_set11 = new Answer(a2_answers_6_set11, 1, idList.get(255));
		answerRepo.save(a2_answer_6_set11);

		String[] a2_answers_7_set11 = { "Khi vượt xe khác.", "Khi vượt xe khác tại đoạn đường được phép vượt.",
				"Khi xe sau xin vượt và đảm bảo an toàn.", "Khi xe sau có tín hiệu vượt bên phải." };
		Answer a2_answer_7_set11 = new Answer(a2_answers_7_set11, 2, idList.get(256));
		answerRepo.save(a2_answer_7_set11);

		String[] a2_answers_8_set11 = { "Được phép.", "Không được phép.", "Chỉ được phép dừng, đỗ khi đường vắng." };
		Answer a2_answer_8_set11 = new Answer(a2_answers_8_set11, 1, idList.get(257));
		answerRepo.save(a2_answer_8_set11);

		String[] a2_answers_9_set11 = { "Đặt nạn nhân nằm ngửa, khai thông đường thở của nạn nhân.",
				"Thực hiện các biện pháp hô hấp nhân tạo.", "Cả ý 1 và ý 2." };
		Answer a2_answer_9_set11 = new Answer(a2_answers_9_set11, 2, idList.get(258));
		answerRepo.save(a2_answer_9_set11);

		String[] a2_answers_10_set11 = { "Sử dụng còi.", "Phanh đồng thời cả phanh trước và phanh sau.",
				"Chỉ sử dụng phanh trước." };
		Answer a2_answer_10_set11 = new Answer(a2_answers_10_set11, 2, idList.get(259));
		answerRepo.save(a2_answer_10_set11);

		String[] a2_answers_11_set11 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_11_set11 = new Answer(a2_answers_11_set11, 0, idList.get(260));
		answerRepo.save(a2_answer_11_set11);

		String[] a2_answers_12_set11 = { "Biển 1.", "Biển 2.", "Cả hai biển." };
		Answer a2_answer_12_set11 = new Answer(a2_answers_12_set11, 0, idList.get(261));
		answerRepo.save(a2_answer_12_set11);

		String[] a2_answers_13_set11 = { "Biển 1 và 2.", "Biển 2 và 3.", "Biển 1 và 3.", "Cả ba biển." };
		Answer a2_answer_13_set11 = new Answer(a2_answers_13_set11, 0, idList.get(262));
		answerRepo.save(a2_answer_13_set11);

		String[] a2_answers_14_set11 = { "Biển 1.", "Biển 2.", "Cả 2 biển." };
		Answer a2_answer_14_set11 = new Answer(a2_answers_14_set11, 0, idList.get(263));
		answerRepo.save(a2_answer_14_set11);

		String[] a2_answers_15_set11 = { "Biển 1.", "Biển 2.", "Biển 3.", "Biển 1 và 2." };
		Answer a2_answer_15_set11 = new Answer(a2_answers_15_set11, 3, idList.get(264));
		answerRepo.save(a2_answer_15_set11);

		String[] a2_answers_16_set11 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_16_set11 = new Answer(a2_answers_16_set11, 1, idList.get(265));
		answerRepo.save(a2_answer_16_set11);

		String[] a2_answers_17_set11 = { "Biển 1.", "Biển 2.", "Cả hai biển." };
		Answer a2_answer_17_set11 = new Answer(a2_answers_17_set11, 0, idList.get(266));
		answerRepo.save(a2_answer_17_set11);

		String[] a2_answers_18_set11 = { "Biển 1.", "Biển 2.", "Không biển nào." };
		Answer a2_answer_18_set11 = new Answer(a2_answers_18_set11, 2, idList.get(267));
		answerRepo.save(a2_answer_18_set11);

		String[] a2_answers_19_set11 = { "Biển 1.", "Biển 2.", "Cả hai biển.", "Không biển nào." };
		Answer a2_answer_19_set11 = new Answer(a2_answers_19_set11, 0, idList.get(268));
		answerRepo.save(a2_answer_19_set11);

		String[] a2_answers_20_set11 = { "Chỉ dẫn đến trạm kiểm tra tải trọng xe.",
				"Chỉ dẫn hướng rẽ vào nơi đặt trạm kiểm tra tải trọng xe.",
				"Chỉ dẫn khoảng cách đến trạm kiểm tra tải trọng xe cách 750 m." };
		Answer a2_answer_20_set11 = new Answer(a2_answers_20_set11, 2, idList.get(269));
		answerRepo.save(a2_answer_20_set11);

		String[] a2_answers_21_set11 = { "Xe cứu thương, xe cứu hỏa, xe con.", "Xe cứu hỏa, xe cứu thương, xe con.",
				"Xe cứu thương, xe con, xe cứu hỏa." };
		Answer a2_answer_21_set11 = new Answer(a2_answers_21_set11, 1, idList.get(270));
		answerRepo.save(a2_answer_21_set11);

		String[] a2_answers_22_set11 = { "Hướng 1 và 2.", "Hướng 3.", "Hướng 1 và 4.", "Hướng 2 và 3." };
		Answer a2_answer_22_set11 = new Answer(a2_answers_22_set11, 1, idList.get(271));
		answerRepo.save(a2_answer_22_set11);

		String[] a2_answers_23_set11 = { "Cả ba hướng.", "Hướng 1 và 2.", "Hướng 1 và 3.", "Hướng 2 và 3." };
		Answer a2_answer_23_set11 = new Answer(a2_answers_23_set11, 2, idList.get(272));
		answerRepo.save(a2_answer_23_set11);

		String[] a2_answers_24_set11 = { "Xe khách, xe tải.", "Xe khách, xe con.", "Xe con, xe tải.",
				"Xe khách, xe tải, xe con." };
		Answer a2_answer_24_set11 = new Answer(a2_answers_24_set11, 0, idList.get(273));
		answerRepo.save(a2_answer_24_set11);

		String[] a2_answers_25_set11 = { "Xe con.", "Xe của bạn." };
		Answer a2_answer_25_set11 = new Answer(a2_answers_25_set11, 1, idList.get(274));
		answerRepo.save(a2_answer_25_set11);

		String[] a2_answers_1_set12 = { "Người điều khiển xe cơ giới, người điều khiển xe thô sơ.",
				"Người điều khiển xe máy chuyên dùng tham gia giao thông đường bộ.", "Cả ý 1 và ý 2." };
		Answer a2_answer_1_set12 = new Answer(a2_answers_1_set12, 2, idList.get(275));
		answerRepo.save(a2_answer_1_set12);

		String[] a2_answers_2_set12 = { "Không bị nghiêm cấm.", "Không bị nghiêm cấm khi rất vội.", "Bị nghiêm cấm.",
				"Không bị nghiêm cấm khi khẩn cấp." };
		Answer a2_answer_2_set12 = new Answer(a2_answers_2_set12, 2, idList.get(276));
		answerRepo.save(a2_answer_2_set12);

		String[] a2_answers_3_set12 = { "Được mang, vác tuỳ trường hợp cụ thể.", "Không được mang, vác.",
				"Được mang, vác nhưng phải đảm bảo an toàn.", "Được mang, vác tùy theo sức khỏe của bản thân." };
		Answer a2_answer_3_set12 = new Answer(a2_answers_3_set12, 1, idList.get(277));
		answerRepo.save(a2_answer_3_set12);

		String[] a2_answers_4_set12 = { "Người tham gia giao thông ở các hướng phải dừng lại.",
				"Người tham gia giao thông ở các hướng được đi theo chiều gậy chỉ của cảnh sát giao thông.",
				"Người tham gia giao thông ở phía trước và phía sau người điều khiển được đi tất cả các hướng; người tham gia giao thông ở phía bên phải và phía bên trái người điều khiển phải dừng lại.",
				"Người tham gia giao thông ở phía trước và phía sau người điều khiển phải dừng lại, người tham giao thông ở phía bên phải và bên trái người điều khiển được đi tất cả các hướng." };
		Answer a2_answer_4_set12 = new Answer(a2_answers_4_set12, 3, idList.get(278));
		answerRepo.save(a2_answer_4_set12);

		String[] a2_answers_5_set12 = {
				"Người đi bộ, xe thô sơ, xe gắn máy, xe mô tô và máy kéo; xe máy chuyên dùng có tốc độ thiết kế nhỏ hơn 70km/h.",
				"Xe mô tô và xe máy chuyên dùng có tốc độ thiết kế lớn hơn 70km/h.",
				"Người đi bộ, xe thô sơ, xe gắn máy và xe ô tô." };
		Answer a2_answer_5_set12 = new Answer(a2_answers_5_set12, 0, idList.get(279));
		answerRepo.save(a2_answer_5_set12);

		String[] a2_answers_6_set12 = { "60 km/h.", "50 km/h.", "40 km/h." };
		Answer a2_answer_6_set12 = new Answer(a2_answers_6_set12, 0, idList.get(280));
		answerRepo.save(a2_answer_6_set12);

		String[] a2_answers_7_set12 = { "Xe cứu hỏa.", "Xe cứu thương.", "Phương tiện giao thông đường sắt.",
				"Ô tô, mô tô và xe máy chuyên dùng." };
		Answer a2_answer_7_set12 = new Answer(a2_answers_7_set12, 2, idList.get(281));
		answerRepo.save(a2_answer_7_set12);

		String[] a2_answers_8_set12 = { "Đường ướt, đường có sỏi cát trên nền đường.",
				"Đường hợp có nhiều điểm giao cắt từ hai phía.", "Đường đèo dốc, vòng liên tục.",
				"Tất cả các ý nêu trên." };
		Answer a2_answer_8_set12 = new Answer(a2_answers_8_set12, 3, idList.get(282));
		answerRepo.save(a2_answer_8_set12);

		String[] a2_answers_9_set12 = { "Không bị nghiêm cấm.", "Nghiêm cấm tuỳ từng trường hợp cụ thể.",
				"Bị nghiêm cấm." };
		Answer a2_answer_9_set12 = new Answer(a2_answers_9_set12, 2, idList.get(283));
		answerRepo.save(a2_answer_9_set12);

		String[] a2_answers_10_set12 = {
				"Bật tín hiệu báo rẽ trước khi quay đầu, từ từ giảm tốc độ đến mức có thể dừng lại.",
				"Chỉ quay đầu xe tại những nơi được phép quay đầu.",
				"Quan sát an toàn các phương tiện tới từ phía trước, phía sau, hai bên đồng thời nhường đường cho xe từ bên phải và phía trước đi tới.",
				"Tất cả các ý nêu trên." };
		Answer a2_answer_10_set12 = new Answer(a2_answers_10_set12, 3, idList.get(284));
		answerRepo.save(a2_answer_10_set12);

		String[] a2_answers_11_set12 = { "Không biển nào.", "Biển 1 và 2.", "Biển 2 và 3.", "Cả ba biến." };
		Answer a2_answer_11_set12 = new Answer(a2_answers_11_set12, 2, idList.get(285));
		answerRepo.save(a2_answer_11_set12);

		String[] a2_answers_12_set12 = { "Biển 1.", "Biển 2.", "Cả ba biển." };
		Answer a2_answer_12_set12 = new Answer(a2_answers_12_set12, 1, idList.get(286));
		answerRepo.save(a2_answer_12_set12);

		String[] a2_answers_13_set12 = { "Biển 1 và 2.", "Biển 1 và 3.", "Biển 2 và 3.", "Cả ba biển." };
		Answer a2_answer_13_set12 = new Answer(a2_answers_13_set12, 2, idList.get(287));
		answerRepo.save(a2_answer_13_set12);

		String[] a2_answers_14_set12 = { "Biển 1.", "Biển 2." };
		Answer a2_answer_14_set12 = new Answer(a2_answers_14_set12, 0, idList.get(288));
		answerRepo.save(a2_answer_14_set12);

		String[] a2_answers_15_set12 = { "Nơi đường sắt giao nhau với đường bộ.",
				"Nơi đường sắt giao vuông góc với đường bộ.",
				"Để báo trước sắp đến vị trí đường sắt giao thông vuông góc với đường bộ, không có người gác và không có rào chắn." };
		Answer a2_answer_15_set12 = new Answer(a2_answers_15_set12, 2, idList.get(289));
		answerRepo.save(a2_answer_15_set12);

		String[] a2_answers_16_set12 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_16_set12 = new Answer(a2_answers_16_set12, 1, idList.get(290));
		answerRepo.save(a2_answer_16_set12);

		String[] a2_answers_17_set12 = { "Biển 1.", "Biển 2." };
		Answer a2_answer_17_set12 = new Answer(a2_answers_17_set12, 0, idList.get(291));
		answerRepo.save(a2_answer_17_set12);

		String[] a2_answers_18_set12 = { "Biển 1.", "Biển 2." };
		Answer a2_answer_18_set12 = new Answer(a2_answers_18_set12, 0, idList.get(292));
		answerRepo.save(a2_answer_18_set12);

		String[] a2_answers_19_set12 = { "Biển 1.", "Biển 2.", "Cả hai biển.", "Không biển nào." };
		Answer a2_answer_19_set12 = new Answer(a2_answers_19_set12, 1, idList.get(293));
		answerRepo.save(a2_answer_19_set12);

		String[] a2_answers_20_set12 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_20_set12 = new Answer(a2_answers_20_set12, 0, idList.get(294));
		answerRepo.save(a2_answer_20_set12);

		String[] a2_answers_21_set12 = { "Mô tô.", "Xe cứu thương." };
		Answer a2_answer_21_set12 = new Answer(a2_answers_21_set12, 1, idList.get(295));
		answerRepo.save(a2_answer_21_set12);

		String[] a2_answers_22_set12 = { "Cả 2 xe đều đúng.", "Xe con.", "Xe khách." };
		Answer a2_answer_22_set12 = new Answer(a2_answers_22_set12, 0, idList.get(296));
		answerRepo.save(a2_answer_22_set12);

		String[] a2_answers_23_set12 = { "Xe công an, xe quân sự, xe con + mô tô.",
				"Xe quân sự, xe công an, xe con + mô tô.", "Xe mô tô + xe con, xe quân sự, xe công an." };
		Answer a2_answer_23_set12 = new Answer(a2_answers_23_set12, 1, idList.get(297));
		answerRepo.save(a2_answer_23_set12);

		String[] a2_answers_24_set12 = { "Xe tải, mô tô.", "Xe khách, mô tô.", "Xe tải, xe con.", "Mô tô, xe con." };
		Answer a2_answer_24_set12 = new Answer(a2_answers_24_set12, 1, idList.get(298));
		answerRepo.save(a2_answer_24_set12);

		String[] a2_answers_25_set12 = { "Xe con, xe tải, xe của bạn.", "Xe tải, xe con, xe của bạn.",
				"Xe tải, xe của bạn, xe con." };
		Answer a2_answer_25_set12 = new Answer(a2_answers_25_set12, 1, idList.get(299));
		answerRepo.save(a2_answer_25_set12);

		String[] a2_answers_1_set13 = {
				"Là người điều khiển phương tiện tham gia giao thông tại nơi thi công, nơi ùn tắc giao thông, ở bến phà, tại cầu đường bộ đi chung với đường sắt.",
				"Là cảnh sát giao thông, người được giao nhiệm vụ hướng dẫn giao thông tại nơi thi công, nơi ùn tắc giao thông, ở bến phà, tại cầu đường bộ đi chung với đường sắt.",
				"Là người tham gia giao thông tại nơi thi công, nơi ùn tắc giao thông, ở bến phà, tại cầu đường bộ đi chung với đường sắt." };
		Answer a2_answer_1_set13 = new Answer(a2_answers_1_set13, 1, idList.get(300));
		answerRepo.save(a2_answer_1_set13);

		String[] a2_answers_2_set13 = { "Từ 22 giờ đêm đến 5 giờ sáng.", "Từ 5 giờ sáng đến 22 giờ tối.",
				"Từ 23 giờ đêm đến 5 giờ sáng hôm sau." };
		Answer a2_answer_2_set13 = new Answer(a2_answers_2_set13, 1, idList.get(301));
		answerRepo.save(a2_answer_2_set13);

		String[] a2_answers_3_set13 = { "Được phép.", "Được làm trong trường hợp phương tiện của mình bị hỏng.",
				"Được kéo, đẩy trong trường hợp phương tiện khác bị hỏng.", "Không được phép." };
		Answer a2_answer_3_set13 = new Answer(a2_answers_3_set13, 3, idList.get(302));
		answerRepo.save(a2_answer_3_set13);

		String[] a2_answers_4_set13 = {
				"Người tham gia giao thông ở hướng đối diện cảnh sát giao thông được đi, các hướng khác cần phải dừng lại.",
				"Người tham gia giao thông được rẽ phải theo chiều mũi tên màu xanh ở bục cảnh sát giao thông.",
				"Người tham gia giao thông ở các hướng đều phải dừng lại trừ các xe đã ở trong khu vực giao nhau.",
				"Người ở hướng đối diện cảnh sát giao thông phải dừng lại, các hướng khác được đi trong đó có bạn." };
		Answer a2_answer_4_set13 = new Answer(a2_answers_4_set13, 2, idList.get(303));
		answerRepo.save(a2_answer_4_set13);

		String[] a2_answers_5_set13 = {
				"Xe cơ giới, xe máy chuyên dùng phải bật đèn; xe thô sơ phải bật đèn hoặc có vật phát sáng báo hiệu; chỉ được dừng xe, đỗ xe ở nơi quy định.",
				"Xe cơ giới phải bật đèn ngay cả khi đường hầm sáng; phải cho xe chạy trên một làn đường và chỉ chuyển làn ở nơi được phép; được quay đầu xe, lùi xe khi cần thiết.",
				"Xe máy chuyên dùng phải bật đèn ngay cả khi đường hầm sáng; phải cho xe chạy trên một làn đường và chỉ chuyến làn ở nơi được phép; được quay đầu xe, lùi xe khi cần thiết." };
		Answer a2_answer_5_set13 = new Answer(a2_answers_5_set13, 0, idList.get(304));
		answerRepo.save(a2_answer_5_set13);

		String[] a2_answers_6_set13 = { "60 km/h.", "50 km/h.", "40 km/h." };
		Answer a2_answer_6_set13 = new Answer(a2_answers_6_set13, 1, idList.get(305));
		answerRepo.save(a2_answer_6_set13);

		String[] a2_answers_7_set13 = { "Hiệu lệnh của nhân viên gác chắn.", "Đèn đỏ sáng nháy, cờ đỏ, biến đỏ.",
				"Còi, chuông kêu, chẳn đã đóng.", "Tất cả các ý trên." };
		Answer a2_answer_7_set13 = new Answer(a2_answers_7_set13, 3, idList.get(306));
		answerRepo.save(a2_answer_7_set13);

		String[] a2_answers_8_set13 = { "Tăng tốc độ để nhanh chóng vượt qua bến đỗ.",
				"Giảm tốc độ đến mức an toàn có thể và quan sát người qua đường và từ từ vượt qua xe buýt.",
				"Yêu cầu phải dừng lại phía sau xe buýt chờ xe rời bến mới đi tiếp." };
		Answer a2_answer_8_set13 = new Answer(a2_answers_8_set13, 1, idList.get(307));
		answerRepo.save(a2_answer_8_set13);

		String[] a2_answers_9_set13 = { "Xâm phạm tính mạng, sức khoẻ, tài sản của người bị nạn và người gây tai nạn.",
				"Bỏ trốn sau khi gây ra tai nạn để trốn tránh trách nhiệm.", "Cả ý 1 và ý 2." };
		Answer a2_answer_9_set13 = new Answer(a2_answers_9_set13, 2, idList.get(308));
		answerRepo.save(a2_answer_9_set13);

		String[] a2_answers_10_set13 = { "Để điều khiển xe chạy về phía trước.",
				"Để điều tiết công suất động cơ qua đó điều khiển tốc độ của xe.", "Để điều khiển xe chạy lùi.",
				"Cả ý 1 và ý 2." };
		Answer a2_answer_10_set13 = new Answer(a2_answers_10_set13, 3, idList.get(309));
		answerRepo.save(a2_answer_10_set13);

		String[] a2_answers_11_set13 = { "Biển 1 và 2.", "Biển 2.", "Biển 1 và 3.", "Biển 2 và 3." };
		Answer a2_answer_11_set13 = new Answer(a2_answers_11_set13, 2, idList.get(310));
		answerRepo.save(a2_answer_11_set13);

		String[] a2_answers_12_set13 = { "Biển 1.", "Biển 2.", "Biển 1 và 2." };
		Answer a2_answer_12_set13 = new Answer(a2_answers_12_set13, 2, idList.get(311));
		answerRepo.save(a2_answer_12_set13);

		String[] a2_answers_13_set13 = { "Được phép.", "Không được phép." };
		Answer a2_answer_13_set13 = new Answer(a2_answers_13_set13, 1, idList.get(312));
		answerRepo.save(a2_answer_13_set13);

		String[] a2_answers_14_set13 = { "Biển 1.", "Biển 2." };
		Answer a2_answer_14_set13 = new Answer(a2_answers_14_set13, 1, idList.get(313));
		answerRepo.save(a2_answer_14_set13);

		String[] a2_answers_15_set13 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_15_set13 = new Answer(a2_answers_15_set13, 2, idList.get(314));
		answerRepo.save(a2_answer_15_set13);

		String[] a2_answers_16_set13 = { "Biển 1.", "Biển 2 và 3.", "Cả ba biển." };
		Answer a2_answer_16_set13 = new Answer(a2_answers_16_set13, 1, idList.get(315));
		answerRepo.save(a2_answer_16_set13);

		String[] a2_answers_17_set13 = { "Biển 1.", "Biển 2." };
		Answer a2_answer_17_set13 = new Answer(a2_answers_17_set13, 1, idList.get(316));
		answerRepo.save(a2_answer_17_set13);

		String[] a2_answers_18_set13 = { "Biển 1.", "Biển 2.", "Cả hai biển." };
		Answer a2_answer_18_set13 = new Answer(a2_answers_18_set13, 1, idList.get(317));
		answerRepo.save(a2_answer_18_set13);

		String[] a2_answers_19_set13 = { "Biển 1.", "Biển 2.", "Biến 3." };
		Answer a2_answer_19_set13 = new Answer(a2_answers_19_set13, 1, idList.get(318));
		answerRepo.save(a2_answer_19_set13);

		String[] a2_answers_20_set13 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_20_set13 = new Answer(a2_answers_20_set13, 2, idList.get(319));
		answerRepo.save(a2_answer_20_set13);

		String[] a2_answers_21_set13 = { "Xe khách.", "Xe tải.", "Xe con." };
		Answer a2_answer_21_set13 = new Answer(a2_answers_21_set13, 0, idList.get(320));
		answerRepo.save(a2_answer_21_set13);

		String[] a2_answers_22_set13 = { "Hướng 2 và 3.", "Hướng 1, 2 và 3.", "Hướng 1 và 3." };
		Answer a2_answer_22_set13 = new Answer(a2_answers_22_set13, 0, idList.get(321));
		answerRepo.save(a2_answer_22_set13);

		String[] a2_answers_23_set13 = { "Không vi phạm.", "Vi phạm." };
		Answer a2_answer_23_set13 = new Answer(a2_answers_23_set13, 1, idList.get(322));
		answerRepo.save(a2_answer_23_set13);

		String[] a2_answers_24_set13 = { "Xe con (A), xe con (B), xe tải (D).", "Xe tải (D), xe con (E), xe buýt (G).",
				"Xe tải (D), xe con (B).", "Xe con (B), xe con (C)." };
		Answer a2_answer_24_set13 = new Answer(a2_answers_24_set13, 2, idList.get(323));
		answerRepo.save(a2_answer_24_set13);

		String[] a2_answers_25_set13 = { "Xe tải.", "Xe của bạn." };
		Answer a2_answer_25_set13 = new Answer(a2_answers_25_set13, 0, idList.get(324));
		answerRepo.save(a2_answer_25_set13);

		String[] a2_answers_1_set14 = {
				"Là trạng thái đứng yên của phương tiện giao thông không giới hạn thời gian để cho người lên, xuống phương tiện, xếp dỡ hàng hóa hoặc thực hiện công việc khác.",
				"Là trạng thái đứng yên tạm thời của phương tiện giao thông trong một khoảng thời gian cần thiết đủ để cho người lên, xuống phương tiện, xếp dỡ hàng hóa hoặc thực hiện công việc khác.",
				"Là trạng thái đứng yên của phương tiện giao thông không giới hạn thời gian giữa 02 lần vận chuyển hàng hoá hoặc hành khách." };
		Answer a2_answer_1_set14 = new Answer(a2_answers_1_set14, 1, idList.get(325));
		answerRepo.save(a2_answer_1_set14);

		String[] a2_answers_2_set14 = { "Bất cứ đèn nào miễn là mắt nhìn rõ phía trước.",
				"Chỉ bật đèn chiếu xa (đèn pha) khi không nhìn rõ đường.",
				"Đèn chiếu xa (đèn pha) khi đường vắng, đèn pha chiếu gần (đèn cốt) khi có xe đi ngược chiều.",
				"Đèn chiếu gần (đèn cốt)." };
		Answer a2_answer_2_set14 = new Answer(a2_answers_2_set14, 3, idList.get(326));
		answerRepo.save(a2_answer_2_set14);

		String[] a2_answers_3_set14 = { "Được sử dụng.", "Chỉ người ngồi sau được sử dụng.", "Không được sử dụng.",
				"Được sử dụng nếu không có áo mưa." };
		Answer a2_answer_3_set14 = new Answer(a2_answers_3_set14, 2, idList.get(327));
		answerRepo.save(a2_answer_3_set14);

		String[] a2_answers_4_set14 = { "Hiệu lệnh của người điều khiển giao thông.",
				"Hiệu lệnh của đèn điều khiển giao thông.", "Hiệu lệnh của biển báo hiệu đường bộ.",
				"Theo quyết định của người tham gia giao thông nhưng phải bảo đảm an toàn." };
		Answer a2_answer_4_set14 = new Answer(a2_answers_4_set14, 0, idList.get(328));
		answerRepo.save(a2_answer_4_set14);

		String[] a2_answers_5_set14 = {
				"Phải được cơ quan quản lý đường bộ có thẩm quyền cấp phép và phải thực hiện các biện pháp bắt buộc để bảo vệ đường bộ, bảo đảm an toàn giao thông.",
				"Chủ phương tiện và lái xe chỉ cần thực hiện biện pháp để hạn chế việc gây hư hại đường bộ.",
				"Được tham gia giao thông trên đường rộng.", "Chỉ được tham gia giao thông vào ban đêm." };
		Answer a2_answer_5_set14 = new Answer(a2_answers_5_set14, 0, idList.get(329));
		answerRepo.save(a2_answer_5_set14);

		String[] a2_answers_6_set14 = { "Ô tô con, ô tô tải, ô tô chở người trên 30 chỗ.",
				"Xe gắn máy, xe máy chuyên dùng.", "Cả ý 1 và ý 2." };
		Answer a2_answer_6_set14 = new Answer(a2_answers_6_set14, 0, idList.get(330));
		answerRepo.save(a2_answer_6_set14);

		String[] a2_answers_7_set14 = { "Không được phép.", "Được phép nhưng phải đảm bảo an toàn.",
				"Được phép tùy từng hoàn cảnh và điều kiện cụ thể." };
		Answer a2_answer_7_set14 = new Answer(a2_answers_7_set14, 0, idList.get(331));
		answerRepo.save(a2_answer_7_set14);

		String[] a2_answers_8_set14 = {
				"Được khách hàng, xã hội tôn trọng, được đồng nghiệp quý mến, giúp đỡ; được doanh nghiệp tin dùng và đóng góp nhiều cho xã hội.",
				"Thu hút được khách hàng, góp phần quan trọng trong xây dựng thương hiệu, kinh doanh có hiệu quả cao.",
				"Cả ý 1 và ý 2." };
		Answer a2_answer_8_set14 = new Answer(a2_answers_8_set14, 2, idList.get(332));
		answerRepo.save(a2_answer_8_set14);

		String[] a2_answers_9_set14 = { "Xâm phạm tính mạng, sức khoẻ, tài sản của người bị nạn và người gây tai nạn.",
				"Sơ cứu người bị nạn khi cơ quan có thẩm quyền chưa cho phép.",
				"Sơ cứu người gây tai nạn khi cơ quan có thẩm quyền chưa cho phép." };
		Answer a2_answer_9_set14 = new Answer(a2_answers_9_set14, 0, idList.get(333));
		answerRepo.save(a2_answer_9_set14);

		String[] a2_answers_10_set14 = { "Để quan sát an toàn phía bên trái khi chuẩn bị rẽ trái.",
				"Để quan sát an toàn phía bên phải khi chuẩn bị rẽ phải.",
				"Để quan sát an toàn phía sau cả bên trái và bên phải trước khi chuyển hướng.",
				"Để quan sát an toàn phía trước cả bên trái và bên phải trước khi chuyển hướng." };
		Answer a2_answer_10_set14 = new Answer(a2_answers_10_set14, 2, idList.get(334));
		answerRepo.save(a2_answer_10_set14);

		String[] a2_answers_11_set14 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_11_set14 = new Answer(a2_answers_11_set14, 1, idList.get(335));
		answerRepo.save(a2_answer_11_set14);

		String[] a2_answers_12_set14 = { "Biển 1.", "Biển 1 và 3.", "Biển 2 và 3.", "Biển 3." };
		Answer a2_answer_12_set14 = new Answer(a2_answers_12_set14, 1, idList.get(336));
		answerRepo.save(a2_answer_12_set14);

		String[] a2_answers_13_set14 = { "Có.", "Không." };
		Answer a2_answer_13_set14 = new Answer(a2_answers_13_set14, 0, idList.get(337));
		answerRepo.save(a2_answer_13_set14);

		String[] a2_answers_14_set14 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_14_set14 = new Answer(a2_answers_14_set14, 1, idList.get(338));
		answerRepo.save(a2_answer_14_set14);

		String[] a2_answers_15_set14 = { "Biển 1 và 2.", "Biển 1 và 3.", "Biển 2 và 3.", "Cả ba biển." };
		Answer a2_answer_15_set14 = new Answer(a2_answers_15_set14, 1, idList.get(339));
		answerRepo.save(a2_answer_15_set14);

		String[] a2_answers_16_set14 = { "Biển 1.", "Biển 2.", "Cả hai biển." };
		Answer a2_answer_16_set14 = new Answer(a2_answers_16_set14, 1, idList.get(340));
		answerRepo.save(a2_answer_16_set14);

		String[] a2_answers_17_set14 = {
				"Chỗ ngoặt nguy hiểm có nguy cơ lật xe bên phải khi đường cong vòng sang trái.",
				"Chỗ ngoặt nguy hiểm có nguy cơ lật xe bên trái khi đường cong vòng bên phải." };
		Answer a2_answer_17_set14 = new Answer(a2_answers_17_set14, 0, idList.get(341));
		answerRepo.save(a2_answer_17_set14);

		String[] a2_answers_18_set14 = { "Biển 1.", "Biển 2.", "Biến 3.", "Cả ba biển." };
		Answer a2_answer_18_set14 = new Answer(a2_answers_18_set14, 0, idList.get(342));
		answerRepo.save(a2_answer_18_set14);

		String[] a2_answers_19_set14 = { "Đỗ xe hoàn toàn trên hè phố.", "Đỗ xe hoàn toàn dưới lòng đường.",
				"Đỗ từ 1/2 thân xe trở lên trên hè phố." };
		Answer a2_answer_19_set14 = new Answer(a2_answers_19_set14, 2, idList.get(343));
		answerRepo.save(a2_answer_19_set14);

		String[] a2_answers_20_set14 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_20_set14 = new Answer(a2_answers_20_set14, 1, idList.get(344));
		answerRepo.save(a2_answer_20_set14);

		String[] a2_answers_21_set14 = { "Xe con.", "Xe tải." };
		Answer a2_answer_21_set14 = new Answer(a2_answers_21_set14, 0, idList.get(345));
		answerRepo.save(a2_answer_21_set14);

		String[] a2_answers_22_set14 = { "Xe con.", "Xe mô tô." };
		Answer a2_answer_22_set14 = new Answer(a2_answers_22_set14, 1, idList.get(346));
		answerRepo.save(a2_answer_22_set14);

		String[] a2_answers_23_set14 = { "Xe con (E), mô tô (c).", "Xe tải (A), mô tô (D).", "Xe khách (B), mô tô (C).",
				"Xe khách (B), mô tô (D)." };
		Answer a2_answer_23_set14 = new Answer(a2_answers_23_set14, 0, idList.get(347));
		answerRepo.save(a2_answer_23_set14);

		String[] a2_answers_24_set14 = { "Xe của bạn, mô tô, xe con.", "Xe con, xe của bạn, mô tô,",
				"Mô tô, xe con, xe của bạn." };
		Answer a2_answer_24_set14 = new Answer(a2_answers_24_set14, 2, idList.get(348));
		answerRepo.save(a2_answer_24_set14);

		String[] a2_answers_25_set14 = { "Xe con.", "Xe mô tô.", "Cả 2 xe đều đúng." };
		Answer a2_answer_25_set14 = new Answer(a2_answers_25_set14, 0, idList.get(349));
		answerRepo.save(a2_answer_25_set14);

		String[] a2_answers_1_set15 = {
				"Là trạng thái đứng yên của phương tiện giao thông có giới hạn trong một khoảng thời gian cần thiết đủ để cho người lên, xuống phương tiện đó, xếp dỡ hàng hóa hoặc thực hiện công việc khác.",
				"Là trạng thái đứng yên của phương tiện giao thông không giới hạn thời gian." };
		Answer a2_answer_1_set15 = new Answer(a2_answers_1_set15, 1, idList.get(350));
		answerRepo.save(a2_answer_1_set15);

		String[] a2_answers_2_set15 = { "Được phép.", "Không được phép.", "Được phép tùy từng trường hợp." };
		Answer a2_answer_2_set15 = new Answer(a2_answers_2_set15, 1, idList.get(351));
		answerRepo.save(a2_answer_2_set15);

		String[] a2_answers_3_set15 = { "Xe mô tô có dung tích xi-lanh 125 cm3.",
				"Xe mô tô có dung tích xi-lanh từ 175 cm3 trở lên.", "Xe mô tô có dung tích xi-lanh 100 cm3." };
		Answer a2_answer_3_set15 = new Answer(a2_answers_3_set15, 1, idList.get(352));
		answerRepo.save(a2_answer_3_set15);

		String[] a2_answers_4_set15 = { "Biển báo hiệu cố định.", "Báo hiệu tạm thời." };
		Answer a2_answer_4_set15 = new Answer(a2_answers_4_set15, 1, idList.get(353));
		answerRepo.save(a2_answer_4_set15);

		String[] a2_answers_5_set15 = { "Dùng dây cáp có độ dài 10 mét.", "Dùng dây cáp có độ dài 5 mét.",
				"Dùng thanh nối cứng." };
		Answer a2_answer_5_set15 = new Answer(a2_answers_5_set15, 2, idList.get(354));
		answerRepo.save(a2_answer_5_set15);

		String[] a2_answers_6_set15 = { "Ô tô con, ô tô tải, ô tô chở người trên 30 chỗ.",
				"Xe gắn máy, xe máy chuyên dùng.", "Cả ý 1 và ý 2." };
		Answer a2_answer_6_set15 = new Answer(a2_answers_6_set15, 0, idList.get(355));
		answerRepo.save(a2_answer_6_set15);

		String[] a2_answers_7_set15 = { "Để làm đẹp.", "Để tránh mưa nắng.", "Để giảm thiểu chấn thương vùng đầu.",
				"Đế các loại phương tiện khác dễ quan sát." };
		Answer a2_answer_7_set15 = new Answer(a2_answers_7_set15, 2, idList.get(356));
		answerRepo.save(a2_answer_7_set15);

		String[] a2_answers_8_set15 = {
				"Thực hiện nghiêm chỉnh những nội dung hợp đồng giữa chủ phương tiện với chủ hàng trong việc vận chuyển và bảo quản hàng hóa trong quá trình vận chuyển; không chở hàng cấm, không xếp hàng quá trọng tải của xe, quá trọng tải cho phép của cầu, đường, khi vận chuyển hàng quá khổ, quá tải, hàng nguy hiểm, hàng siêu trường, siêu trọng phải có giấy phép.",
				"Thực hiện nghiêm chỉnh những nội dung hợp đồng giữa chủ hàng với khách hàng trong việc vận chuyển và bảo quản hàng hóa trong quá trình vận chuyển, trong trường hợp cần thiết có thể xếp hàng quá trọng tải của xe, quá trọng tải cho phép của cầu theo yêu cầu của chủ hàng; khi vận chuyển hàng quá khổ, quá tải, hàng nguy hiểm, hàng siêu trường, siêu trọng phải được chủ hàng cho phép." };
		Answer a2_answer_8_set15 = new Answer(a2_answers_8_set15, 0, idList.get(357));
		answerRepo.save(a2_answer_8_set15);

		String[] a2_answers_9_set15 = { "Cho xe lấn sang làn ngược chiều để nhanh chóng thoát khỏi nơi ùn tắc.",
				"Điều khiển xe lên vỉa hè để nhanh chóng thoát khỏi nơi ùn tắc.",
				"Kiên nhẫn tuân thủ hướng dẫn của người điều khiển giao thông hoặc tín hiệu giao thông, di chuyển trên đúng phần đường bên phải theo chiều đi, nhường đường cho các phương tiện đi ngược chiều để nút tắc nhanh chóng được giải tỏa." };
		Answer a2_answer_9_set15 = new Answer(a2_answers_9_set15, 2, idList.get(358));
		answerRepo.save(a2_answer_9_set15);

		String[] a2_answers_10_set15 = { "Tăng ga thật nhanh, giảm ga từ từ.",
				"Tăng ga thật nhanh, giảm ga thật nhanh.", "Tăng ga từ từ, giảm ga thật nhanh.",
				"Tăng ga từ từ, giảm ga từ từ." };
		Answer a2_answer_10_set15 = new Answer(a2_answers_10_set15, 2, idList.get(359));
		answerRepo.save(a2_answer_10_set15);

		String[] a2_answers_11_set15 = { "Biển 1.", "Biển 1 và 2.", "Biển 1 và 3.", "Biển 2 và 3." };
		Answer a2_answer_11_set15 = new Answer(a2_answers_11_set15, 3, idList.get(360));
		answerRepo.save(a2_answer_11_set15);

		String[] a2_answers_12_set15 = { "Biển 1 và 2.", "Biển 2.", "Biển 2 và 3.", "Biến 3." };
		Answer a2_answer_12_set15 = new Answer(a2_answers_12_set15, 0, idList.get(361));
		answerRepo.save(a2_answer_12_set15);

		String[] a2_answers_13_set15 = { "Cấm xe cơ giới (trừ xe ưu tiên theo luật định) đi thẳng.",
				"Cấm xe ô tô và mô tô (trừ xe ưu tiên theo luật định) đi về bên trái và bên phải.",
				"Hướng trái và phải không cấm xe cơ giới." };
		Answer a2_answer_13_set15 = new Answer(a2_answers_13_set15, 1, idList.get(362));
		answerRepo.save(a2_answer_13_set15);

		String[] a2_answers_14_set15 = { "Tốc độ tối đa các xe cơ giới được phép chạy.",
				"Tốc độ tối thiểu các xe cơ giới được phép chạy." };
		Answer a2_answer_14_set15 = new Answer(a2_answers_14_set15, 0, idList.get(363));
		answerRepo.save(a2_answer_14_set15);

		String[] a2_answers_15_set15 = { "Biển 1.", "Biển 2.", "Biển 3.", "Biến 2 và 3." };
		Answer a2_answer_15_set15 = new Answer(a2_answers_15_set15, 0, idList.get(364));
		answerRepo.save(a2_answer_15_set15);

		String[] a2_answers_16_set15 = { "Biển 1.", "Biển 2.", "Cả hai biển." };
		Answer a2_answer_16_set15 = new Answer(a2_answers_16_set15, 1, idList.get(365));
		answerRepo.save(a2_answer_16_set15);

		String[] a2_answers_17_set15 = { "Báo trước đoạn đường có gió ngang.", "Báo trước đoạn đường trơn trượt.",
				"Báo trước sắp đến bến phà." };
		Answer a2_answer_17_set15 = new Answer(a2_answers_17_set15, 2, idList.get(366));
		answerRepo.save(a2_answer_17_set15);

		String[] a2_answers_18_set15 = { "Biển 1.", "Biển 2.", "Biển 3.", "Biên 1 và 2." };
		Answer a2_answer_18_set15 = new Answer(a2_answers_18_set15, 3, idList.get(367));
		answerRepo.save(a2_answer_18_set15);

		String[] a2_answers_19_set15 = { "Dừng xe tại khu vực có trạm Cảnh sát giao thông.",
				"Tiếp tục lưu thông với tốc độ bình thường.",
				"Phải giảm tốc độ đến mức an toàn và không được vượt khi đi qua khu vực này." };
		Answer a2_answer_19_set15 = new Answer(a2_answers_19_set15, 2, idList.get(368));
		answerRepo.save(a2_answer_19_set15);

		String[] a2_answers_20_set15 = { "Biển 1 và 2.", "Biển 1 và 3.", "Biển 2.", "Biến 3." };
		Answer a2_answer_20_set15 = new Answer(a2_answers_20_set15, 1, idList.get(369));
		answerRepo.save(a2_answer_20_set15);

		String[] a2_answers_21_set15 = { "Xe công an.", "Xe chữa cháy." };
		Answer a2_answer_21_set15 = new Answer(a2_answers_21_set15, 1, idList.get(370));
		answerRepo.save(a2_answer_21_set15);

		String[] a2_answers_22_set15 = { "Xe con (A), mô tô, xe con (B), xe đạp.",
				"Xe con (B), xe đạp, mô tô, xe con (A).", "Xe con (A), xe con (B), mô tô + xe đạp.",
				"Mô tô + xe đạp, xe con (A), xe con (B)." };
		Answer a2_answer_22_set15 = new Answer(a2_answers_22_set15, 3, idList.get(371));
		answerRepo.save(a2_answer_22_set15);

		String[] a2_answers_23_set15 = { "Nhường xe con rẽ trái trước.", "Đi thẳng không nhường." };
		Answer a2_answer_23_set15 = new Answer(a2_answers_23_set15, 0, idList.get(372));
		answerRepo.save(a2_answer_23_set15);

		String[] a2_answers_24_set15 = { "Xe của bạn, mô tô, xe con.", "Xe con, xe của bạn, mô tô.",
				"Mô tô, xe con, xe của bạn." };
		Answer a2_answer_24_set15 = new Answer(a2_answers_24_set15, 1, idList.get(373));
		answerRepo.save(a2_answer_24_set15);

		String[] a2_answers_25_set15 = { "Xe tải, xe đạp, xe của bạn.", "Xe của bạn, xe đạp, xe tải.",
				"Xe của bạn, xe tải, xe đạp." };
		Answer a2_answer_25_set15 = new Answer(a2_answers_25_set15, 2, idList.get(374));
		answerRepo.save(a2_answer_25_set15);

		String[] a2_answers_1_set16 = {
				"Đường dành riêng cho xe ô tô và một số loại xe chuyên dùng được phép đi vào theo quy định của Luật Giao thông đường bộ.",
				"Có dải phân cách phân chia đường cho xe chạy hai chiều riêng biệt mà dải phân cách này xe không đi được lên trên; không giao nhau cùng mức với một hoặc một số đường khác.",
				"Được bố trí đầy đủ trang thiết bị phục vụ, bảo đảm giao thông liên tục, an toàn, rút ngắn thời gian hành trình và chỉ cho xe ra, vào ở những điểm nhất định.",
				"Tất cả các ý trên." };
		Answer a2_answer_1_set16 = new Answer(a2_answers_1_set16, 3, idList.get(375));
		answerRepo.save(a2_answer_1_set16);

		String[] a2_answers_2_set16 = { "Phải đảm bảo phụ tùng do đúng nhà sản xuất đó cung cấp.",
				"Phải được chấp thuận của cơ quan có thẩm quyền.",
				"Phải là xe đăng ký và hoạt động tại các khu vực có địa hình phức tạp." };
		Answer a2_answer_2_set16 = new Answer(a2_answers_2_set16, 1, idList.get(376));
		answerRepo.save(a2_answer_2_set16);

		String[] a2_answers_3_set16 = { "Chỉ được kéo nếu đã nhìn thấy trạm xăng.",
				"Chỉ được thực hiện trên đường vắng phương tiện cùng tham gia giao thông.", "Không được phép." };
		Answer a2_answer_3_set16 = new Answer(a2_answers_3_set16, 2, idList.get(377));
		answerRepo.save(a2_answer_3_set16);

		String[] a2_answers_4_set16 = {
				"Cho xe đi trên bất kỳ làn đường nào hoặc giữa 02 làn đường nếu không có xe phía trước, khi cần thiết phải chuyển làn đường, người lái xe phải quan sát xe phía trước để bảo đảm an toàn.",
				"Phải cho xe đi trong một làn đường và chỉ được chuyển làn đường ở những nơi cho phép; khi chuyển làn phải có tín hiệu báo trước và phải bảo đảm an toàn.",
				"Phải cho xe đi trong một làn đường, khi cần thiết phải chuyến làn đường, người lái xe phải quan sát xe phía trước để bảo đảm an toàn." };
		Answer a2_answer_4_set16 = new Answer(a2_answers_4_set16, 1, idList.get(378));
		answerRepo.save(a2_answer_4_set16);

		String[] a2_answers_5_set16 = { "Khi tham gia giao thông đường bộ.",
				"Chỉ khi đi trên đường chuyên dùng, đường cao tốc.",
				"Khi tham gia giao thông trên đường tỉnh lộ hoặc quốc lộ." };
		Answer a2_answer_5_set16 = new Answer(a2_answers_5_set16, 0, idList.get(379));
		answerRepo.save(a2_answer_5_set16);

		String[] a2_answers_6_set16 = {
				"Ô tô chở người trên 30 chỗ (trừ ô tô buýt), ô tô tải có trọng tải trên 3,5 tấn.",
				"Xe ô tô con, xe ô tô chở người đến 30 chỗ (trừ ô tô buýt), ô tô tải có trọng tải đến 3,5 tấn.",
				"Ô tô buýt, ô tô đầu kéo kéo sơ mi rơ moóc, ô tô chuyên  dùng, xe mô tô." };
		Answer a2_answer_6_set16 = new Answer(a2_answers_6_set16, 1, idList.get(380));
		answerRepo.save(a2_answer_6_set16);

		String[] a2_answers_7_set16 = { "Thay đổi tốc độ của xe trên đường bộ.",
				"Thay đổi tay số của xe trên đường bộ.", "Lạng lách, đánh võng trên đường bộ." };
		Answer a2_answer_7_set16 = new Answer(a2_answers_7_set16, 2, idList.get(381));
		answerRepo.save(a2_answer_7_set16);

		String[] a2_answers_8_set16 = {
				"Phải yêu quý xe, quản lý và sử dụng xe tốt; bảo dưỡng xe đúng định kỳ; thực hành tiết kiệm vật tư, nhiên liệu; luôn | tu dưỡng bản thân, có lối sống lành mạnh, tác phong làm việc công nghiệp.",
				"Nắm vững các quy định của pháp luật, tự giác chấp hành pháp luật, lái xe an toàn; coi hành khách như người thân, là đối tác tin cậy, có ý thức tổ chức kỷ luật và xây dựng doanh nghiệp vững mạnh, có tinh thần hợp tác, tương trợ, giúp đỡ đồng nghiệp.",
				"Cả ý 1 và ý 2." };
		Answer a2_answer_8_set16 = new Answer(a2_answers_8_set16, 2, idList.get(382));
		answerRepo.save(a2_answer_8_set16);

		String[] a2_answers_9_set16 = { "Bấm còi liên tục thúc giục các phương tiện phía trước nhường đường.",
				"Đi lên vỉa hè, tận dụng mọi khoảng trống để nhanh chóng thoát khỏi nơi ùn tắc.",
				"Lấn sang trái đường cố gắng vượt lên xe khác.", "Tất cả các ý nêu trên." };
		Answer a2_answer_9_set16 = new Answer(a2_answers_9_set16, 3, idList.get(383));
		answerRepo.save(a2_answer_9_set16);

		String[] a2_answers_10_set16 = {
				"Đứng thẳng trên giá gác chân lái sau đó hơi gập đầu gối và khuỷu tay, đi chậm để không nẩy quá mạnh.",
				"Ngồi lùi lại phía sau, tăng ga vượt nhanh qua đoạn đường xóc.",
				"Ngồi lệch sang bên trái hoặc bên phải để lấy thăng bằng qua đoạn đường gồ ghề." };
		Answer a2_answer_10_set16 = new Answer(a2_answers_10_set16, 0, idList.get(384));
		answerRepo.save(a2_answer_10_set16);

		String[] a2_answers_11_set16 = { "Biển 1.", "Biển 2.", "Cả hai biển." };
		Answer a2_answer_11_set16 = new Answer(a2_answers_11_set16, 2, idList.get(385));
		answerRepo.save(a2_answer_11_set16);

		String[] a2_answers_12_set16 = { "Biển 1.", "Biển 2.", "Cả ba biển." };
		Answer a2_answer_12_set16 = new Answer(a2_answers_12_set16, 1, idList.get(386));
		answerRepo.save(a2_answer_12_set16);

		String[] a2_answers_13_set16 = { "Báo khoảng cách đến nơi cấm bóp còi.",
				"Chiều dài đoạn đường cấm bóp còi từ nơi đặt biển.", "Báo cấm dùng còi có độ vang xa 500 m." };
		Answer a2_answer_13_set16 = new Answer(a2_answers_13_set16, 1, idList.get(387));
		answerRepo.save(a2_answer_13_set16);

		String[] a2_answers_14_set16 = { "Biển 1.", "Biển 2.", "Cả biển 1 và biển 2." };
		Answer a2_answer_14_set16 = new Answer(a2_answers_14_set16, 1, idList.get(388));
		answerRepo.save(a2_answer_14_set16);

		String[] a2_answers_15_set16 = { "Biển 1 và 3.", "Biển 2.", "Biến 3." };
		Answer a2_answer_15_set16 = new Answer(a2_answers_15_set16, 1, idList.get(389));
		answerRepo.save(a2_answer_15_set16);

		String[] a2_answers_16_set16 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_16_set16 = new Answer(a2_answers_16_set16, 1, idList.get(390));
		answerRepo.save(a2_answer_16_set16);

		String[] a2_answers_17_set16 = { "Báo hiệu đường có ổ gà, lồi lõm.",
				"Báo hiệu đường có gồ giảm tốc phía trước." };
		Answer a2_answer_17_set16 = new Answer(a2_answers_17_set16, 0, idList.get(391));
		answerRepo.save(a2_answer_17_set16);

		String[] a2_answers_18_set16 = { "Biển 1.", "Biển 2.", "Biến 3.", "Cả ba biển." };
		Answer a2_answer_18_set16 = new Answer(a2_answers_18_set16, 2, idList.get(392));
		answerRepo.save(a2_answer_18_set16);

		String[] a2_answers_19_set16 = { "Bắt buộc.", "Không bắt buộc." };
		Answer a2_answer_19_set16 = new Answer(a2_answers_19_set16, 0, idList.get(393));
		answerRepo.save(a2_answer_19_set16);

		String[] a2_answers_20_set16 = { "Vạch 1.", "Vạch 2 và 3.", "Vạch 3.", "Vạch 1 và 2." };
		Answer a2_answer_20_set16 = new Answer(a2_answers_20_set16, 2, idList.get(394));
		answerRepo.save(a2_answer_20_set16);

		String[] a2_answers_21_set16 = { "Xe con và xe khách.", "Mô tô." };
		Answer a2_answer_21_set16 = new Answer(a2_answers_21_set16, 0, idList.get(395));
		answerRepo.save(a2_answer_21_set16);

		String[] a2_answers_22_set16 = { "Mô tô.", "Xe con." };
		Answer a2_answer_22_set16 = new Answer(a2_answers_22_set16, 0, idList.get(396));
		answerRepo.save(a2_answer_22_set16);

		String[] a2_answers_23_set16 = { "Chỉ hướng 2.", "Hướng 1 và 2.", "Tất cả các hướng trừ hướng 3.",
				"Tất cả các hướng trừ hướng 4." };
		Answer a2_answer_23_set16 = new Answer(a2_answers_23_set16, 1, idList.get(397));
		answerRepo.save(a2_answer_23_set16);

		String[] a2_answers_24_set16 = { "Xe con.", "Xe của bạn.", "Cả hai xe." };
		Answer a2_answer_24_set16 = new Answer(a2_answers_24_set16, 0, idList.get(398));
		answerRepo.save(a2_answer_24_set16);

		String[] a2_answers_25_set16 = { "Hướng 1.", "Hướng 2.", "Cả 02 hướng đều được." };
		Answer a2_answer_25_set16 = new Answer(a2_answers_25_set16, 0, idList.get(399));
		answerRepo.save(a2_answer_25_set16);

		String[] a2_answers_1_set17 = { "Đỗ xe trên đường phố.",
				"Sử dụng xe đạp đi trên các tuyến quốc lộ có tốc độ cao.",
				"Làm hỏng (cố ý) cọc tiêu, gương cầu, dải phân cách.", "Sử dụng còi và quay đầu xe trong khu dân cư." };
		Answer a2_answer_1_set17 = new Answer(a2_answers_1_set17, 2, idList.get(400));
		answerRepo.save(a2_answer_1_set17);

		String[] a2_answers_2_set17 = { "Được phép sản xuất, sử dụng khi bị mất biển số.",
				"Được phép mua bán, sử dụng khi bị mất biển số.", "Nghiêm cấm sản xuất, mua bán, sử dụng trái phép." };
		Answer a2_answer_2_set17 = new Answer(a2_answers_2_set17, 2, idList.get(401));
		answerRepo.save(a2_answer_2_set17);

		String[] a2_answers_3_set17 = { "Không được vận chuyển.", "Chỉ được vận chuyển khi đã chằng buộc cẩn thận.",
				"Chỉ được vận chuyển vật cồng kềnh trên xe máy nếu khoảng cách về nhà ngắn hơn 2 km." };
		Answer a2_answer_3_set17 = new Answer(a2_answers_3_set17, 0, idList.get(402));
		answerRepo.save(a2_answer_3_set17);

		String[] a2_answers_4_set17 = {
				"Xe thô sơ phải đi trên làn đường bên trái ngoài cùng, xe cơ giới, xe máy chuyên dùng đi trên làn đường bên phải.",
				"Xe thô sơ phải đi trên làn đường bên phải trong cùng; xe cơ giới, xe máy chuyên dùng đi trên làn đường bên trái.",
				"Xe thô sơ đi trên làn đường phù hợp không gây cản trở giao thông, xe cơ giới, xe máy chuyên dùng đi trên làn đường bên phải." };
		Answer a2_answer_4_set17 = new Answer(a2_answers_4_set17, 1, idList.get(403));
		answerRepo.save(a2_answer_4_set17);

		String[] a2_answers_5_set17 = { "Chở người bệnh đi cấp cứu; trẻ em dưới 14 tuổi.",
				"Áp giải người có hành vi vi phạm pháp luật.", "Cả ý 1 và ý 2." };
		Answer a2_answer_5_set17 = new Answer(a2_answers_5_set17, 2, idList.get(404));
		answerRepo.save(a2_answer_5_set17);

		String[] a2_answers_6_set17 = { "Ô tô buýt; ô tô đầu kéo kéo sơ mi rơ moóc; ô tô chuyên dùng; xe mô tô.",
				"Ô tô chở người trên 30 chỗ (trừ ô tô buýt), ô tô tải có trọng tải trên 3,5 tấn (trừ ô tô xi téc).",
				"Ô tô kéo rơ moóc, ô tô kéo xe khác, xe gắn máy.",
				"Xe ô tô con, xe ô tô chở người đến 30 chỗ (trừ ô tô buýt), ô tô tải có trọng tải đến 3,5 tấn." };
		Answer a2_answer_6_set17 = new Answer(a2_answers_6_set17, 1, idList.get(405));
		answerRepo.save(a2_answer_6_set17);

		String[] a2_answers_7_set17 = {
				"Xe chữa cháy, xe quân sự, xe công an, xe cứu thương, xe hộ để sau khi thực hiện nhiệm vụ khẩn cấp, không có tín hiệu còi, cờ, đèn theo quy định của pháp luật.",
				"Xe chữa cháy, xe quân sự, xe công an, xe cứu thương, xe hộ để đi làm nhiệm vụ khẩn cấp có tín hiệu còi, cờ, đèn theo quy định của pháp luật.",
				"Xe ô tô, xe máy, đoàn xe đang diễu hành có tổ chức có báo tín hiệu xin vượt bằng còi và đèn." };
		Answer a2_answer_7_set17 = new Answer(a2_answers_7_set17, 1, idList.get(406));
		answerRepo.save(a2_answer_7_set17);

		String[] a2_answers_8_set17 = {
				"Kiểm tra các điều kiện bảo đảm an toàn của xe sau khi khởi hành; có trách nhiệm lái xe thật nhanh khi chậm giờ của khách.",
				"Kiểm tra các điều kiện bảo đảm an toàn của xe trước khi khởi hành; có thái độ văn minh, lịch sự, hướng dẫn hành khách ngồi đúng nơi quy định; kiểm tra việc sắp xếp, chẳng buộc hành lý, bảo đảm an toàn.",
				"Có biện pháp bảo vệ tính mạng, sức khỏe, tài sản của | hành khách đi xe, giữ gìn trật tự, vệ sinh trong xe; đóng cửa lên xuống của xe trước và trong khi xe chạy.",
				"Cả ý 2 và ý 3." };
		Answer a2_answer_8_set17 = new Answer(a2_answers_8_set17, 3, idList.get(407));
		answerRepo.save(a2_answer_8_set17);

		String[] a2_answers_9_set17 = { "Giữ tay ga ở mức độ phù hợp, sử dụng phanh trước và phanh sau để giảm tốc độ.",
				"Nhả hết tay ga, tắt động cơ, sử dụng phanh trước và phanh sau để giảm tốc độ.",
				"Sử dụng phanh trước để giảm tốc độ kết hợp với tắt chìa khóa điện của xe." };
		Answer a2_answer_9_set17 = new Answer(a2_answers_9_set17, 0, idList.get(408));
		answerRepo.save(a2_answer_9_set17);

		String[] a2_answers_10_set17 = { "Được phép thay đổi bằng cách dán đề can với màu sắc phù hợp.",
				"Không được phép thay đổi.", "Tùy từng loại phương tiện cơ giới đường bộ." };
		Answer a2_answer_10_set17 = new Answer(a2_answers_10_set17, 1, idList.get(409));
		answerRepo.save(a2_answer_10_set17);

		String[] a2_answers_11_set17 = { "Biển 1.", "Biển 2." };
		Answer a2_answer_11_set17 = new Answer(a2_answers_11_set17, 1, idList.get(410));
		answerRepo.save(a2_answer_11_set17);

		String[] a2_answers_12_set17 = { "Biển 1.", "Biển 2.", "Cả hai biển." };
		Answer a2_answer_12_set17 = new Answer(a2_answers_12_set17, 0, idList.get(411));
		answerRepo.save(a2_answer_12_set17);

		String[] a2_answers_13_set17 = { "Được phép.", "Không được phép." };
		Answer a2_answer_13_set17 = new Answer(a2_answers_13_set17, 1, idList.get(412));
		answerRepo.save(a2_answer_13_set17);

		String[] a2_answers_14_set17 = { "Báo hiệu tốc độ tối đa cho phép các xe cơ giới chạy.",
				"Báo hiệu tốc độ tối thiểu cho phép các xe cơ giới chạy." };
		Answer a2_answer_14_set17 = new Answer(a2_answers_14_set17, 1, idList.get(413));
		answerRepo.save(a2_answer_14_set17);

		String[] a2_answers_15_set17 = { "Biển 1 và 2.", "Biển 1 và 3.", "Biến 2 và 3.", "Cả ba biến." };
		Answer a2_answer_15_set17 = new Answer(a2_answers_15_set17, 0, idList.get(414));
		answerRepo.save(a2_answer_15_set17);

		String[] a2_answers_16_set17 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_16_set17 = new Answer(a2_answers_16_set17, 2, idList.get(415));
		answerRepo.save(a2_answer_16_set17);

		String[] a2_answers_17_set17 = { "Báo hiệu đường có ổ gà, lồi lõm.",
				"Báo hiệu đường có gờ giảm tốc phía trước." };
		Answer a2_answer_17_set17 = new Answer(a2_answers_17_set17, 1, idList.get(416));
		answerRepo.save(a2_answer_17_set17);

		String[] a2_answers_18_set17 = { "Biển 1.", "Biển 2.", "Biển 3.", "Biển 2 và 3." };
		Answer a2_answer_18_set17 = new Answer(a2_answers_18_set17, 3, idList.get(417));
		answerRepo.save(a2_answer_18_set17);

		String[] a2_answers_19_set17 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_19_set17 = new Answer(a2_answers_19_set17, 1, idList.get(418));
		answerRepo.save(a2_answer_19_set17);

		String[] a2_answers_20_set17 = { "Vạch 1.", "Vạch 2.", "Vạch 3.", "Vạch 1 và 2." };
		Answer a2_answer_20_set17 = new Answer(a2_answers_20_set17, 3, idList.get(419));
		answerRepo.save(a2_answer_20_set17);

		String[] a2_answers_21_set17 = { "Xe khách, mô tô.", "Xe con, xe tải.", "Xe tải, mô tô." };
		Answer a2_answer_21_set17 = new Answer(a2_answers_21_set17, 1, idList.get(420));
		answerRepo.save(a2_answer_21_set17);

		String[] a2_answers_22_set17 = { "Không vi phạm.", "Vi phạm." };
		Answer a2_answer_22_set17 = new Answer(a2_answers_22_set17, 1, idList.get(421));
		answerRepo.save(a2_answer_22_set17);

		String[] a2_answers_23_set17 = { "Rẽ trái ngay trước xe buýt.", "Rẽ trái trước xe tải.",
				"Nhường đường cho xe buýt và xe tải." };
		Answer a2_answer_23_set17 = new Answer(a2_answers_23_set17, 2, idList.get(422));
		answerRepo.save(a2_answer_23_set17);

		String[] a2_answers_24_set17 = { "Đi thẳng, rẽ trái.", "Đi thẳng, rẽ phải.", "Rẽ trái.",
				"Đi thẳng, rẽ phải, rẽ trái." };
		Answer a2_answer_24_set17 = new Answer(a2_answers_24_set17, 0, idList.get(423));
		answerRepo.save(a2_answer_24_set17);

		String[] a2_answers_25_set17 = { "Quan sát nếu thấy không có tầu thì tăng tốc cho xe vượt qua đường sắt.",
				"Dừng lại trước rào chắn một khoảng cách an toàn.",
				"Ra tín hiệu, yêu cầu người gác chắn tàu kéo chậm Barie để xe bạn qua." };
		Answer a2_answer_25_set17 = new Answer(a2_answers_25_set17, 1, idList.get(424));
		answerRepo.save(a2_answer_25_set17);

		String[] a2_answers_1_set18 = { "Không nghiêm cấm.", "Bị nghiêm cấm.",
				"Bị nghiêm cấm tuỳ theo các tuyến đường.", "Bị nghiêm cấm tuỳ theo loại xe." };
		Answer a2_answer_1_set18 = new Answer(a2_answers_1_set18, 2, idList.get(425));
		answerRepo.save(a2_answer_1_set18);

		String[] a2_answers_2_set18 = {
				"Trên cầu hẹp có một làn xe. Nơi đường giao nhau, đường bộ giao nhau cùng mức với đường sắt; xe được quyền ưu tiên đang phát tín hiệu ưu tiên đi làm nhiệm vụ.",
				"Trên cầu có từ 02 làn xe trở lên; nơi đường bộ giao nhau không cùng mức với đường sắt; xe được quyền ưu tiên đang đi phía trước nhưng không phát tín hiệu ưu tiên.",
				"Trên đường có 2 làn đường được phân chia làn bằng vạch kẻ nét đứt." };
		Answer a2_answer_2_set18 = new Answer(a2_answers_2_set18, 0, idList.get(426));
		answerRepo.save(a2_answer_2_set18);

		String[] a2_answers_3_set18 = { "Xe mô tô 2 bánh có dung tích xi-lanh từ 50 cm3 trở lên.",
				"Xe gắn máy có dung tích xi-lanh dưới 50 cm3.",
				"Xe ô tô tải dưới 3.500 kg; xe chở người đến 9 chỗ ngồi.", "Tất cả các ý nêu trên." };
		Answer a2_answer_3_set18 = new Answer(a2_answers_3_set18, 1, idList.get(427));
		answerRepo.save(a2_answer_3_set18);

		String[] a2_answers_4_set18 = { "Phải báo hiệu bằng đèn hoặc còi.", "Chỉ được báo hiệu bằng còi.",
				"Phải báo hiệu bằng cả còi và đèn.", "Chỉ được báo hiệu bằng đèn." };
		Answer a2_answer_4_set18 = new Answer(a2_answers_4_set18, 3, idList.get(428));
		answerRepo.save(a2_answer_4_set18);

		String[] a2_answers_5_set18 = {
				"Đi vào phần đường dành cho người đi bộ và phương tiện khác, sử dụng ô, điện thoại di động, thiết bị âm thanh (trừ thiết bị trợ thính), đi xe dàn hàng ngang.",
				"Chở 02 người, trong đó, có người bệnh đi cấp cứu hoặc trẻ em dưới 14 tuổi hoặc áp giải người có hành vi vi phạm pháp luật.",
				"Điều khiển phương tiện tham gia giao thông trên đường tỉnh lộ hoặc quốc lộ." };
		Answer a2_answer_5_set18 = new Answer(a2_answers_5_set18, 0, idList.get(429));
		answerRepo.save(a2_answer_5_set18);

		String[] a2_answers_6_set18 = {
				"Ô tô buýt; ô tô đầu kéo kéo sơ mi rơ moóc, xe mô tô, ô tô chuyên dùng (trừ ô tô trộn vữa, ô tô trộn bê tông).",
				"Ô tô kéo rơ moóc, ô tô kéo xe khác, xe gắn máy.",
				"Ô tô chở người trên 30 chỗ (trừ ô tô buýt), ô tô tải có trọng  tải trên 3,5 tấn.",
				"Xe ô tô con, xe ô tô chở người đến 30 chỗ (trừ ô tô buýt), ô tô tải có trọng tải đến 3,5 tấn." };
		Answer a2_answer_6_set18 = new Answer(a2_answers_6_set18, 0, idList.get(430));
		answerRepo.save(a2_answer_6_set18);

		String[] a2_answers_7_set18 = { "Tăng tốc độ qua đường giao nhau để đi trước xe đi trên đường ưu tiên.",
				"Giảm tốc độ qua đường giao nhau để đi trước xe đi trên đường ưu tiên.",
				"Nhường đường cho xe đi trên đường ưu tiên từ bất kỳ hướng nào tới." };
		Answer a2_answer_7_set18 = new Answer(a2_answers_7_set18, 2, idList.get(431));
		answerRepo.save(a2_answer_7_set18);

		String[] a2_answers_8_set18 = {
				"Là sự hiểu biết và chấp hành nghiêm chỉnh pháp luật về giao thông; là ý thức trách nhiệm với cộng đồng khi tham gia giao thông.",
				"Là ứng xử có văn hóa, có tình yêu thương con người trong các tình huống không may xảy ra khi tham gia giao thông.",
				"Cả ý 1 và ý 2." };
		Answer a2_answer_8_set18 = new Answer(a2_answers_8_set18, 2, idList.get(432));
		answerRepo.save(a2_answer_8_set18);

		String[] a2_answers_9_set18 = {
				"Quan sát biển báo hiệu để biết nơi được phép quay đầu;quan sát kỹ địa hình nơi chọn để quay đầu, lựa chọn quỹ đạo quay đầu xe cho thích hợp; quay đầu xe với tốc độ thấp; thường xuyên báo tín hiệu để người, các phương tiện xung quanh được biết, nếu quay đầu xe ở nơi nguy hiểm thì đưa đầu xe về phía nguy hiểm đưa đuôi xe về phía an toàn.",
				"Quan sát biển báo hiệu để biết nơi được phép quay đầu;quan sát kỹ địa hình nơi chọn để quay đầu, lựa chọn quỹ đạo quay đầu xe, quay đầu xe với tốc độ tối đa, thường xuyên báo tín hiệu đến người, các phương tiện xung quanh được biết, nếu quay đầu xe ở nơi nguy hiểm thì đưa đuôi xe về phía nguy hiểm và đầu xe về phía an toàn." };
		Answer a2_answer_9_set18 = new Answer(a2_answers_9_set18, 0, idList.get(433));
		answerRepo.save(a2_answer_9_set18);

		String[] a2_answers_10_set18 = { "Không bắt buộc.", "Bắt buộc.", "Tùy từng trường hợp." };
		Answer a2_answer_10_set18 = new Answer(a2_answers_10_set18, 1, idList.get(434));
		answerRepo.save(a2_answer_10_set18);

		String[] a2_answers_11_set18 = { "Biển 1.", "Biển 2.", "Không biến nào.", "Cả hai biển." };
		Answer a2_answer_11_set18 = new Answer(a2_answers_11_set18, 1, idList.get(435));
		answerRepo.save(a2_answer_11_set18);

		String[] a2_answers_12_set18 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_12_set18 = new Answer(a2_answers_12_set18, 2, idList.get(436));
		answerRepo.save(a2_answer_12_set18);

		String[] a2_answers_13_set18 = { "Biển 1 và 2.", "Biển 1 và 3.", "Biển 2 và 3." };
		Answer a2_answer_13_set18 = new Answer(a2_answers_13_set18, 1, idList.get(437));
		answerRepo.save(a2_answer_13_set18);

		String[] a2_answers_14_set18 = { "Biển 1.", "Biển 2.", "Biến 3.", "Biển 1 và 3." };
		Answer a2_answer_14_set18 = new Answer(a2_answers_14_set18, 0, idList.get(438));
		answerRepo.save(a2_answer_14_set18);

		String[] a2_answers_15_set18 = { "Biển 1.", "Biển 1 và 3.", "Biển 2 và 3.", "Cả ba biển." };
		Answer a2_answer_15_set18 = new Answer(a2_answers_15_set18, 2, idList.get(439));
		answerRepo.save(a2_answer_15_set18);

		String[] a2_answers_16_set18 = { "Biển 1.", "Biển 2.", "Biển 3." };
		Answer a2_answer_16_set18 = new Answer(a2_answers_16_set18, 0, idList.get(440));
		answerRepo.save(a2_answer_16_set18);

		String[] a2_answers_17_set18 = {
				"Để báo trước gần tới đoạn đường có hiện tượng đất đá từ trên ta luy dương sụt lở bất ngờ gây nguy hiểm cho xe cộ và người đi đường.",
				"Để báo trước nơi có kết cấu mặt đường rời rạc, khi phương tiện đi qua, làm cho các viên đá, sỏi văng lên gây nguy hiểm và mất an toàn cho người và phương tiện tham gia giao thông.",
				"Đế cảnh báo những đoạn nền đường yêu, đoạn đường đang theo dõi lún mà việc vận hành xe ở tốc độ cao có thể gây nguy hiểm." };
		Answer a2_answer_17_set18 = new Answer(a2_answers_17_set18, 2, idList.get(441));
		answerRepo.save(a2_answer_17_set18);

		String[] a2_answers_18_set18 = { "Biển 1.", "Biển 2.", "Biển 3.", "Cả ba biển." };
		Answer a2_answer_18_set18 = new Answer(a2_answers_18_set18, 1, idList.get(442));
		answerRepo.save(a2_answer_18_set18);

		String[] a2_answers_19_set18 = { "Biển 2 và 3.", "Biển 1 và 2.", "Biến 1 và 3.", "Cả ba biển." };
		Answer a2_answer_19_set18 = new Answer(a2_answers_19_set18, 2, idList.get(443));
		answerRepo.save(a2_answer_19_set18);

		String[] a2_answers_20_set18 = { "Vạch 1.", "Vạch 2.", "Vạch 3.", "Cả 3 vạch." };
		Answer a2_answer_20_set18 = new Answer(a2_answers_20_set18, 1, idList.get(444));
		answerRepo.save(a2_answer_20_set18);

		String[] a2_answers_21_set18 = { "Xe công an.", "Xe quân sự." };
		Answer a2_answer_21_set18 = new Answer(a2_answers_21_set18, 1, idList.get(445));
		answerRepo.save(a2_answer_21_set18);

		String[] a2_answers_22_set18 = { "Xe khách.", "Mô tô.", "Xe con.", "Xe con và mô tô." };
		Answer a2_answer_22_set18 = new Answer(a2_answers_22_set18, 2, idList.get(446));
		answerRepo.save(a2_answer_22_set18);

		String[] a2_answers_23_set18 = { "Xe con (B), mô tô (C).", "Xe con (A), mô tô (C).", "Xe con (E), mô tô (D).",
				"Tất cả các loại xe trên." };
		Answer a2_answer_23_set18 = new Answer(a2_answers_23_set18, 2, idList.get(447));
		answerRepo.save(a2_answer_23_set18);

		String[] a2_answers_24_set18 = { "Chuyển sang làn đường bên phải và rẽ phải.",
				"Dừng lại trước vạch dừng và rẽ phải khi đèn xanh.",
				"Dừng lại trước vạch dừng và đi thẳng hoặc rẽ trái khi đèn xanh." };
		Answer a2_answer_24_set18 = new Answer(a2_answers_24_set18, 2, idList.get(448));
		answerRepo.save(a2_answer_24_set18);

		String[] a2_answers_25_set18 = { "Vượt về phía bên phải để đi tiếp.",
				"Giảm tốc độ chờ xe container rẽ xong rồi tiếp tục đi. ", "Vượt về phía bên trái để đi tiếp." };
		Answer a2_answer_25_set18 = new Answer(a2_answers_25_set18, 1, idList.get(449));
		answerRepo.save(a2_answer_25_set18);
	}
}
