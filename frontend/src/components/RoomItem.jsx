import React from 'react';

const RoomItem = ({ product }) => {
  return (
    <div className="col-md-4"> {/* Mỗi card chiếm 1/3 chiều rộng trên màn hình medium */}
      <div className="card mb-4"> {/* Margin bottom để tạo khoảng cách giữa các card */}
        <img src={product.image} className="card-img-top" alt={product.productName} />
        <div className="card-body">
          <h5 className="card-title">{product.productName}</h5>
          <p className="card-text">{product.describes}</p>
          <div className="d-flex justify-content-between">
            <div>
              <p className="card-text">Giá: {product.price.toLocaleString()} VND</p>
              <p className="card-text">Diện tích: {product.acreage} m²</p>
            </div>
            <button className="btn btn-primary">Xem chi tiết</button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default RoomItem;