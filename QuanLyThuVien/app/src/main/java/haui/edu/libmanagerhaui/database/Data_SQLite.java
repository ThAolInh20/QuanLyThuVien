package haui.edu.libmanagerhaui.database;

public class Data_SQLite {
    public static final String INSERT_THUTHU = "insert into ThuThu(MaTT, HoTen, MatKhau) values" +
            "('admin', 'admin','admin')," +
            "('namnp', 'Nguyen Phuong Nam','111111')," +
            "('hieunt', 'Nguyen Trung Hieu','222222')," +
            "('phunq', 'Nguyen Que Phu','333333')," +
            "('ngocnm', 'Nguyen Minh Ngoc','444444')," +
            "('hoangtm', 'Trinh Minh Hoang','555555')";
    public static final String INSERT_THANHVIEN = "insert into ThanhVien(hoTen, namSinh) values" +
            "('Nguyễn Văn Hùng','2000')," +
            "('Trần Thị Hoa','2001')," +
            "('Lê Minh Tú','2002')," +
            "('Hoàng Thị Lan','2003')," +
            "('Phạm Văn Long','2004')," +
            "('Vũ Thị Mai','2000')," +
            "('Nguyễn Đức Anh','2001')," +
            "('Đặng Quốc Huy','2002')," +
            "('Trần Bảo Ngọc','2003')," +
            "('Phan Thị Hồng','2004')," +
            "('Lê Thị Hà','2000')," +
            "('Hoàng Văn Kiên','2001')," +
            "('Nguyễn Thu Hà','2002')," +
            "('Trần Quốc Bảo','2003')," +
            "('Lê Anh Dũng','2004')," +
            "('Ngô Thị Huệ','2000')," +
            "('Vũ Đức Thành','2001')," +
            "('Nguyễn Quang Hải','2002')," +
            "('Trần Văn Tuấn','2003')," +
            "('Phạm Thị Thanh','2004')," +
            "('Nguyễn Thu Minh','2000')," +
            "('Lê Minh Nhật','2001')," +
            "('Phan Anh Tuấn','2002')," +
            "('Trần Đức Lâm','2003')," +
            "('Nguyễn Khắc Huy','2004')," +
            "('Vũ Thị Thanh','2000')," +
            "('Đặng Văn Sơn','2001')," +
            "('Lê Quốc Khánh','2002')," +
            "('Nguyễn Thị Hương','2003')," +
            "('Trần Văn Hoàng','2004')";

