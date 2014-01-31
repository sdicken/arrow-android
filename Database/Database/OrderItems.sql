CREATE TABLE [dbo].[OrderItems]
(
	[Id] INT NOT NULL PRIMARY KEY, 
    [OrderId] INT NOT NULL, 
    [ItemId] INT NOT NULL, 
    [Quantity] INT NOT NULL, 
    [TotalPrice] MONEY NOT NULL, 
    [Comments] NTEXT NULL, 
    CONSTRAINT [FK_OrderItems_Orders] FOREIGN KEY ([OrderId]) REFERENCES [Orders]([Id]), 
    CONSTRAINT [FK_OrderItems_Items] FOREIGN KEY ([ItemId]) REFERENCES [Items]([Id])
)

GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The unique id for the order item',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'OrderItems',
    @level2type = N'COLUMN',
    @level2name = N'Id'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The Id of the order that the item(s) are on.',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'OrderItems',
    @level2type = N'COLUMN',
    @level2name = N'OrderId'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The Id of the item being ordered.',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'OrderItems',
    @level2type = N'COLUMN',
    @level2name = N'ItemId'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The number of Items ordered.',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'OrderItems',
    @level2type = N'COLUMN',
    @level2name = N'Quantity'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The total price of items ordered',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'OrderItems',
    @level2type = N'COLUMN',
    @level2name = N'TotalPrice'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'Any comments relating to this item(s). This can be information such as what toppings should go on a sandwhich. The Client User should be warned that they will be responsible for possible increases in the price of their order due to the input given here.',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'OrderItems',
    @level2type = N'COLUMN',
    @level2name = N'Comments'