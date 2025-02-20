import React, { useState } from "react";
import { createProduct } from "../services/ProductService";
import { useNavigate } from "react-router-dom";

const ProductForm = () => {
  const [newProduct, setNewProduct] = useState({
    productName: "",
    productType: "",
    priceShort: "",
    priceLong: "",
    productStatus: "",
    address: "",
    acreage: "",
    describes: ""
  });

  const [errors, setErrors] = useState({});
  const navigate = useNavigate();

  const handleChange = (e) => {
    setNewProduct({ ...newProduct, [e.target.name]: e.target.value });
    setErrors({ ...errors, [e.target.name]: "" });
  };

  const validateForm = () => {
    let newErrors = {};
    
    Object.keys(newProduct).forEach((key) => {
      if (!newProduct[key]) {
        newErrors[key] = "Trường này không được để trống";
      }
    });

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const addProduct = () => {
    if (!validateForm()) {
      alert("Vui lòng điền đầy đủ thông tin!");
      return;
    }

    console.log("Thêm sản phẩm:", newProduct);
    
    createProduct(newProduct)
      .then((response) => {
        console.log("Sản phẩm đã được lưu:", response.data);
        alert("Thêm sản phẩm thành công!");
        navigate('/');
      })
      .catch((error) => {
        console.error("Lỗi khi thêm sản phẩm:", error);
        alert("Có lỗi xảy ra khi thêm sản phẩm!");
      });
  };

  return (
    <div className="container mt-4">
      <div className="row justify-content-center">
        <div className="col-md-8">
          <div className="card shadow-lg">
            <div className="card-header bg-primary text-white text-center">
              <h2>Thêm sản phẩm</h2>
            </div>
            <div className="card-body">
              <form>
                <div className="mb-3">
                  <label className="form-label">Tên sản phẩm</label>
                  <input
                    type="text"
                    name="productName"
                    className={`form-control ${errors.productName ? "is-invalid" : ""}`}
                    placeholder="Nhập tên sản phẩm"
                    value={newProduct.productName}
                    onChange={handleChange}
                  />
                  {errors.productName && <div className="invalid-feedback">{errors.productName}</div>}
                </div>
                
                <div className="mb-3">
                  <label className="form-label">Loại sản phẩm</label>
                  <input
                    type="text"
                    name="productType"
                    className={`form-control ${errors.productType ? "is-invalid" : ""}`}
                    placeholder="Nhập loại sản phẩm"
                    value={newProduct.productType}
                    onChange={handleChange}
                  />
                  {errors.productType && <div className="invalid-feedback">{errors.productType}</div>}
                </div>

                <div className="row">
                  <div className="col-md-6 mb-3">
                    <label className="form-label">Giá bán theo ngày</label>
                    <input
                      type="text"
                      name="priceShort"
                      className={`form-control ${errors.priceShort ? "is-invalid" : ""}`}
                      placeholder="Nhập giá bán theo ngày"
                      value={newProduct.priceShort}
                      onChange={handleChange}
                    />
                    {errors.priceShort && <div className="invalid-feedback">{errors.priceShort}</div>}
                  </div>

                  <div className="col-md-6 mb-3">
                    <label className="form-label">Giá bán theo tháng</label>
                    <input
                      type="text"
                      name="priceLong"
                      className={`form-control ${errors.priceLong ? "is-invalid" : ""}`}
                      placeholder="Nhập giá bán theo tháng"
                      value={newProduct.priceLong}
                      onChange={handleChange}
                    />
                    {errors.priceLong && <div className="invalid-feedback">{errors.priceLong}</div>}
                  </div>
                </div>

                <div className="mb-3">
                  <label className="form-label">Trạng thái</label>
                  <input
                    type="text"
                    name="productStatus"
                    className={`form-control ${errors.productStatus ? "is-invalid" : ""}`}
                    placeholder="Nhập trạng thái"
                    value={newProduct.productStatus}
                    onChange={handleChange}
                  />
                  {errors.productStatus && <div className="invalid-feedback">{errors.productStatus}</div>}
                </div>

                <div className="mb-3">
                  <label className="form-label">Địa chỉ</label>
                  <input
                    type="text"
                    name="address"
                    className={`form-control ${errors.address ? "is-invalid" : ""}`}
                    placeholder="Nhập địa chỉ"
                    value={newProduct.address}
                    onChange={handleChange}
                  />
                  {errors.address && <div className="invalid-feedback">{errors.address}</div>}
                </div>

                <div className="mb-3">
                  <label className="form-label">Diện tích (m²)</label>
                  <input
                    type="number"
                    name="acreage"
                    className={`form-control ${errors.acreage ? "is-invalid" : ""}`}
                    placeholder="Nhập diện tích"
                    value={newProduct.acreage}
                    onChange={handleChange}
                  />
                  {errors.acreage && <div className="invalid-feedback">{errors.acreage}</div>}
                </div>

                <div className="mb-3">
                  <label className="form-label">Mô tả</label>
                  <textarea
                    name="describes"
                    className={`form-control ${errors.describes ? "is-invalid" : ""}`}
                    placeholder="Nhập mô tả sản phẩm"
                    rows="3"
                    value={newProduct.describes}
                    onChange={handleChange}
                  ></textarea>
                  {errors.describes && <div className="invalid-feedback">{errors.describes}</div>}
                </div>

                <button type="button" className="btn btn-primary w-100" onClick={addProduct}>
                  Thêm sản phẩm
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProductForm;