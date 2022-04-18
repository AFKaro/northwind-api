-- Se o cliente for de um pais diferente de onde vem o produto então há um limite de 5 produtos por mês

create trigger limite_compra
on dbo.[Order Details] for insert
as

if exists(select * from inserted ordersDetails
				join Orders orders on orders.OrderID = ordersDetails.OrderID 
				join Customers c on c.CustomerID = orders.CustomerID 
				join Products p on p.ProductID = ordersDetails.ProductID 
				join Suppliers s on s.SupplierID = p.SupplierID 
			where c.country <> s.Country  
			and (
				(select count(oAux.OrderID) 
					from dbo.Orders oAux
					join Customers cAux on cAux.CustomerID = oAux.CustomerID 
					join [Order Details] odAux on odAux.OrderID = oAux.OrderID 
					join Products pAux on pAux.ProductID = odAux.ProductID 
					join Suppliers sAux on sAux.SupplierID = pAux.SupplierID 
					
					where oAux.CustomerID = c.CustomerID
					and cAux.Country <> sAux.Country 
					and year(oAux.OrderDate) = year(orders.OrderDate)
					and month(oAux.OrderDate) = month(orders.OrderDate)) > 5 
				))
begin
	RAISERROR('Erro: a compra não pôde ser registrada!
			Motivo: cliente é de um pais diferente do fornecedor do produto pedido e exedeu o limite de 5 compras no mês', 1, 1)
	rollback
end


