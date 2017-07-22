var app = angular.module('app', ['ngResource']);

app.factory('MyService', function() {
	  return $resource('/api/aluno/:nome');
	});

app.controller('home', function($scope, $http, $resource) {
	var resource = $resource('/api/aluno/:nome');
	  
	  var aluno = {};
	  var alunos = [];
	  $scope.aluno = aluno;
	  
	  
	  alunos = resource.query(function() {
		  console.log(alunos);
	  });
		  
    $scope.deletar = function(aluno) {
    	resource.delete({nome: aluno.nome});
    	var index = alunos.indexOf(aluno);
    	alunos.splice(index, 1);
    }

    $scope.salvar = function(aluno) {
    	resource.save(aluno);
    	alunos.push(aluno);
    	aluno = {};
    }
    
    $scope.alunos = alunos;


})
