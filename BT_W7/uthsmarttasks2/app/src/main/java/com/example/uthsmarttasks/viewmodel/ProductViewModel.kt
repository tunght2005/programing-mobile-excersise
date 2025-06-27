//package com.example.uthsmarttasks.viewmodel
//
//import androidx.lifecycle.ViewModel
//import com.example.uthsmarttasks.R
//import com.example.uthsmarttasks.model.Product
//
//class ProductViewModel : ViewModel() {
//    val selectedProduct = Product(
//        id = 1,
//        name = "Giày Nike Nam Nữ Chính Hãng - Nike Air Force 1 '07 LV8 - Màu Trắng | JapanSport HF2898-100",
//        price = "4.000.000₫",
//        imageResId = R.drawable.image,
//        description = """
//            Với giày chạy bộ, từng gram đều quan trọng. Đó là lý do tại sao đế giữa LIGHTSTRIKE PRO mới nhẹ hơn so với phiên bản trước. Mút foam đế giữa siêu nhẹ và thoải mái này có lớp đệm đàn hồi được thiết kế để hạn chế tiêu hao năng lượng. Trong các mẫu giày tập luyện, công nghệ này được thiết kế hỗ trợ cho cơ bắp của vận động viên để họ có thể phục hồi nhanh hơn giữa các cuộc đua.
//        """.trimIndent()
//    )
//}

package com.example.uthsmarttasks.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.uthsmarttasks.R
import com.example.uthsmarttasks.model.Product

class ProductViewModel : ViewModel() {
    // List sp
    val productList = listOf(
        Product(
            id = 1,
            name = "Giày Nike Nam Nữ Chính Hãng - Nike Air Force 1 '07 LV8 - Màu Trắng | JapanSport HF2898-100",
            price = "4.000.000₫",
            imageResId = R.drawable.image,
            description = """
            Với giày chạy bộ, từng gram đều quan trọng. Đó là lý do tại sao đế giữa LIGHTSTRIKE PRO mới nhẹ hơn so với phiên bản trước. Mút foam đế giữa siêu nhẹ và thoải mái này có lớp đệm đàn hồi được thiết kế để hạn chế tiêu hao năng lượng. Trong các mẫu giày tập luyện, công nghệ này được thiết kế hỗ trợ cho cơ bắp của vận động viên để họ có thể phục hồi nhanh hơn giữa các cuộc đua.
        """.trimIndent()
        ),
        Product(
            id = 2,
            name = "Adidas Ultra Boost",
            price = "3.500.000₫",
            imageResId = R.drawable.image,
            description = "Giày chạy bộ nhẹ và êm với công nghệ Boost mới nhất."
        ),
        Product(
            id = 3,
            name = "Puma Suede Classic",
            price = "2.000.000₫",
            imageResId = R.drawable.image,
            description = "Thiết kế cổ điển, chất liệu da lộn, cực kỳ êm chân."
        )
    )

    // chi tiết sản phẩm
    var selectedProduct by mutableStateOf(productList[0])
}
