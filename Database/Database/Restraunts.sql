CREATE TABLE [dbo].[Restraunts]
(
	[Id] INT NOT NULL PRIMARY KEY, 
    [Name] TEXT NOT NULL, 
    [Street] TEXT NOT NULL, 
    [City] TEXT NOT NULL DEFAULT 'Louisville', 
    [State] NCHAR(2) NOT NULL DEFAULT 'KY', 
    [Zip] NUMERIC(5) NOT NULL DEFAULT 40208, 
    [Phone] TEXT NULL, 
    [Fax] TEXT NULL, 
    [Url] TEXT NULL, 
    [Email] TEXT NULL
)

GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The unique id of the restraunt',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Restraunts',
    @level2type = N'COLUMN',
    @level2name = N'Id'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The name of the restraunt',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Restraunts',
    @level2type = N'COLUMN',
    @level2name = N'Name'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The street level address of the restraunt',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Restraunts',
    @level2type = N'COLUMN',
    @level2name = N'Street'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The city address of the restraunt',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Restraunts',
    @level2type = N'COLUMN',
    @level2name = N'City'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The state postal code for the restraunt',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Restraunts',
    @level2type = N'COLUMN',
    @level2name = N'State'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The ZIP code of the address of the restraunt',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Restraunts',
    @level2type = N'COLUMN',
    @level2name = N'Zip'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The phone number of the restraunt',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Restraunts',
    @level2type = N'COLUMN',
    @level2name = N'Phone'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The fax number of the restraunt',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Restraunts',
    @level2type = N'COLUMN',
    @level2name = N'Fax'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The url for the restraunt website',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Restraunts',
    @level2type = N'COLUMN',
    @level2name = N'Url'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The email for the restraunt.',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Restraunts',
    @level2type = N'COLUMN',
    @level2name = N'Email'