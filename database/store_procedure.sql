create proc dbo.resumoComprasEClientes
as
select distinct customer.CustomerID, customer.CompanyName, count(orders.OrderID) as quantidade_pedidos, 
sum(orderDetail.Quantity) as quantidade_produtos, sum(orderDetail.UnitPrice * orderDetail.Quantity) as total_gasto
from dbo.Customers customer
	join dbo.Orders orders on orders.CustomerID = customer.CustomerID
	join dbo.[Order Details] orderDetail on orderDetail.OrderID = orders.OrderID
	join dbo.Products product on product.ProductID = orderDetail.ProductID
	group by customer.CustomerID, customer.CompanyName
	order by customer.CustomerID
