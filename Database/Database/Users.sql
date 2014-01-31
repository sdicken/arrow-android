CREATE TABLE [dbo].[Users]
(
	[Id] INT NOT NULL PRIMARY KEY, 
    [Username] NTEXT NOT NULL UNIQUE, 
    [Secret] NTEXT NOT NULL, 
    [Email] NTEXT NULL, 
    [Street] NTEXT NULL, 
    [City] NTEXT NULL DEFAULT 'Louisville', 
    [State] NTEXT NULL DEFAULT 'KY', 
    [Zip] NUMERIC(5) NULL DEFAULT 40208, 
    [TotalOrders] INT NOT NULL DEFAULT 0, 
    [TotalPaid] MONEY NOT NULL DEFAULT 0.00, 
    [LastOrder] TIMESTAMP NULL, 
    [Role] NCHAR(10) NOT NULL DEFAULT 'client'
)

GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'Username for the user that will be used to log into the system.',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Users',
    @level2type = N'COLUMN',
    @level2name = N'Username'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'Base64 encoded salt and hash of the authentication key for the user.',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Users',
    @level2type = N'COLUMN',
    @level2name = N'Secret'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'Primary email address of the user.',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Users',
    @level2type = N'COLUMN',
    @level2name = N'Email'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The street address (ie: 123 Sherman Way Apt #345b) the user is to be delivered at.',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Users',
    @level2type = N'COLUMN',
    @level2name = N'Street'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The city the user is located for delivery to.',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Users',
    @level2type = N'COLUMN',
    @level2name = N'City'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The state of the address of the user to be delivered to.',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Users',
    @level2type = N'COLUMN',
    @level2name = N'State'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The ZIP code of the address the user is to be delivered at.',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Users',
    @level2type = N'COLUMN',
    @level2name = N'Zip'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The access role for the user. Values are ''client'', ''driver'', ''dispatcher'',''admin''',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Users',
    @level2type = N'COLUMN',
    @level2name = N'Role'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The unique id of the user.',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Users',
    @level2type = N'COLUMN',
    @level2name = N'Id'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The total number of orders the user has either placed or serviced.',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Users',
    @level2type = N'COLUMN',
    @level2name = N'TotalOrders'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The total amount of money that the user has either paid or serviced.',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Users',
    @level2type = N'COLUMN',
    @level2name = N'TotalPaid'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The time of the last order was submited or serviced by a user.',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Users',
    @level2type = N'COLUMN',
    @level2name = N'LastOrder'