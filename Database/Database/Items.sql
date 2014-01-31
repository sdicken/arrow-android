CREATE TABLE [dbo].[Items]
(
	[Id] INT NOT NULL PRIMARY KEY, 
    [RestrauntId] INT NOT NULL, 
    [Name] TEXT NOT NULL, 
    [Price] MONEY NOT NULL, 
    [Description] TEXT NOT NULL, 
    CONSTRAINT [FK_Items_Restraunts] FOREIGN KEY ([RestrauntId]) REFERENCES [Restraunts]([Id])
)

GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The unique id of the menu item',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Items',
    @level2type = N'COLUMN',
    @level2name = N'Id'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The id of the restraunt that this item appears on the menu of',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Items',
    @level2type = N'COLUMN',
    @level2name = N'RestrauntId'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The name of the item',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Items',
    @level2type = N'COLUMN',
    @level2name = N'Name'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The price of the item',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Items',
    @level2type = N'COLUMN',
    @level2name = N'Price'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The description of the item',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Items',
    @level2type = N'COLUMN',
    @level2name = N'Description'