/**
 * 
 */
angular.module('main', [])
	.controller('home', function($scope, $http) {
		
		// Provide user credentials
		$http.get('/usr').success(function(data) {
			$scope.usr = data;
		})
		
		// Update table 'add to cart' according to the selection in the main table
		$scope.selectedProducts = [];
		
		$scope.addToCart = function addToCart(product, selectedItems){
			var idx = selectedItems.indexOf(product);
			if(idx > -1){
				selectedItems.splice(idx, 1);
				$scope.totalPrice = 0;
				for(i=0; i<selectedItems.length; i++){
					$scope.totalPrice += parseInt(selectedItems[i].price) * parseInt(selectedItems[i].quantity);
				}
			}
			else{
				if(parseInt(product.stock)){
					product.quantity = 1;
					selectedItems.push(product);
					$scope.totalPrice = 0;
					for(i=0; i<selectedItems.length; i++){
						$scope.totalPrice += parseInt(selectedItems[i].price) * parseInt(selectedItems[i].quantity);
					}
				}
				else{
					window.alert("Out of stock. Please contact Cart.js for further details.")
				}
			}
		}
		
		$scope.exists = function exists(product, selectedItems){
			return selectedItems.indexOf(product) > -1;
		}
		
		// Auto calculation for multiple product specifications
		$scope.recalc = function recalc(selectedItems){
			$scope.totalPrice = 0;
			for(i=0; i<selectedItems.length; i++){
				$scope.totalPrice += parseInt(selectedItems[i].price) * parseInt(selectedItems[i].quantity);
			}
		}
		
		// Provide all the available products in the main table
		$http.get('/products/all').success(function(data) {
			$scope.products = data;
		})
		
		// Update (manual) the table when there is an update from administration panel
		$scope.updateTable = function updateTable(){
			$http.get('/products/all').success(function(data) {
				$scope.products = data;
				$scope.selectedProducts = [];
				$scope.totalPrice = 0;
			})
		}
		
		// Purchase products syncing with the database
		$scope.purchaseProducts = function purchaseProducts(selectedItems){
			if(confirm("Are you sure to proceed with the payment?") == true){
				for(i=0; i<selectedItems.length; i++){
					$http({
						method: 'PUT',
						url: '/products/purchase/'+selectedItems[i].id+'/'+selectedItems[i].quantity+'/'+selectedItems[i].stock
					}).success(function(data){
							$scope.purchased = data;
					})
				}
				window.open("../confirmation.html", "_self");
			}
		}
		
		
		/** Controllers for administration starts from here**/
		
		// Add product
		$scope.addProduct = function addProduct(product){
			$http.post('/products/create/'+product.name+'/'+product.price+'/'+product.stock).success(function(data) {
				$scope.added = data;
			})
		}
		
		// Search for a product
		$scope.findProduct = function findProduct(product){
			$http({
				method: 'GET',
				url: '/products/read/'+product.id
			}).success(function(data){
					$scope.read = data;
			})
		}
		
		// Update a product
		$scope.updateProduct = function updateProduct(product){
			$http({
				method: 'PUT',
				url: '/products/update/'+product.id+'/'+product.name+'/'+product.price+'/'+product.stock
			}).success(function(data){
					$scope.update = data;
			})
		}
		
		// Delete a product
		$scope.deleteProduct = function updateProduct(product){
			$http({
				method: 'DELETE',
				url: '/products/delete/'+product.id
			}).success(function(data){
					$scope.erase = data;
			})
		}
		
	});

//var webApp = angular.module('salam', []);
//
//webApp.controller('UserController', ['$scope', function($scope){
//	console.log('UserController started...')
//	$scope.users = [
//	                {'id': 'zami', 'email': 'zamiguy_ni@yahoo.com', 'fullName': 'Zamrath Nizam'},
//	                {'id': 'anon', 'email': 'aannoonnanon@gmail.com', 'fullName': 'Anon Anon'},
//	                {'id': 'sami', 'email': 'zamiguy.ni@gmail.com', 'fullName': 'Samurath Nizam'}
//	                ];
//}]);