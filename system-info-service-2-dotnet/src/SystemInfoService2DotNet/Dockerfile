FROM mcr.microsoft.com/dotnet/aspnet:8.0-alpine AS base
USER $APP_UID
WORKDIR /app

FROM mcr.microsoft.com/dotnet/sdk:8.0-alpine AS build
ARG BUILD_CONFIGURATION=Release
COPY ["src/SystemInfoService2DotNet/SystemInfoService2DotNet.csproj", "src/SystemInfoService2DotNet/"]
RUN dotnet restore "src/SystemInfoService2DotNet/SystemInfoService2DotNet.csproj"
COPY . .
WORKDIR "/src/SystemInfoService2DotNet"
RUN dotnet build "SystemInfoService2DotNet.csproj" -c $BUILD_CONFIGURATION -o /app/build

FROM build AS publish
ARG BUILD_CONFIGURATION=Release
RUN dotnet publish "SystemInfoService2DotNet.csproj" -c $BUILD_CONFIGURATION -o /app/publish /p:UseAppHost=false

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "SystemInfoService2DotNet.dll"]