    public static final String INSERT_LOAI_SACH = "insert into LoaiSach(tenLoai) values" +
            "('Truong Ngoai Ngu - Du lich')," +
            "('Truong Co khi - O to')," +
            "('Khoa Dien')," +
            "('Khoa Dien tu')," +
            "('Khoa CNTT')," +
            "('Khoa Ke toan - Kiem toan')," +
            "('Khoa Quan ly kinh doanh')";
    public static final String INSERT_SACH = "insert into Sach(tenSach, tacGia, giaThue, maLoai) values" +
            "('English for Tourism','John Walker','5000','1')," +
            "('Tiếng Anh Du Lịch','Nguyễn Văn Hải','5000','1')," +
            "('Hospitality Management','Sarah Wilson','5000','1')," +
            "('Quản Lý Khách Sạn','Trần Thị Lan','5000','1')," +
            "('Travel and Tourism Marketing','Michael White','5000','1')," +
            "('Marketing Du Lịch','Hoàng Văn Nam','5000','1')," +
            "('Automobile Engineering Basics','James Peterson','5500','2')," +
            "('Cơ Bản Về Kỹ Thuật Ô Tô','Phạm Đức Anh','5500','2')," +
            "('Advanced Automotive Technology','Robert Turner','5500','2')," +
            "('Công Nghệ Ô Tô Hiện Đại','Lê Minh Tú','5500','2')," +
            "('Vehicle Dynamics and Control','Rajesh Kumar','5500','2')," +
            "('Động Lực Học Và Điều Khiển Xe','Ngô Thanh Tùng','5500','2')," +
            "('Electric Circuit Fundamentals','David Johnson','6000','3')," +
            "('Cơ Bản Về Mạch Điện','Nguyễn Quốc Huy','6000','3')," +
            "('Power Systems Analysis','John Stevenson','6000','3')," +
            "('Phân Tích Hệ Thống Điện','Vũ Thị Mai','6000','3')," +
            "('Electric Motors and Drives','Austin Hughes','6000','3')," +
            "('Động Cơ Điện Và Ổ Đĩa','Trần Văn Bình','6000','3')," +
            "('Analog and Digital Circuits','Ali M. Niknejad','6500','4')," +
            "('Mạch Tương Tự Và Số','Phạm Văn Nam','6500','4')," +
            "('Introduction to VLSI Circuits','Wayne Wolf','6500','4')," +
            "('Giới Thiệu Mạch VLSI','Nguyễn Đức Thành','6500','4')," +
            "('Microcontroller Applications','Mazidi Muhammad','6500','4')," +
            "('Ứng Dụng Vi Điều Khiển','Hoàng Quốc Bảo','6500','4')," +
            "('Data Structures and Algorithms','Mark Allen Weiss','7000','5')," +
            "('Cấu Trúc Dữ Liệu Và Thuật Toán','Lê Anh Tuấn','7000','5')," +
            "('Operating System Concepts','Abraham Silberschatz','7000','5')," +
            "('Khái Niệm Hệ Điều Hành','Nguyễn Văn Lâm','7000','5')," +
            "('Artificial Intelligence Basics','Kevin Warwick','7000','5')," +
            "('Nhập Môn Trí Tuệ Nhân Tạo','Trần Thu Minh','7000','5')," +
            "('Financial Accounting Principles','Carl Warren','7500','6')," +
            "('Nguyên Tắc Kế Toán Tài Chính','Phạm Quang Hải','7500','6')," +
            "('Auditing and Assurance Services','Alvin Arens','7500','6')," +
            "('Dịch Vụ Kiểm Toán Và Bảo Đảm','Nguyễn Văn Hùng','7500','6')," +
            "('Cost Management Accounting','Don Hansen','7500','6')," +
            "('Kế Toán Quản Trị Chi Phí','Lê Minh Hoàng','7500','6')," +
            "('Business Strategy Management','Gerry Johnson','8000','7')," +
            "('Quản Lý Chiến Lược Kinh Doanh','Nguyễn Thu Hà','8000','7')," +
            "('Operations Management Essentials','Jay Heizer','8000','7')," +
            "('Những Điều Cần Biết Về Quản Lý Vận Hành','Trần Văn Khánh','8000','7')," +
            "('Marketing Principles and Practices','Philip Kotler','8000','7')," +
            "('Nguyên Lý Và Thực Tiễn Marketing','Vũ Minh Châu','8000','7')," +
            "('Tourism Economics','Chris Cooper','5000','1')," +
            "('Kinh Tế Du Lịch','Nguyễn Văn Bình','5000','1')," +
            "('Cultural Tourism','Greg Richards','5000','1')," +
            "('Du Lịch Văn Hóa','Trần Quốc Dũng','5000','1')," +
            "('Global Tourism Trends','Peter Smith','5000','1')," +
            "('Xu Hướng Du Lịch Toàn Cầu','Lê Quốc Khánh','5000','1')," +
            "('Automotive Sensors and Actuators','David Hall','5500','2')," +
            "('Cảm Biến Và Bộ Chấp Hành Ô Tô','Nguyễn Đăng Minh','5500','2')," +
            "('Electric Vehicle Technology','Mark Thompson','5500','2')," +
            "('Công Nghệ Xe Điện','Hoàng Văn Long','5500','2')," +
            "('Hybrid Electric Cars','Richard Bellman','5500','2')," +
            "('Xe Điện Lai','Vũ Thị Hoa','5500','2')," +
            "('High Voltage Engineering','Martin Brown','6000','3')," +
            "('Kỹ Thuật Cao Áp','Phạm Văn Duy','6000','3')," +
            "('Analog Circuit Design','Jim Williams','6000','4')," +
            "('Thiết Kế Mạch Tương Tự','Nguyễn Đức Hải','6000','4')," +
            "('Machine Learning Fundamentals','Ian Goodfellow','7000','5')," +
            "('Cơ Bản Về Học Máy','Lê Thị Mai','7000','5')," +
            "('Strategic Management Concepts','Frank Rothaermel','8000','7')," +
            "('Khái Niệm Quản Lý Chiến Lược','Trần Văn Hoàng','8000','7')" +
            ";";
    public static final String INSERT_PHIEU_MUON = "insert into PhieuMuon(MaTT,MaTV,MaSach,TienThue,Ngay,Trasach) values" +
            "('admin', '1', '15', '3000', '2024/01/05', 1)," +
            "('admin', '2', '3', '3500', '2024/01/10', 0)," +
            "('namnp', '3', '8', '4000', '2024/02/01', 1)," +
            "('namnp', '4', '12', '4500', '2024/02/12', 0)," +
            "('hieunt', '5', '5', '3000', '2024/03/03', 1)," +
            "('hieunt', '6', '9', '3500', '2024/03/15', 1)," +
            "('phunq', '7', '18', '3700', '2024/04/05', 0)," +
            "('phunq', '8', '22', '3900', '2024/04/20', 0)," +
            "('ngocnm', '9', '30', '4000', '2024/05/01', 1)," +
            "('ngocnm', '10', '7', '3200', '2024/05/10', 1)," +
            "('hoangtm', '11', '11', '3100', '2024/06/18', 0)," +
            "('hoangtm', '12', '25', '3600', '2024/07/04', 1)," +
            "('admin', '13', '2', '3400', '2024/07/25', 1)," +
            "('admin', '14', '19', '4000', '2024/08/09', 0)," +
            "('namnp', '15', '6', '3000', '2024/08/22', 1)," +
            "('hieunt', '16', '17', '3800', '2024/09/10', 1)," +
            "('phunq', '17', '24', '3500', '2024/09/28', 0)," +
            "('ngocnm', '18', '14', '4000', '2024/10/15', 1)," +
            "('hoangtm', '19', '28', '3900', '2024/11/01', 0)," +
            "('admin', '20', '23', '4100', '2024/12/20', 1)";

}
