import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const API_BASE_URL = "http://localhost:8080";

const Register = () => {
  const [formData, setFormData] = useState({
    lessorName: "",
    lessorEmail: "",
    lessorAddress: "",
    lessorPassword: "",
    lessorStatus: "active",
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(`${API_BASE_URL}/lessor`, formData);
      if (response.data.result) {
        alert("Đăng ký thành công! Hãy đăng nhập.");
        navigate("/login");
      } else {
        alert("Đăng ký thất bại. Vui lòng thử lại.");
      }
    } catch (error) {
      alert("Lỗi khi đăng ký: " + (error.response?.data?.message || "Không thể kết nối đến máy chủ."));
    }
  };

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card shadow-lg">
            <div className="card-header bg-success text-white text-center">
              <h2>Đăng ký</h2>
            </div>
            <div className="card-body">
              <form onSubmit={handleSubmit}>
                <div className="mb-3">
                  <label className="form-label">Tên</label>
                  <input type="text" name="lessorName" className="form-control" placeholder="Nhập tên" onChange={handleChange} required />
                </div>
                <div className="mb-3">
                  <label className="form-label">Email</label>
                  <input type="email" name="lessorEmail" className="form-control" placeholder="Nhập email" onChange={handleChange} required />
                </div>
                <div className="mb-3">
                  <label className="form-label">Địa chỉ</label>
                  <input type="text" name="lessorAddress" className="form-control" placeholder="Nhập địa chỉ" onChange={handleChange} required />
                </div>
                <div className="mb-3">
                  <label className="form-label">Mật khẩu</label>
                  <input type="password" name="lessorPassword" className="form-control" placeholder="Nhập mật khẩu" onChange={handleChange} required />
                </div>
                <button type="submit" className="btn btn-success w-100">Đăng ký</button>
              </form>
              <div className="text-center mt-3">
                <button className="btn btn-link" onClick={() => navigate("/login")}>
                  Đã có tài khoản? Đăng nhập
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Register;