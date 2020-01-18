
function IMCController($scope) {
	$scope.result = 0.0;
	$scope.description = ""; 
	$scope.calculate = function() {
		if($scope.peso !== '' && $scope.altura !== '') {
			$scope.result = $scope.peso / ($scope.altura * $scope.altura);
		}
	};
}