import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const API_BASE_URL = "http://localhost:8080";

const Login = ({ setIsAuthenticated }) => {
  const [formData, setFormData] = useState({ lessorEmail: "", lessorPassword: "" });
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        `${API_BASE_URL}/lessor/login`,
        formData,
        { withCredentials: true } 
      );

      if (response.data.code === 1000) {
        localStorage.setItem("isAuthenticated", "true");
        setIsAuthenticated(true);
        navigate("/");
      } else {
        alert("Email hoặc mật khẩu không đúng!");
      }
    } catch (error) {
      alert("Lỗi khi đăng nhập: " + (error.response?.data?.message || "Không thể kết nối đến máy chủ."));
    }
  };

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card shadow-lg">
            <div className="card-header bg-primary text-white text-center">
              <h2>Đăng nhập</h2>
            </div>
            <div className="card-body">
              <form onSubmit={handleSubmit}>
                <div className="mb-3">
                  <label className="form-label">Email</label>
                  <input
                    type="email"
                    name="lessorEmail"
                    className="form-control"
                    placeholder="Nhập email"
                    value={formData.lessorEmail}
                    onChange={handleChange}
                    required
                  />
                </div>
                <div className="mb-3">
                  <label className="form-label">Mật khẩu</label>
                  <input
                    type="password"
                    name="lessorPassword"
                    className="form-control"
                    placeholder="Nhập mật khẩu"
                    value={formData.lessorPassword}
                    onChange={handleChange}
                    required
                  />
                </div>
                <button type="submit" className="btn btn-primary w-100">Đăng nhập</button>
              </form>
              <div className="text-center mt-3">
                <button className="btn btn-link" onClick={() => navigate("/register")}>Đăng ký</button>
                <span> | </span>
                <button className="btn btn-link" onClick={() => navigate("/forgot-password")}>Quên mật khẩu?</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;