CREATE TABLE [dbo].[Orders]
(
	[Id] INT NOT NULL PRIMARY KEY, 
    [SubmitTime] TIMESTAMP NOT NULL, 
    [PickUpTime] TIMESTAMP NULL, 
    [DeliveryTime] TIMESTAMP NULL, 
    [DriverId] INT NOT NULL, 
    [ClientId] INT NOT NULL, 
    [OrderTotal] MONEY NOT NULL, 
    [ItemsTotal] INT NOT NULL, 
    [RestrauntId] INT NOT NULL, 
    CONSTRAINT [FK_Orders_Users_Driver] FOREIGN KEY ([DriverId]) REFERENCES [Users]([Id]), 
    CONSTRAINT [FK_Orders_Users_Client] FOREIGN KEY ([ClientId]) REFERENCES [Users]([Id]), 
    CONSTRAINT [FK_Orders_Restraunts] FOREIGN KEY ([RestrauntId]) REFERENCES [Restraunts]([Id]), 
    CONSTRAINT [CK_Orders_SubmitTime] CHECK ([PickupTime]=NULL OR ([SubmitTime]<[PickupTime]) AND ([DeliveryTime]=NULL OR [SubmitTime]<[DeliveryTime])),
	CONSTRAINT [CK_Orders_PickupTime] CHECK ([PickupTime]>[SubmitTime] AND ([DeliveryTime]=NULL OR [PickupTime]<[DeliveryTime])),
	CONSTRAINT [CK_Orders_DeliveryTime] CHECK ([DeliveryTime]>[SubmitTime] AND [DeliveryTime]>[PickupTime]),
	CONSTRAINT [CK_Orders_OrderTotal] CHECK ([OrderTotal]>=0),
	CONSTRAINT [CK_Orders_ItemsTotal] CHECK ([ItemsTotal]>=0)
)

GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The time that the order was submitted.',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Orders',
    @level2type = N'COLUMN',
    @level2name = N'SubmitTime'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The time the driver picked up the order from the restraunt.',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Orders',
    @level2type = N'COLUMN',
    @level2name = N'PickUpTime'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The time the driver delivered the order to the customer.',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Orders',
    @level2type = N'COLUMN',
    @level2name = N'DeliveryTime'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The User Id of the driver.',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Orders',
    @level2type = N'COLUMN',
    @level2name = N'DriverId'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The User Id of the client.',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Orders',
    @level2type = N'COLUMN',
    @level2name = N'ClientId'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The total value of the order',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Orders',
    @level2type = N'COLUMN',
    @level2name = N'OrderTotal'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The total number of items in the order.',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Orders',
    @level2type = N'COLUMN',
    @level2name = N'ItemsTotal'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The restraunt that the order items  are from',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Orders',
    @level2type = N'COLUMN',
    @level2name = N'RestrauntId'