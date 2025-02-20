import React, { useEffect, useState, useCallback } from 'react';
import { listProduct } from '../services/ProductService';
import { useNavigate } from 'react-router-dom';

const ListProduct = () => {
  const [products, setProducts] = useState([]);
  const [search, setSearch] = useState('');
  const [sortType, setSortType] = useState('');
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(10);
  const navigate = useNavigate();

  // Hàm lấy danh sách sản phẩm từ API
  const fetchProducts = async (keyword = '', sort = []) => {
    try {
      const response = await listProduct(page, size, keyword, sort);
      setProducts(response);
    } catch (error) {
      console.error('Lỗi khi lấy danh sách sản phẩm:', error);
    }
  };

  // Khi trang hoặc sắp xếp thay đổi, gọi API
  useEffect(() => {
    fetchProducts();
  }, [page, size, sortType]);

  // Xử lý tìm kiếm sản phẩm
  const handleSearchChange = (e) => setSearch(e.target.value);
  const handleSearchSubmit = () => fetchProducts(search, sortType ? [`${sortType.split('-')[0]},${sortType.split('-')[1]}`] : []);

  // Xử lý sắp xếp sản phẩm
  const handleSort = (e) => {
    setSortType(e.target.value);
    fetchProducts(search, e.target.value ? [`${e.target.value.split('-')[0]},${e.target.value.split('-')[1]}`] : []);
  };

  // Chuyển hướng đến trang thêm sản phẩm mới
  const addNewProduct = useCallback(() => navigate('/add-product'), [navigate]);

  // Chuyển hướng đến trang chi tiết sản phẩm
  const viewProductDetails = useCallback(
    (id) => navigate(`/product/${id}`),
    [navigate]
  );

  return (
    <div className="container mt-3">
      {/* Thanh tìm kiếm và bộ lọc */}
      <div className="d-flex justify-content-between mb-3">
        <input
          className="form-control w-25"
          type="text"
          placeholder="Tìm kiếm..."
          value={search}
          onChange={handleSearchChange}
        />
        <button className="btn btn-secondary" onClick={handleSearchSubmit}>
          Tìm kiếm
        </button>
        <select className="form-select w-25" onChange={handleSort} value={sortType}>
          <option value="">Sắp xếp</option>
          <option value="address,asc">Địa chỉ (A-Z)</option>
          <option value="address,desc">Địa chỉ (Z-A)</option>
          <option value="price,asc">Giá (Thấp - Cao)</option>
          <option value="price,desc">Giá (Cao - Thấp)</option>
        </select>
        <button className="btn btn-primary" onClick={addNewProduct}>
          Thêm phòng
        </button>
      </div>

      {/* Danh sách sản phẩm */}
      {products.length === 0 ? (
        <p className="text-center text-muted">Không tìm thấy sản phẩm nào.</p>
      ) : (
        products.map((product) => (
          <div key={product.id} className="row border p-3 align-items-center mb-2">
            <div className="col-md-3">
              <img
                src={product.imageUrl || '/default-image.jpg'}
                className="img-fluid rounded"
                alt={product.productName}
              />
            </div>
            <div className="col-md-5">
              <h5>
                {product.productName} | {product.productType}
              </h5>
              <p>{product.address}</p>
              <p>Diện tích: {product.acreage} m²</p>
              <p>{product.describes}</p>
            </div>
            <div className="col-md-2 text-end">
              <div className="row">
                <div className="col-6">
                  <strong>/ Ngày</strong>
                </div>
                <div className="col-6">
                  <strong>/ Tháng</strong>
                </div>
              </div>
              <div className="row">
                <div className="col-6">{product.priceShort.toLocaleString()} đ</div>
                <div className="col-6">{product.priceLong.toLocaleString()} đ</div>
              </div>
            </div>
            <div className="col-md-2 d-flex justify-content-between">
              <button className="btn btn-secondary">{product.productStatus}</button>
              <button className="btn btn-primary" onClick={() => viewProductDetails(product.id)}>
                Chi tiết
              </button>
            </div>
          </div>
        ))
      )}
    </div>
  );
};

export default ListProduct;
