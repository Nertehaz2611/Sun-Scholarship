<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
<meta charset="UTF-8">
<meta name="description" content="Ashion Template">
<meta name="keywords" content="Ashion, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Ashion | Template</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cookie&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700;800;900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="css/magnific-popup.css" type="text/css">
<link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
<link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="css/style.css" type="text/css">
<link rel="stylesheet" href="filecss/img-cart.css">
</head>

<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Offcanvas Menu Begin -->
	<div class="offcanvas-menu-overlay"></div>
	<div class="offcanvas-menu-wrapper">
		<div class="offcanvas__close">+</div>
		<ul class="offcanvas__widget">
			<li><span class="icon_search search-switch"></span></li>
			<li><a href="#"><span class="icon_heart_alt"></span>
					<div class="tip">2</div> </a></li>
			<li><a href="#"><span class="icon_bag_alt"></span>
					<div class="tip">2</div> </a></li>
		</ul>
		<div class="offcanvas__logo">
			<a href="./index.html"><img src="img/logo.png" alt=""></a>
		</div>
		<div id="mobile-menu-wrap"></div>
		<div class="offcanvas__auth">
			<a href="#">Login</a> <a href="#">Register</a>
		</div>
	</div>
	<!-- Offcanvas Menu End -->

	<!-- Header Section Begin -->
	<header class="header">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xl-3 col-lg-2">
					<div class="header__logo">
						<a href="./index.html"><img src="img/logo.png" alt=""></a>
					</div>
				</div>
				<div class="col-xl-6 col-lg-7">
					<nav class="header__menu">
						<ul>
							<li><a href="./home">Home</a></li>
							<li><a href="#">Women’s</a></li>
							<li><a href="#">Men’s</a></li>
							<li><a href="./shop">Shop</a></li>
							<li class="active"><a href="#">Pages</a>
								<ul class="dropdown">
									<li><a href="./dangbai">Post Article</a></li>
									<li><a href="./cart">Shop Cart</a></li>
									<li><a href="./checkout">Checkout</a></li>
									<li><a href="./blog-details.html">Blog Details</a></li>
								</ul></li>
							<li><a href="./blog.html">Blog</a></li>
							<li><a href="./contact.html">Contact</a></li>
						</ul>
					</nav>
				</div>
				<div class="col-lg-3">
					<div class="header__right">
						<div class="header__right__auth">
							<c:if test="${tk == null }">
								<a href="home" class="login">Login</a>
								<a href="home" class="sigin">Register</a>
							</c:if>
							<c:if test="${tk != null }">
								<a href="login" class="fa-regular fa-circle-user">${tk.getUser()}</a>
							</c:if>
						</div>
						<ul class="header__right__widget">
							<li><span class="icon_search search-switch"></span></li>
							<li><a href="#"><span class="icon_heart_alt"></span> </a></li>
							<li><a href="#"><span class="icon_bag_alt"></span>
									<div class="tip">${slCart}</div> </a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="canvas__open">
				<i class="fa fa-bars"></i>
			</div>
		</div>
	</header>
	<!-- Header Section End -->

	<!-- Breadcrumb Begin -->
	<div class="breadcrumb-option">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="breadcrumb__links">
						<a href="./index.html"><i class="fa fa-home"></i> Home</a> <span>Shopping
							cart</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Breadcrumb End -->

	<!-- Shop Cart Section Begin -->
	<section class="shop-cart spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="shop__cart__table">
						<table>
							<thead>
								<tr>
									<th>Product</th>
									<th>Price</th>
									<th>Quantity</th>
									<th>Total</th>
									<th></th>
								</tr>
							</thead>
							<tbody>

								<c:forEach var="o" items="${listSpCart}">
									<tr>
										<td class="cart__product__item "><img
											src="img/shop/${o.getAnh()}" alt="" class="img-size ">
											<div class="cart__product__item__title">
												<h6>
													<a class="nameSp" href="details?masp=${o.getMaSp()}">${o.getTenSp()}</a>
												</h6>
												<div class="rating">
													<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
														class="fa fa-star"></i> <i class="fa fa-star"></i> <i
														class="fa fa-star"></i>
												</div>
											</div></td>
										<td class="cart__price">$ ${o.getGia()}.0</td>
										<td class="cart__quantity">
											<div class="pro-qty">
												<input type="text" value="${o.getSl() }"
													class="${o.getMaSp() }"> <input type="text"
													value="${o.getMaxsl() }" style="display: none;">
											</div>
										</td>
										<td class="cart__total">$ ${o.getGia() * o.getSl()}.0</td>
										<td class="cart__close"><a
											href="deletecart?Masp=${o.getMaSp()}"><span
												class="icon_close"></span></a></td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-6">
					<div class="cart__btn">
						<a href="shop">Continue Shopping</a>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6">
					<div class="cart__btn update__btn">
						<a href="updata"><span class="icon_loading"></span> Update cart</a>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-6">
					<div class="discount__content">
						<h6>Discount codes</h6>
						<form action="#">
							<input type="text" placeholder="Enter your coupon code">
							<button type="submit" class="site-btn">Apply</button>
						</form>
					</div>
				</div>
				<div class="col-lg-4 offset-lg-2">
					<div class="cart__total__procced">
						<h6>Cart total</h6>
						<ul>
							<li>Subtotal <span class = "Subtotal"></span></li>
							<li>Total <span class = "Total"></span></li>
						</ul>
						<a href="checkout" class="primary-btn">Proceed to checkout</a>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Shop Cart Section End -->

	<!-- Instagram Begin -->
	<div class="instagram">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-2 col-md-4 col-sm-4 p-0">
					<div class="instagram__item set-bg"
						data-setbg="img/instagram/insta-1.jpg">
						<div class="instagram__text">
							<i class="fa fa-instagram"></i> <a href="#">@ ashion_shop</a>
						</div>
					</div>
				</div>
				<div class="col-lg-2 col-md-4 col-sm-4 p-0">
					<div class="instagram__item set-bg"
						data-setbg="img/instagram/insta-2.jpg">
						<div class="instagram__text">
							<i class="fa fa-instagram"></i> <a href="#">@ ashion_shop</a>
						</div>
					</div>
				</div>
				<div class="col-lg-2 col-md-4 col-sm-4 p-0">
					<div class="instagram__item set-bg"
						data-setbg="img/instagram/insta-3.jpg">
						<div class="instagram__text">
							<i class="fa fa-instagram"></i> <a href="#">@ ashion_shop</a>
						</div>
					</div>
				</div>
				<div class="col-lg-2 col-md-4 col-sm-4 p-0">
					<div class="instagram__item set-bg"
						data-setbg="img/instagram/insta-4.jpg">
						<div class="instagram__text">
							<i class="fa fa-instagram"></i> <a href="#">@ ashion_shop</a>
						</div>
					</div>
				</div>
				<div class="col-lg-2 col-md-4 col-sm-4 p-0">
					<div class="instagram__item set-bg"
						data-setbg="img/instagram/insta-5.jpg">
						<div class="instagram__text">
							<i class="fa fa-instagram"></i> <a href="#">@ ashion_shop</a>
						</div>
					</div>
				</div>
				<div class="col-lg-2 col-md-4 col-sm-4 p-0">
					<div class="instagram__item set-bg"
						data-setbg="img/instagram/insta-6.jpg">
						<div class="instagram__text">
							<i class="fa fa-instagram"></i> <a href="#">@ ashion_shop</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Instagram End -->

	<!-- Footer Section Begin -->
	<footer class="footer">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-6 col-sm-7">
					<div class="footer__about">
						<div class="footer__logo">
							<a href="./index.html"><img src="img/logo.png" alt=""></a>
						</div>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit,
							sed do eiusmod tempor incididunt cilisis.</p>
						<div class="footer__payment">
							<a href="#"><img src="img/payment/payment-1.png" alt=""></a>
							<a href="#"><img src="img/payment/payment-2.png" alt=""></a>
							<a href="#"><img src="img/payment/payment-3.png" alt=""></a>
							<a href="#"><img src="img/payment/payment-4.png" alt=""></a>
							<a href="#"><img src="img/payment/payment-5.png" alt=""></a>
						</div>
					</div>
				</div>
				<div class="col-lg-2 col-md-3 col-sm-5">
					<div class="footer__widget">
						<h6>Quick links</h6>
						<ul>
							<li><a href="#">About</a></li>
							<li><a href="#">Blogs</a></li>
							<li><a href="#">Contact</a></li>
							<li><a href="#">FAQ</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-2 col-md-3 col-sm-4">
					<div class="footer__widget">
						<h6>Account</h6>
						<ul>
							<li><a href="#">My Account</a></li>
							<li><a href="#">Orders Tracking</a></li>
							<li><a href="checkout">Checkout</a></li>
							<li><a href="#">Wishlist</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-4 col-md-8 col-sm-8">
					<div class="footer__newslatter">
						<h6>NEWSLETTER</h6>
						<form action="#">
							<input type="text" placeholder="Email">
							<button type="submit" class="site-btn">Subscribe</button>
						</form>
						<div class="footer__social">
							<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
								class="fa fa-twitter"></i></a> <a href="#"><i
								class="fa fa-youtube-play"></i></a> <a href="#"><i
								class="fa fa-instagram"></i></a> <a href="#"><i
								class="fa fa-pinterest"></i></a>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					<div class="footer__copyright__text">
						<p>
							Copyright &copy;
							<script>
								document.write(new Date().getFullYear());
							</script>
							All rights reserved | This template is made with <i
								class="fa fa-heart" aria-hidden="true"></i> by <a
								href="https://colorlib.com" target="_blank">Colorlib</a>
						</p>
					</div>
					<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
				</div>
			</div>
		</div>
	</footer>
	<!-- Footer Section End -->

	<!-- Search Begin -->
	<div class="search-model">
		<div class="h-100 d-flex align-items-center justify-content-center">
			<div class="search-close-switch">+</div>
			<form class="search-model-form">
				<input type="text" id="search-input" placeholder="Search here.....">
			</form>
		</div>
	</div>
	<!-- Search End -->

	<!-- Js Plugins -->
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/mixitup.min.js"></script>
	<script src="js/jquery.countdown.min.js"></script>
	<script src="js/jquery.slicknav.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/jquery.nicescroll.min.js"></script>
	<script src="js/main2.js"></script>
	<script src="js/cart.js"></script>
    <script src="js/sum.js"></script>
</body>

</html>