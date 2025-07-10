//package com.example.uthsmarttasks.viewmodel
//
//import androidx.compose.runtime.*
//import androidx.lifecycle.ViewModel
//import com.example.uthsmarttasks.R
//import com.example.uthsmarttasks.model.Product
//
//class ProductViewModel : ViewModel() {
//    // List sp
//    val productList = listOf(
//        Product(
//            id = 1,
//            name = "Giày Nike Nam Nữ Chính Hãng - Nike Air Force 1 '07 LV8 - Màu Trắng | JapanSport HF2898-100",
//            price = "4.000.000₫",
//            imageResId = R.drawable.image,
//            description = """
//            Với giày chạy bộ, từng gram đều quan trọng. Đó là lý do tại sao đế giữa LIGHTSTRIKE PRO mới nhẹ hơn so với phiên bản trước. Mút foam đế giữa siêu nhẹ và thoải mái này có lớp đệm đàn hồi được thiết kế để hạn chế tiêu hao năng lượng. Trong các mẫu giày tập luyện, công nghệ này được thiết kế hỗ trợ cho cơ bắp của vận động viên để họ có thể phục hồi nhanh hơn giữa các cuộc đua.
//        """.trimIndent()
//        ),
//        Product(
//            id = 2,
//            name = "Adidas Ultra Boost",
//            price = "3.500.000₫",
//            imageResId = R.drawable.image,
//            description = "Giày chạy bộ nhẹ và êm với công nghệ Boost mới nhất."
//        ),
//        Product(
//            id = 3,
//            name = "Puma Suede Classic",
//            price = "2.000.000₫",
//            imageResId = R.drawable.image,
//            description = "Thiết kế cổ điển, chất liệu da lộn, cực kỳ êm chân."
//        )
//    )
//
//    // chi tiết sản phẩm
//    var selectedProduct by mutableStateOf(productList[0])
//}
package com.example.uthsmarttasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uthsmarttasks.api.RetrofitClient
import com.example.uthsmarttasks.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    // Danh sách sản phẩm hiển thị trên màn hình danh sách
    private val _productList = MutableStateFlow<List<Product>>(emptyList())
    val productList: StateFlow<List<Product>> = _productList

    // Sản phẩm đang được chọn
    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?> = _selectedProduct

    // Gọi API để lấy 1 sản phẩm, gói vào list để hiển thị
    fun fetchProductList() {
        viewModelScope.launch {
            try {
                val result = RetrofitClient.api.getProduct() // API trả về 1 object
                println("✅ API result: $result")
                _productList.value = listOf(result)
                _selectedProduct.value = result
            } catch (e: Exception) {
                e.printStackTrace()
                println("❌ API error: ${e.message}")
            }
        }
    }

    // Hàm chọn sản phẩm khi người dùng bấm vào card
    fun setSelectedProduct(product: Product) {
        _selectedProduct.value = product
    }
}


